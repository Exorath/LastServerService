/*
 * Copyright 2016 Exorath
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.exorath.service.lastserver.dynamodb;

import com.amazonaws.services.dynamodbv2.document.AttributeUpdate;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.exorath.service.commons.dynamoDBProvider.DynamoDBProvider;
import com.exorath.service.lastserver.Service;
import com.exorath.service.lastserver.res.GetResult;
import com.exorath.service.lastserver.res.PutResult;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.UUID;

public class DynamoDBService implements Service {
	private static final String TABLE_NAME = "LastServer";
	private static final String PRIM_KEY = "playerId";
	private static final String GAMEID_ATTR = "gameId";
	private static final String MAPID_ATTR = "mapId";
	private static final String FLAVORID_ATTR = "flavorId";
	private static final Logger logger = LoggerFactory.getLogger(DynamoDBService.class);
	private static final Gson Gson = new Gson();
	private static final Type MapStringStringType = new TypeToken<Map<String, String>>() {
	}.getType();

	private Table table;

	public DynamoDBService(DynamoDBProvider provider) {
		try {
			table = provider.getDB().createTable(new CreateTableRequest()
					.withTableName(TABLE_NAME)
					.withKeySchema(new KeySchemaElement(PRIM_KEY, KeyType.HASH))
					.withAttributeDefinitions(new AttributeDefinition(PRIM_KEY, ScalarAttributeType.S))
					.withProvisionedThroughput(new ProvisionedThroughput(1L, 1L))
			);
			logger.info("Created DynamoDB table " + TABLE_NAME + " with 1r/1w provisioning. Waiting for it to activate.");
		} catch (ResourceInUseException ex) {
			table = provider.getDB().getTable(TABLE_NAME);
			logger.info("DynamoDB table " + TABLE_NAME + " already existed. Waiting for it to activate.");
		}

		try {
			table.waitForActive();
		} catch (InterruptedException ex) {
			logger.error("DynamoDB table " + TABLE_NAME + " could not activate!\n" + ex.getMessage());
			System.exit(1);
		}
		logger.info("DynamoDB table " + TABLE_NAME + " active.");
	}


	@Override
	public GetResult getLastServer(UUID playerId) {
		GetItemSpec spec = new GetItemSpec().withPrimaryKey(PRIM_KEY, playerId.toString());
		Item item = table.getItem(spec);
		logger.info("Retrieved the following last server data for player " + playerId + ": " + item);
		if (item == null || !item.hasAttribute(GAMEID_ATTR)) {
			return new GetResult(null, null, null);
		} else {
			String gameId = null;
			String mapId = null;
			String flavorId = null;
			if (item.hasAttribute(GAMEID_ATTR)) {
				gameId = item.getString(GAMEID_ATTR);
			}
			if (item.hasAttribute(MAPID_ATTR)) {
				mapId = item.getString(MAPID_ATTR);
			}
			if (item.hasAttribute(FLAVORID_ATTR)) {
				flavorId = item.getString(FLAVORID_ATTR);
			}
			return new GetResult(gameId, mapId, flavorId);
		}
	}

	@Override
	public PutResult setLastServer(UUID playerId, String gameId, String requestBody) {
		if (gameId == null) {
			return new PutResult("gameId can't be null.");
		}

		Map<String, String> body;
		try {
			body = Gson.fromJson(requestBody, MapStringStringType);
		} catch (JsonSyntaxException ex) {
			return new PutResult(ex.getMessage());
		}

		UpdateItemSpec spec = new UpdateItemSpec()
				.withPrimaryKey(PRIM_KEY, playerId.toString())
				.withAttributeUpdate(
						new AttributeUpdate(GAMEID_ATTR).put(gameId),
						new AttributeUpdate(MAPID_ATTR).put(body.get(MAPID_ATTR)),
						new AttributeUpdate(FLAVORID_ATTR).put(body.get(FLAVORID_ATTR)));
		try {
			UpdateItemOutcome outcome = table.updateItem(spec);
			logger.info("Successfully set last server data for player " + playerId + ": gameId(" + gameId + ") mapId(" + body.get(MAPID_ATTR) + ") flavorId(" + body.get(FLAVORID_ATTR) + ")");
			return new PutResult();
		} catch (ConditionalCheckFailedException ex) {
			logger.warn("Failed to set last server data for player" + playerId + ": gameId(" + gameId + ") mapId(" + body.get(MAPID_ATTR) + ") flavorId(" + body.get(FLAVORID_ATTR) + ")\n:" + ex.getMessage());
			return new PutResult(ex.getMessage());
		}
	}
}

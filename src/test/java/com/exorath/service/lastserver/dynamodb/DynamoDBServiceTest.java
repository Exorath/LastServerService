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

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.exorath.service.lastserver.Service;
import com.exorath.service.lastserver.res.GetResult;
import com.exorath.service.lastserver.res.PutResult;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.UUID;

public class DynamoDBServiceTest {
	private static final String NULL_STRING = "NULL";
	private static final String GAME_ID_2 = "gameId2";
	private static final String MAP_ID_2 = "mapId2";
	private static final String FLAVOR_ID_2 = "flavorId2";

	@ClassRule
	public static final LocalDynamoDBCreationRule client = new LocalDynamoDBCreationRule();

	@Test
	public void testGetLastServerEqualsSetLastServer() {
		DynamoDB db = new DynamoDB(client.getAmazonDynamoDB());
		Service srv = new DynamoDBService(() -> db);

		UUID playerId = UUID.randomUUID();
		UUID playerId2 = UUID.randomUUID();
		PutResult putResult = srv.setLastServer(playerId, null, NULL_STRING, NULL_STRING);
		Assert.assertEquals(false, putResult.isSuccess());

		PutResult putResult2 = srv.setLastServer(playerId2, GAME_ID_2, MAP_ID_2, FLAVOR_ID_2);
		Assert.assertEquals(true, putResult2.isSuccess());

		GetResult getResult = srv.getLastServer(playerId);
		Assert.assertEquals(null, getResult.getGameId());

		GetResult getResult2 = srv.getLastServer(playerId2);
		Assert.assertEquals(GAME_ID_2, getResult2.getGameId());
		Assert.assertEquals(MAP_ID_2, getResult2.getMapId());
		Assert.assertEquals(FLAVOR_ID_2, getResult2.getFlavorId());
	}
}

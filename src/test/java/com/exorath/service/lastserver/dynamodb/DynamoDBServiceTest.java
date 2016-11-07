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
import org.junit.ClassRule;
import org.junit.Test;

import java.util.UUID;

public class DynamoDBServiceTest {

	@ClassRule
	public static final LocalDynamoDBCreationRule client = new LocalDynamoDBCreationRule();

	@Test
	public void testGetTreasuresEqualsSetTreasures() {
		DynamoDB db = new DynamoDB(client.getAmazonDynamoDB());
		Service srv = new DynamoDBService(() -> db);

		UUID playerId = UUID.randomUUID();
		UUID playerId2 = UUID.randomUUID();

	}
}

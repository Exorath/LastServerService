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

package com.exorath.service.lastserver;

import com.exorath.service.lastserver.res.GetResult;
import com.exorath.service.lastserver.res.PutResult;

import java.util.UUID;

/**
 * The last server {@code Service} allows for the setting and retrievel of the server data on which the {@code Player}
 * has last played.
 */
public interface Service {

	/**
	 * @param playerId The id of the {@code Player} for which to retrieve the {@code GetResult} data.
	 * @return A {@code GetResult} representing the data of the last server the player has played on.
	 */
	GetResult getLastServer(UUID playerId);

	/**
	 * @param playerId    The id of the {@code Player} to which this treasure should be added.
	 * @param gameId      The id of the last game the {@code Player} this {@code GetResult} object represents has played
	 *                    on.
	 * @param requestBody If available the id of the last map and flavor the {@code Player} this {@code GetResult}
	 *                    object represents has played on in json format.
	 * @return A {@code PutResult} with information regarding the success of the operation.
	 */
	PutResult setLastServer(UUID playerId, String gameId, String requestBody);
}

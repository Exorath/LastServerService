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

package com.exorath.service.lastserver.res;

import com.google.gson.annotations.SerializedName;

/**
 * Wraps the data of the last server a {@code Player} has played on.
 */
public class GetResult {
	@SerializedName("gameId")
	private String gameId;

	@SerializedName("mapId")
	private String mapId;

	@SerializedName("flavorId")
	private String flavorId;

	/**
	 * @param gameId   The id of the last game the {@code Player} has played on.
	 * @param mapId    If set the id of the last map the {@code Player} has played on.
	 * @param flavorId If set the id of the last flavor the {@code Player} has played.
	 */
	public GetResult(String gameId, String mapId, String flavorId) {
		this.gameId = gameId;
		this.mapId = mapId;
		this.flavorId = flavorId;
	}

	/**
	 * @return The id of the last game the {@code Player} has played on.
	 */
	public String getGameId() {
		return gameId;
	}

	/**
	 * @return If set the id of the last map the {@code Player} has played on.
	 */
	public String getMapId() {
		return mapId;
	}

	/**
	 * @return If set the id of the last flavor the {@code Player} has played.
	 */
	public String getFlavorId() {
		return flavorId;
	}
}

package com.exorath.service.lastserver.res;

import com.google.gson.annotations.SerializedName;

/**
 * Wraps the data of the last server a {@code Player} has played on.
 */
public class LastServer {
	@SerializedName("gameId")
	private String gameId;

	@SerializedName("mapId")
	private String mapId;

	@SerializedName("flavorId")
	private String flavorId;

	/**
	 * @param gameId   The id of the last game the {@code Player} this {@code LastServer} object represents has played
	 *                 on.
	 * @param mapId    If available the id of the last map the {@code Player} this {@code LastServer} object represents
	 *                 has played on.
	 * @param flavorId If available the id of the last flavor the {@code Player} this {@code LastServer} object
	 *                 represents has played.
	 */
	public LastServer(String gameId, String mapId, String flavorId) {
		this.gameId = gameId;
		this.mapId = mapId;
		this.flavorId = flavorId;
	}

	/**
	 * @return The id of the last game the {@code Player} this {@code LastServer} object represents has played on.
	 */
	public String getGameId() {
		return gameId;
	}

	/**
	 * @return If available the id of the last map the {@code Player} this {@code LastServer} object represents has
	 * played on.
	 */
	public String getMapId() {
		return mapId;
	}

	/**
	 * @return If available the id of the last flavor the {@code Player} this {@code LastServer} object represents has
	 * played.
	 */
	public String getFlavorId() {
		return flavorId;
	}
}

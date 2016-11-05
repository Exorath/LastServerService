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

import com.exorath.service.commons.portProvider.PortProvider;
import com.google.gson.Gson;
import spark.Route;

import java.util.UUID;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;

public class Transport {
	private static final String PLAYER_ID = "playerId";
	private static final String GAME_ID = "gameId";
	private static final String MAP_ID = "mapId";
	private static final String FLAVOR_ID = "flavorId";
	private static final Gson Gson = new Gson();

	public static void setup(Service srv, PortProvider provider) {
		port(provider.getPort());
		get("/players/:" + PLAYER_ID + "/lastserver/", Transport.getGetLastServerRoute(srv), Gson::toJson);
		put("/players/:" + PLAYER_ID + "/lastserver/:" + GAME_ID, Transport.getSetLastServerGameIdOnlyRoute(srv), Gson::toJson);
		put("/players/:" + PLAYER_ID + "/lastserver/:" + GAME_ID + "/:" + MAP_ID, Transport.getSetLastServerWithMapIdRoute(srv), Gson::toJson);
		put("/players/:" + PLAYER_ID + "/lastserver/:" + GAME_ID + "/:" + MAP_ID + "/:" + FLAVOR_ID, Transport.getSetLastServerWithMapAndFlavorIdRoute(srv), Gson::toJson);
	}

	public static Route getGetLastServerRoute(Service srv) {
		return (req, res) -> srv.getLastServer(UUID.fromString(req.params(PLAYER_ID)));
	}

	public static Route getSetLastServerGameIdOnlyRoute(Service srv) {
		return (req, res) -> srv.setLastServer(UUID.fromString(req.params(PLAYER_ID)), req.params(GAME_ID), null, null);
	}

	public static Route getSetLastServerWithMapIdRoute(Service srv) {
		return (req, res) -> srv.setLastServer(UUID.fromString(req.params(PLAYER_ID)), req.params(GAME_ID), req.params(MAP_ID), null);
	}

	public static Route getSetLastServerWithMapAndFlavorIdRoute(Service srv) {
		return (req, res) -> srv.setLastServer(UUID.fromString(req.params(PLAYER_ID)), req.params(GAME_ID), req.params(MAP_ID), req.params(FLAVOR_ID));
	}
}

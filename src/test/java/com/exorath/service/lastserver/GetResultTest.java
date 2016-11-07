package com.exorath.service.lastserver;

import com.exorath.service.lastserver.res.GetResult;
import org.junit.Assert;
import org.junit.Test;

public class GetResultTest {
	private static final String GAME_ID_1 = "gameId1";
	private static final String GAME_ID_2 = "gameId2";
	private static final String GAME_ID_3 = "gameId3";
	private static final String GAME_ID_4 = "gameId4";

	private static final String MAP_ID_2 = "mapId2";
	private static final String MAP_ID_3 = "mapId3";

	private static final String FLAVOR_ID_3 = "flavorId3";
	private static final String FLAVOR_ID_4 = "flavorId3";

	@Test
	public void testGetIdsMatchConstructorIds() {
		GetResult result1 = new GetResult(GAME_ID_1, null, null);
		Assert.assertEquals(result1.getGameId(), GAME_ID_1);
		Assert.assertEquals(result1.getMapId(), null);
		Assert.assertEquals(result1.getFlavorId(), null);

		GetResult result2 = new GetResult(GAME_ID_2, MAP_ID_2, null);
		Assert.assertEquals(result2.getGameId(), GAME_ID_2);
		Assert.assertEquals(result2.getMapId(), MAP_ID_2);
		Assert.assertEquals(result2.getFlavorId(), null);

		GetResult result3 = new GetResult(GAME_ID_3, MAP_ID_3, FLAVOR_ID_3);
		Assert.assertEquals(result3.getGameId(), GAME_ID_3);
		Assert.assertEquals(result3.getMapId(), MAP_ID_3);
		Assert.assertEquals(result3.getFlavorId(), FLAVOR_ID_3);

		GetResult result4 = new GetResult(GAME_ID_4, null, FLAVOR_ID_4);
		Assert.assertEquals(result4.getGameId(), GAME_ID_4);
		Assert.assertEquals(result4.getMapId(), null);
		Assert.assertEquals(result4.getFlavorId(), FLAVOR_ID_4);

		GetResult result5 = new GetResult(null, null, null);
		Assert.assertEquals(result5.getGameId(), null);
		Assert.assertEquals(result5.getMapId(), null);
		Assert.assertEquals(result5.getFlavorId(), null);

	}
}

package com.exorath.service.lastserver;

import com.exorath.service.lastserver.res.PutResult;
import org.junit.Assert;
import org.junit.Test;

public class PutResultTest {

	@Test
	public void testIsSuccessEqualsConstructorSuccess() {
		PutResult result = new PutResult();
		Assert.assertTrue(result.isSuccess());

		String error = "This is an error";
		result = new PutResult(error);
		Assert.assertFalse(result.isSuccess());
		Assert.assertEquals(result.getError(), error);
	}
}


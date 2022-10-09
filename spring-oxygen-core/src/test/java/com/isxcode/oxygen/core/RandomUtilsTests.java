package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.random.RandomUtils;
import org.junit.jupiter.api.Test;

public class RandomUtilsTests {

	@Test
	public void testRandom() {

		System.out.println("data-->" + RandomUtils.generateNumber(7));
	}
}

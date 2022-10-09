package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.snowflake.SnowflakeUtils;
import org.junit.jupiter.api.Test;

public class SnowflakeUtilsTests {

	@Test
	public void testSnowflake() {

		Thread thread1 =
				new Thread(
						() -> {
							for (int i = 0; i < 100; i++) {
								System.out.println("thread1-->" + SnowflakeUtils.getNextUuid());
							}
						});

		Thread thread2 =
				new Thread(
						() -> {
							for (int i = 0; i < 100; i++) {
								System.out.println("thread2-->" + SnowflakeUtils.getNextUuid());
							}
						});

		thread1.start();
		thread2.start();
	}
}

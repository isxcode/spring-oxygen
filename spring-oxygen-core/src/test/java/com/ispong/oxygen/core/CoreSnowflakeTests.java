package com.ispong.oxygen.core;

import com.ispong.oxygen.core.snowflake.SnowflakeUtils;
import org.junit.jupiter.api.Test;

public class CoreSnowflakeTests {

    @Test
    public void testSnowflake() {
        System.out.println(SnowflakeUtils.getNextUuid());
    }
}

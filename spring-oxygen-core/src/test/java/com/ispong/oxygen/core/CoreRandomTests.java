package com.ispong.oxygen.core;

import com.ispong.oxygen.core.random.RandomUtils;
import org.junit.jupiter.api.Test;

public class CoreRandomTests {

    @Test
    public void testRandom() {

        System.out.println("data-->" + RandomUtils.generateNumber(7));
    }
}

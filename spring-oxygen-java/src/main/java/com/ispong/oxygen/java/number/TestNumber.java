package com.ispong.oxygen.java.number;

import java.math.BigDecimal;

public class TestNumber {

    public static void main(String[] args) {

        BigDecimal bigDecimal = new BigDecimal("8.1");
        System.out.println(bigDecimal.divideAndRemainder(new BigDecimal("0.5"))[1].compareTo(new BigDecimal("0.0")) != 0);

    }
}

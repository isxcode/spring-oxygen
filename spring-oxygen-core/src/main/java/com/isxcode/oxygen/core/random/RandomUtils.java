package com.isxcode.oxygen.core.random;

public class RandomUtils {

    public static String generateNumber(int number) {

        int templateNumber = 1;
        while (number > 1) {
            templateNumber = templateNumber * 10;
            number--;
        }

        return String.valueOf((int) ((Math.random() * 9 + 1) * templateNumber));
    }
}

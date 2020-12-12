package com.isxcode.oxygen.core.random;

/**
 * random utils
 *
 * @author ispong
 * @since 0.0.2
 */
public class RandomUtils {

    /**
     * generate custom number
     *
     * @param number number
     * @return string
     * @since 0.0.2
     */
    public static String generateNumber(int number) {

        int templateNumber = 1;
        while (number > 1) {
            templateNumber = templateNumber * 10;
            number--;
        }

        return String.valueOf((int) ((Math.random() * 9 + 1) * templateNumber));
    }
}

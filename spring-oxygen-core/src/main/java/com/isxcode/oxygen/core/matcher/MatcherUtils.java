package com.isxcode.oxygen.core.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class MatcherUtils {

    public static String matcherParse(String startPattern, String endPattern, String content) {

        Pattern pattern = compile(startPattern + ".+?" + endPattern);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group().replace(startPattern, "").replace(endPattern, "");
        } else {
            return null;
        }
    }
}

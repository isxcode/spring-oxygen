package com.ispong.learn.java.regex;

public class RegexLearn {

    public static void main(String[] args) {

        // 正则表达式
        // 排除指定路径 /user/userSignIn  /user/userSignUp
        String content = "/file/delete";
        System.out.println(content.matches("(?!(/user/userSignIn)|(/file/delete)).*"));

    }
}

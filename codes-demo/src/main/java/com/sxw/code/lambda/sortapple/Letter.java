package com.sxw.code.lambda.sortapple;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-06-16
 * Time: 21:10
 *
 * @author shixiangweii
 */
public class Letter {
    public static String addHeader(String text) {
        return "From Raoul, Mario and Alan: " + text;
    }

    public static String addFooter(String text) {
        return text + " Kind regards";
    }

    public static String checkSpelling(String text) {
        return text.replaceAll("labda", "lambda");
    }
}
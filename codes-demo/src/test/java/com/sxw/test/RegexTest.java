package com.sxw.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-04-29
 * Time: 17:32
 */
public class RegexTest {


    /**
     * 匹配双引号，逗号，分号的问题
     * ()匹配组
     * 整体的匹配
     *
     * 查找字串
     * 标记重置
     */
    @Test
    public void testRegex() {
        Logger logger = LoggerFactory.getLogger(RegexTest.class);

        // s1 定义正则
        String regex = "^(1[0-9]{1})+(\\d{9}$)";
        // s2 预编译
        Pattern pattern = Pattern.compile(regex);
        // s3 match 匹配
        Matcher matcher = pattern.matcher("18019694171");

        boolean b = false;
        //b = matcher.find();
        //logger.info("matcher.find()\t{}", b);

        //b = matcher.find();
//        logger.info("matcher.find()\t{}", b);
        System.out.println(matcher.start());

        // 整个字符串是否匹配
        logger.info("matcher.matches()\t{}", matcher.matches());

        System.out.println(matcher.start());


        b = matcher.find();
        logger.info("matcher.find()\t{}", b);

        // the previous match，上个match,是指的，pattern.matcher，返回的match
        logger.info("matcher.group()\t{}", matcher.group());

        logger.info("matcher.group(0)\t{}", matcher.group(0));

        logger.info("matcher.group(1)\t{}", matcher.group(1));

        logger.info("matcher.group(2)\t{}", matcher.group(2));

        logger.info("matcher.group()\t{}", matcher.group());



        // matcher.reset();



    }

    /**
     * 常见的转义字符的匹配
     */
    @Test
    public void testMatch(){
        String str = "\\[]&&-^$?*+{}[]`!@#$%^&*()_+|,./<>?=";
        // []&&-^$?*+{}[]`!@#$%^&*()_+|,./<>?=
        // 匹配特殊字符：
        // 需要使用转义符：
        // \[
        // \]
        // \^
        // \$
        // \?
        // \*
        // \+
        // \{ \}
        // \( \)
        // \|

        // 可以不使用转义符
        // & \&
        // - \-
        // ` \`
        // ! \!
        // @ \@
        // # \#
        // % \%
        // _ \_
        // / \/
        // < \<
        // > \>
        // = \=
    }

}

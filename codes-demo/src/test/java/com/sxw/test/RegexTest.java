package com.sxw.test;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: 正则表达式测试
 * User: shixiangweii
 * Date: 2018-04-29
 * Time: 17:32
 */
public class RegexTest {
    /**
     * 正则表达式，编译得到模式，匹配器
     *
     * 字符串整体完全匹配,matches
     *
     * ()匹配组，[0]本身，小括号后的[1],[2]..., group()/group(0)
     *
     * “上一个匹配”，就是上一个pattern得到的matcher匹配器
     *
     * find 字串的查找
     */
    @Test
    public void testRegex() {
        // s1 定义正则表达式
        String regex = "^(1[0-9]{1})+(\\d{9}$)";
        // s2 预编译得到模式
        Pattern pattern = Pattern.compile(regex);
        // s3 匹配具体字符串，返回匹配器
        Matcher matcher = pattern.matcher("18019694171");

        // 没有执行"matcher.matches"会报异常，java.lang.IllegalStateException: No match available
        // System.out.println(matcher.start());

        // 完全匹配，会修改匹配器中的标记变量
        // true
        System.out.println(matcher.matches());

        // 0
        System.out.println(matcher.start());

        // 字符串本身
        // 18019694171
        System.out.println(matcher.group());

        // 字符串本身
        // 18019694171
        System.out.println(matcher.group(0));

        // 第一个匹配组
        // 18
        System.out.println(matcher.group(1));

        // 第2个匹配组
        // 019694171
        System.out.println(matcher.group(2));

        // 查找字串
        // false
        System.out.println(matcher.find());

        // 重置标记
        matcher.reset();

        // 查找
        // true
        System.out.println(matcher.find());

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

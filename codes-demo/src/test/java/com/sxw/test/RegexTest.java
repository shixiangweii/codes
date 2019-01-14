package com.sxw.test;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * matcher.matches 完全匹配，会修改游标
 * reset() 重置游标
 * find()查找下一个
 *
 *
 * Description: 正则表达式测试
 * User: shixiangweii
 * Date: 2018-04-29
 * Time: 17:32
 */
public class RegexTest {
    /**
     * 正则表达式，编译得到模式，匹配器
     * <p>
     * 字符串整体完全匹配,matches
     * <p>
     * ()匹配组，[0]本身，小括号后的[1],[2]..., group()/group(0)
     * <p>
     * “上一个匹配”，就是上一个pattern得到的matcher匹配器
     * <p>
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
    public void testMatch() {
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

    /**
     * "匹配"，"匹配位置"
     * "匹配结果"
     * "全局"
     *
     * 求助，*代表任意多，如果是懒惰模式不是应该匹配到“北”就停止吗？
     *
     * ：他的后面有(?=\\()，表示先行断言（顺序环视），需要确保前面“.*?”匹配到的字符后要挨着（左括号
     *
     * (?<=Expression)
     逆序肯定环视，表示所在位置左侧能够匹配Expression
     (?<!Expression)
     逆序否定环视，表示所在位置左侧不能匹配Expression
     (?=Expression)
     顺序肯定环视，表示所在位置右侧能够匹配Expression
     (?!Expression)
     顺序否定环视，表示所在位置右侧不能匹配Expression
     */
    @Test
    public void testReg() {
        String regex = ".*?(?=\\()";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("北京市(海淀区)(朝阳区)(西城区)");
        // 尝试匹配全部，此时会移动游标
        System.out.println(matcher.matches());
        // 重置游标
        matcher.reset();
        // MD还一定要加下这个find，不然匹配不到
        // 尝试寻找下一个匹配的子串
        if (matcher.find()) {
            /**
             * 其实就是group(0)
             */
            System.out.println(matcher.group());
            System.out.println(matcher.group(0));
        }
    }
}

package interview.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * matcher.matches 完全匹配，会修改游标
 * reset() 重置游标
 * find()查找下一个
 * <p>
 * <p>
 * Description: 正则表达式测试
 * User: shixiangweii
 * Date: 2018-04-29
 * Time: 17:32
 * <p>
 * 常用场景：
 *
 * 一、测试字符串内的模式（数据验证）
 * 1、整个字符串校验（填写表单的时候，手机号，身份证之类，所输入的整个字符串校验）
 *
 * 二、替换文本
 * 三、基于模式匹配从字符串中提取子字符串
 * <p>
 * 2、在一个字符串中查找提取（在一个文件中，查找某个固定的字符串，替换，提取IP，提取数字，html提取文本）
 *
 *
 * 不要把什么HTML，中文文档，代码什么特殊化去看，有什么换行，特殊符号，
 * 就是把他抽象成一个，字符串，换行，逗号，空格，tab，都是一个字符，都有对应的字符代码，
 * 一个文件里面的内容就是一个超级长的字符串，没什么换行不换行的（统一抽象概念）
 *
 * 语法
 * https://www.runoob.com/regexp/regexp-syntax.html
 *
 * 元字符
 * https://www.runoob.com/regexp/regexp-metachar.html
 *
 * + (前面的字符、匹配前面的子表达>=1)  [1,+∞)
 * * (前面的字符、匹配前面的子表达>=0)  [0,+∞)
 * ? (前面的字符、匹配前面的子表达=0,1) [0,1]
 *
 * 普通字符
 *   非打印字符
 *   可打印
 *
 * 特殊字符
 *   特殊含义的字符(匹配这些特殊字符，必须首先使字符"转义")
 *     限定符
 *       *,+ 贪婪的，因为它们会尽可能多的匹配文字，只有在它们的后面加上一个?就可以实现非贪婪或最小匹配
 *
 *     定位符
 *
 * . 匹配除换行符 \n 之外的任何单字符
 * $ 匹配输入字符串的结尾位置
 * ^ 匹配输入字符串的开始位置
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
     * <p>
     * 求助，*代表任意多，如果是懒惰模式不是应该匹配到“北”就停止吗？
     * <p>
     * ：他的后面有(?=\\()，表示先行断言（顺序环视），需要确保前面“.*?”匹配到的字符后要挨着（左括号
     * <p>
     * (?<=Expression)
     * 逆序肯定环视，表示所在位置左侧能够匹配Expression
     * (?<!Expression)
     * 逆序否定环视，表示所在位置左侧不能匹配Expression
     * (?=Expression)
     * 顺序肯定环视，表示所在位置右侧能够匹配Expression
     * (?!Expression)
     * 顺序否定环视，表示所在位置右侧不能匹配Expression
     * <p>
     * https://www.nowcoder.com/questionTerminal/758401c48ddc4deebb955821e175614d
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

    /**
     * https://www.nowcoder.com/questionTerminal/53ea61fdee47423fb63a71d9dd0309f4
     * <p>
     * ///////MyClass.class
     */
    @Test
    public void testReplace() {
        String classFile = "com.jd.".replaceAll(".", "/") + "MyClass.class";

        System.out.println(classFile);

        /**
         * 正则表达式，替换"."，
         */
        System.out.println("com.jd.".replaceAll("\\.", "/") + "MyClass.class");
    }


}

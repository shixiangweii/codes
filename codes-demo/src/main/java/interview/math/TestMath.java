package interview.math;

import org.junit.Test;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-03-02
 * Time: 15:49
 *
 * @author shixiangweii
 */
public class TestMath {
    /**
     * https://www.nowcoder.com/questionTerminal/6a7a47502af94cb98d4e005ddaf35f33
     * 链接：https://www.nowcoder.com/questionTerminal/6a7a47502af94cb98d4e005ddaf35f33
     * 来源：牛客网
     * <p>
     * Math.floor()   表示向下取整，返回double类型   （floor---地板）
     * Math.ceil()   表示向上取整，返回double类型    （ceil---天花板）
     * Math.round()  四舍五入，返回int类型
     * <p>
     * https://www.nowcoder.com/questionTerminal/380131bb026149b9a2157ec1b624036a
     * floor ： 意为地板，指向下取整，返回不大于它的最大整数
     * ceil ： 意为天花板，指向上取整，返回不小于它的最小整数
     * round ： 意为大约，表示“四舍五入”，而四舍五入是往大数方向入
     */
    @Test
    public void test() {
        System.out.println(Math.floor(-8.5));
    }

    /**
     * https://www.nowcoder.com/questionTerminal/c4b42ebc2e4b44f693b3a8372e7c1ee1
     */
    @Test
    public void testAdd() {
        // 这里有符号，负数，而这里的16进制表示，就是计算机中对负数的表示(补码)
        long a = 0Xf000000000000000L;
        // 正数
        long b = 0x7FFFFFFFFFFFFFFFL;
        // 先求-b在计算机中的表示(补码)
        System.out.println(a - b);
        System.out.println(0X7000000000000001L);

//        链接：https://www.nowcoder.com/questionTerminal/d44fe32902d44b178685d76a9c56ec27
//        来源：牛客网
/**
 * 作者：猴子派来的逗比
 链接：https://www.nowcoder.com/questionTerminal/d44fe32902d44b178685d76a9c56ec27
 来源：牛客网

 至于为什么是-0.0，而不是0.0，因为源码注释：&ldquo;If the argument value is less than zero but greater than -1.0,
 then the result is negative zero &rdquo;，翻译过来就是：如果参数是-1.0到0.0之间的数，结果是-0.0
 */
        double d1=-0.5;
        System.out.println("Ceil d1="+Math.ceil(d1));
        System.out.println("floor d1="+Math.floor(d1));
    }
}

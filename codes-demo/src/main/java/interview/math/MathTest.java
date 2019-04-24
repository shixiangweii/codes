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
public class MathTest {
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
        System.out.println(Math.floor(8.5));
    }
}

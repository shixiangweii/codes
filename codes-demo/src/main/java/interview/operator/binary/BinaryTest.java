package interview.operator.binary;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-04-24
 * Time: 8:53
 *
 * @author shixiangweii
 */
public class BinaryTest {
    /**
     * 负数补码，二进制计算
     * https://www.nowcoder.com/questionTerminal/c4b42ebc2e4b44f693b3a8372e7c1ee1
     */
    @Test
    public void testAdd() {
        // 这里有符号，负数，而这里的16进制表示，就是计算机中对负数的表示(补码)
        long a = 0Xf000000000000000L;
        System.out.println(a);
        // 正数
        long b = 0x7FFFFFFFFFFFFFFFL;
        System.out.println(b);
        // 先求-b在计算机中的表示(补码)
        // 负数，补码，取反加1
        System.out.println(a - b);
        System.out.println(0X7000000000000001L);

        // 链接：https://www.nowcoder.com/questionTerminal/d44fe32902d44b178685d76a9c56ec27
        // 来源：牛客网
        /**
         * 作者：猴子派来的逗比
         * 链接：https://www.nowcoder.com/questionTerminal/d44fe32902d44b178685d76a9c56ec27
         * 至于为什么是-0.0，而不是0.0，因为源码注释：&ldquo;If the argument value is less than zero but greater than -1.0,
         * then the result is negative zero &rdquo;，翻译过来就是：如果参数是-1.0到0.0之间的数，结果是-0.0
         */
        double d1 = -0.5;
        System.out.println("Ceil d1=" + Math.ceil(d1));
        System.out.println("floor d1=" + Math.floor(d1));


        a = 0XB000000000000000L;
        System.out.println(a);
        System.out.println(a << 1);
        // 丢弃符号位
        System.out.println(0X6000000000000000L);
    }

    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING = -1 << COUNT_BITS;
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    private static final int STOP = 1 << COUNT_BITS;
    private static final int TIDYING = 2 << COUNT_BITS;
    private static final int TERMINATED = 3 << COUNT_BITS;

    // Packing and unpacking ctl
    private static int runStateOf(int c) {
        return c & ~CAPACITY;
    }

    private static int workerCountOf(int c) {
        return c & CAPACITY;
    }

    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    @Test
    public void test() {
        System.out.println(COUNT_BITS);

        System.out.println(CAPACITY);

        System.out.println(RUNNING);
        System.out.println(SHUTDOWN);
        System.out.println(STOP);
        System.out.println(TIDYING);
        System.out.println(TERMINATED);
    }
}

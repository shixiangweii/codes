package interview.string;

import org.junit.Test;

/**
 * @author shixiangweii
 * @date 2019/7/31 10:53
 */
public class StringFormatTest {

    @Test
    public void testStringFormat() {
        // 0
        System.out.println(String.format("%.0f", 0.01));
        // 1
        System.out.println(String.format("%.0f", 0.91));
    }
}

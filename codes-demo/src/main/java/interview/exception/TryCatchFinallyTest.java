package interview.exception;

import org.junit.Test;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-11-02
 * Time: 15:32
 *
 * @author shixiangweii
 */
public class TryCatchFinallyTest {

    public static String func() {
        String name = "";
        try {
            name = "try";
            return name;
        } finally {
            name = "finally";

            // 如果finally中直接return则直接返回
           //  return "shixinagwe";
        }
    }

    @Test
    public void test() {
        // 返回值还是"try"
        System.out.println(func());
    }
}

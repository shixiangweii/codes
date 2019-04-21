package interview.operator.demo2;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-01-24
 * Time: 17:43
 *
 * @author shixiangweii
 */
public class SumTest {
    static {
        int x = 5;
    }

    private static int x, y;

    public static void main(String args[]) {
        // -1
        x--;
        myMethod();
        // 3
        // 1 + 0 + 2
        System.out.println(x + y + ++x);
    }

    private static void myMethod() {
        // -1 + 0
        y = x++ + ++x;
    }
}

package interview.exception;

/**
 * https://www.nowcoder.com/questionTerminal/ebe94f2eae814d30b12464487c53649c
 * Description:
 * User: shixiangweii
 * Date: 2019-01-15
 * Time: 9:03
 * <p>
 * https://www.nowcoder.com/questionTerminal/5a6ea98ed42347fe81c950a1a206dc7e
 *
 * @author shixiangweii
 */
public class TryCatchFinally {

    static void func() {
        try {
            throw new RuntimeException();
        } catch (Exception e) {
            // 在try语句块或catch语句块中执行到System.exit(0)直接退出程序
            // 结束程序，自然就退出程序
            System.exit(0);
        } finally {
            System.out.println("finally");
        }
    }

    public static String output = "";

    static void foo(int i) {
        try {
            if (i == 1) {
                throw new Exception();
            }
        } catch (Exception e) {
            output += "2";
            // 虽然这里return但还是会执行finally
            return;
        } finally {
            output += "3";
        }
        output += "4";
    }

    public static int func3() {
        try {
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            return 3;
        }
    }

    public static int func4(int n) {
        try {
            System.out.println("func4 before");
            System.out.println(100 / n);
            return 1;
        } catch (Exception e) {
            System.out.println("func4 after");
            throw new RuntimeException();
        } finally {
            System.out.println("finally");
            return 3;
        }
    }

    public static void main(String[] args) {
//        int i = func4(0);
//        System.out.println(i);

//
//        System.out.println(func3());
//
//        foo(0);
        foo(1);
        System.out.println(output);
//
//
//        func();


        String[] split = "yisiudfia肺部思考".split("");
        System.out.println(split);
    }
}

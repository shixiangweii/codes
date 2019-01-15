package com.sxw.test.basejava;

/**
 * https://www.nowcoder.com/questionTerminal/ebe94f2eae814d30b12464487c53649c
 * Description:
 * User: shixiangweii
 * Date: 2019-01-15
 * Time: 9:03
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

    public static void main(String[] args) {
        func();
    }
}

package interview.array;

import org.junit.Test;

/**
 * Description: 数组初始化测试
 * User: shixiangweii
 * Date: 2019-03-02
 * Time: 15:31
 *
 * @author shixiangweii
 */
public class TestArray {
    /**
     * 类的成员变量会被默认初始化
     */
    static int arr[] = new int[5];

    char[] ch = new char[3];

    String[] strings = new String[2];

    /**
     * https://www.nowcoder.com/questionTerminal/efb04eee96e44a29b4e0b70ed2cd7dd1
     */
    @Test
    public void testArrayInit() {
        // 0
        System.out.println(arr[0]);
        // true
        System.out.println(ch[0] == '\0');
        // null
        System.out.println(strings[0]);

        // 数组无论是在定义为实例变量还是局部变量，若没有初始化，都会被自动初始化
        int a[] = new int[7];
        // 0
        System.out.println(a[0]);
        String[] s = new String[8];
        // null
        System.out.println(s[0]);


    }

    @Test
    public void testArrayDeclare() {
        // https://www.nowcoder.com/questionTerminal/8d53f414b58b4c3cbc3e20c13facbeca
        float f1[][] = new float[6][6];
        float[] f2[] = new float[6][6];
        float[][] f4 = new float[6][6];
        System.out.println(f1[0][0]);
        System.out.println(f2[0][0]);
        System.out.println(f4[0][0]);

        // 第一维，要显示指定长度
        // float f3[][] = new float[][6];
        float f6[][] = new float[6][];
        float[] f7[] = new float[6][];
        float[][] f5 = new float[6][];

        // new二维数组
        f5[0] = new float[10];
        System.out.println(f5[0][0]);
        f6[0] = new float[1];
        System.out.println(f6[0][0]);
        // 没有new，f7[0]二维，会异常4
        System.out.println(f7[0][0]);
    }
}

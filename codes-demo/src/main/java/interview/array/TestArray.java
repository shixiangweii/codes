package interview.array;

import org.junit.Test;

/**
 * Description:
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
        System.out.println(arr[0]);
        System.out.println(ch[0]=='\0');
        System.out.println(strings[0]);
        // 数组无论是在定义为实例变量还是局部变量，若没有初始化，都会被自动初始化
        int a[] =new int[7];
        String[] s = new String[8];
        System.out.println(a[0]);
        System.out.println(s[0]);


        // https://www.nowcoder.com/questionTerminal/8d53f414b58b4c3cbc3e20c13facbeca
        float f1[][] = new float[6][6];
        float []f2[] = new float[6][6];
        float [][]f5 = new float[6][];
        float [][]f4 = new float[6][6];

        // float f3[][] = new float[][6];
        float f6[][] = new float[6][];
        float []f7[] = new float[6][];
    }
}

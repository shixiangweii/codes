package interview.operator.demo3;

import org.junit.Test;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-03-02
 * Time: 12:29
 * https://www.nowcoder.com/questionTerminal/7e44c9a4f2b2438c9ede557f7964162f
 *
 * @author shixiangweii
 */
public class TestDemo {
    @Test
    public void test() {
        int a = Integer.MAX_VALUE;
        System.out.println(a << 1);
        System.out.println(a >> 1);
        // >>>为不带符号右移，右移后左边的空位被填充为0
        // 因为右移后，最左边空出，则填充1还是0，所以有，带符号位、不带符号位右移之分
        System.out.println(a >>> 1);
        // 而左移，右边总是填充0，所以不用考虑符号位的问题，所有没有“不带符号位左移”说法，因为不用考虑这种事情
        // System.out.println(a <<< 1);
    }

    /**
     * https://www.nowcoder.com/questionTerminal/fb15f051854544a29174eaac463b330a
     */
    @Test
    public void testInc() {
        int i = 5;
        // 6+7+6+5
        // 它加了括号了(什么都不用考虑了),
        System.out.println((i++) + (++i) + (i--) + (--i));
    }

    /**
     * https://www.nowcoder.com/questionTerminal/7e391239d19a4f37b702de1e41187b1b
     */
    @Test
    public void testChange() {
        float f = 42.0f;
        float f1[] = new float[2];
        float f2[] = new float[2];
        float[] f3 = f1;
        long x = 42;
        f1[0] = 42.0f;
        // false
        System.out.println(f1 == f2);


        // 数值间进行比较（二元操作）的时候，如果两边的数值类型不一样的话，则会向上转型。
        // （int ->> long ->> float ->> double、char ->> int 、byte ->> int、 short ->> int)
        // byte ,  short ,  int ,  long ,  fload ,  double
        // 作比较的时候会自动向上转型， 向下转型的时候则需要强转！
        // 低精度向高精度
        // float比long精度高
        // true
        System.out.println(x == f1[0]);

        // true
        System.out.println(f1 == f3);

        // System.out.println(f2==f1[1]);
    }

    /**
     * https://www.nowcoder.com/questionTerminal/ca7818b6a6734b10989883b0eb47c126
     */
    @Test
    public void testChange2() {
        short a = 128;
        byte b = (byte) a;
        // 128
        System.out.println(a);
        // -128
        System.out.println(b);
    }

    /**
     * https://www.nowcoder.com/questionTerminal/15b1429e6c364c3bbe6e8134a519f2a3
     * 链接：https://www.nowcoder.com/questionTerminal/15b1429e6c364c3bbe6e8134a519f2a3
     * 来源：牛客网
     * <p>
     * ------------知识点------------
     * Java表达式转型规则由低到高转换：
     * 1、所有的byte,short,char型的值将被提升为int型；
     * 2、如果有一个操作数是long型，计算结果是long型；
     * <p>
     * 3、如果有一个操作数是float型，计算结果是float型；
     * <p>
     * 4、如果有一个操作数是double型，计算结果是double型；
     * 5、被fianl修饰的变量不会自动改变类型，当2个final修饰相操作时，结果会根据左边变量的类型而转化。
     */
    @Test
    public void testAutoChangeFinal() {


        byte b1 = 1, b2 = 2, b3, b6, b8;
        final byte b4 = 4, b5 = 6, b7;
        // b3=(b1+b2);自动转为int，所以正确写法为b3=(byte)(b1+b2);或者将b3定义为int
        //b3=(b1+b2);  /*语句1*/

        // b6=b4+b5;b4、b5为final类型，不会自动提升，所以和的类型视左边变量类型而定，即b6可以是任意数值类型
        // b4,b5都是final所以不会自动提升
        b6 = b4 + b5;    /*语句2*/
        int cc = b4 + b5;
        long dd = b4 + b5;
        double ddd = b4 + b5;

        // b8=(b1+b4);虽然b4不会自动提升，但b1仍会自动提升，所以结果需要强转，b8=(byte)(b1+b4);
        //b8=(b1+b4);  /*语句3*/

        // 同上，b2不是final会自动提升
        // 同时注意b7是final修饰，即只可赋值一次，便不可再改变
        //b7=(b2+b5);  /*语句4*/

        //System.out.println(b3+b6);

        byte a1 = 2, a2 = 4, a3;
        short s = 16;
        // short 转 byte需要强转
        // a2 = s;
        // 计算时候，自动提升为int
        // a3 = a1 * a2;

        // https://www.nowcoder.com/questionTerminal/e4fddf5ba5b042a2b457b4168a38e934
        System.out.println(100 % 3);
        // 1.0
        double x = 100 % 3.0;
        System.out.println(x);

        // https://www.nowcoder.com/questionTerminal/904af36af29f491fab5fa335fdb7dcf3
        double d = 5.3e12;
        //  float f=11.1;
        // int i=0.0;

        // 会报错，因为是包装类，3默认转为Integer，Integer无法自动转换为Double
        // Double oD=3;
        Double oD = 3.0;
        Integer oI = 1;


        // https://www.nowcoder.com/questionTerminal/a568949ffbfc4a20977a2dab786032dd
        int i = 5;
        int j = 10;
        // 计算机表示负数 补码
        // 取反只是一种操作
        // 补码下 减去1，和加上-1 的补码 一样
        System.out.println(i + ~j);
    }

    /**
     * https://www.nowcoder.com/questionTerminal/0886d19c7b6a4cbe8c52b2a31d748252
     */
    float func0() {
        byte i = 1;
        return i;
    }

   /* float func1() {
        int i = 1;
        // 缺少返回值
        return;
    }*/

    float func2() {
        short i = 2;
        return i;
    }

    float func3() {
        long i = 3;
        return i;
    }

/*    float func4() {
        double i = 4;
        // double比float精度高
        return i;
    }*/

    public static void main(String[] args) {
        int x = 0;
        int y = 0;
        int k = 0;
        for (int z = 0; z < 5; z++) {
            if ((++x > 2) && (++y > 2) && (k++ > 2)) {
                x++;
                ++y;
                k++;
            }
        }
        System.out.println(x + "" + y + "" + k);
    }
}

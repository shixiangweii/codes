package interview.order.demo4;

/**
 * https://www.nowcoder.com/questionTerminal/ab6eb06face84c4e81ab5bc6f0f7f258
 *
 * 把静态字段，类字段也看成一种静态代码块，静态块从上到下按顺序
 *
 * 加载，链接，初始化
 *
 * 验证准备解析
 *
 * 其实加载，验证的时候，就已经把Class字节信息装载了，
 * 自己不要有那种，初始化静态块的时候，不能调用构造函数的误区
 *
 * 静态变量按声明顺序执行，静态变量在静态块之前，所以会先执行静态变量，再执行静态块
 *
 * 执行顺序优先级：静态块>main()>构造块>构造方法
 * 静态块按照申明顺序执行，所以先执行publicstaticB t1 = newB();该语句创建对象，则又会调用构造块，输出构造块
 *
 */
public class B {

    public static B t1 = new B();
    public static B t2 = new B();

    {
        System.out.println("构造块");
    }

    static {
        System.out.println("静态块");
    }

    public static void main(String[] args) {
        B t = new B();
    }
}
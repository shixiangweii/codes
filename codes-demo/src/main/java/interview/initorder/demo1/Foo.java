package interview.initorder.demo1;

/**
 * https://www.nowcoder.com/questionTerminal/ab6eb06face84c4e81ab5bc6f0f7f258
 * <p>
 * “静态域”：
 * 分为静态变量，
 * 静态方法，
 * 静态块。
 * 这里面涉及到的是静态变量和静态块，当执行到静态域时，按照静态域的顺序加载
 * <p>
 * 静态域只初始化一次，按照顺序
 *
 * @author shixiangweii
 */
public class Foo {
    static {
        System.out.println("静态块1");
    }


    {
        System.out.println("构造块1");
    }

    private Foo() {
        System.out.println("B");
    }

    /**
     * 自己不要感觉神奇，觉得初始化静态字段的时候，感觉类都没有初始化完成，居然还能调用构造方法？！
     * 其实，构造方法还是方法罢了，只是有点特殊
     * 而且，加载——连接——初始化，其实初始化静态字段的时候，class文件早就加载完了，这里就是执行方法罢了
     */
    public static Foo t1 = new Foo();
    public static Foo t2 = new Foo();

    {
        System.out.println("构造块2");
    }

    static {
        System.out.println("静态块2");
    }

    public static void main(String[] args) {
        Foo t = new Foo();
    }
}
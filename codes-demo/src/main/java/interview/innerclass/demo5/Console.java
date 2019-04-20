package interview.innerclass.demo5;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-01-14
 * Time: 9:02
 *
 * @author shixiangweii
 */
public class Console {
    public static void main(String[] args) {
        // 静态内部类
        // Base.Sub
        Base.Sub sub = new Base.Sub();
        Base base = new Base();
        // 内部类
        Base.Sub2 sub2 = base.new Sub2();
    }
}

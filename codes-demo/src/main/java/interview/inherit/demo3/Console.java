package interview.inherit.demo3;

/**
 * Description:
 * <p>
 * https://www.nowcoder.com/questionTerminal/0514f9318d3547bbbd601128f1a85e63
 * <p>
 * 一道很贱的题目
 * User: shixiangweii
 * Date: 2019-01-25
 * Time: 9:49
 * <p>
 * this调用自身构造方法，显示调用一定是第一行
 * super调用父亲构造方法，显示调用一定是第一行
 * 所以this,super不能都显示存在
 *
 * @author shixiangweii
 */
public class Console {
}

class Human {

    Human(String name) {

    }
}

class Man extends Human {

    public Man() {
        // 但是如果显示调用构造函数，一定是第一行
        this("");
        // super语句必须是构造方法内部，第一行
        // super("");
        System.out.println();
    }

    private Man(String text) {
        super("");
    }
}


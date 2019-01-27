package hyuki.design.bridge.displaydemo.user;

import hyuki.design.bridge.displaydemo.api.Display;
import hyuki.design.bridge.displaydemo.api.extender.CountDisplay;
import hyuki.design.bridge.displaydemo.api.impler.api.impl.StringDisplayImpl;

/**
 * Description: 用户，使用者
 * User: shixiangweii
 * Date: 2019-01-26
 * Time: 20:47
 * <p>
 * 设计模式注重的是，逻辑模型，设计
 * 或许仅仅从具体的编码方式来看，模式之间很类似，基本都是依赖接口，抽象类，持有字段，
 * 类组织方式含有其他类
 * 如果只是从物理编码上去理解，那完了。。。
 * <p>
 * 其实可以从：
 *
 * 使用接口的人,调用方   Main
 * 提供方
 *    具体实现的人   impler
 *    扩展功能的人   extender
 * <p>
 * 来看代码，或者从一种，团队协作开发，不同公司人员开发的角度去理解设计模式，
 * 而不是还是以个人的视角去看待OOP，AOP
 *
 * @author shixiangweii
 */
public class Main {
    /**
     * 从底层的框架来看，OOP，可以用来封装各种基本编程组件，
     * 从业务系统角度来看，自己要学会用OOP的思想，以基础模块，基础子系统，去设计类，
     * 例如，评论子系统/模块
     * 其实都是用OOP的思想，但是具体的编码实现的思考单位不一样
     * @param args
     */
    public static void main(String[] args) {
        Display d1 = new Display(new StringDisplayImpl("Hello, China."));
        Display d2 = new CountDisplay(new StringDisplayImpl("Hello, World."));
        CountDisplay d3 = new CountDisplay(new StringDisplayImpl("Hello, Universe."));
        d1.display();
        d2.display();
        d3.multiDisplay(5);
    }
}

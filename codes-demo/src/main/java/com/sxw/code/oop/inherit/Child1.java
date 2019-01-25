package com.sxw.code.oop.inherit;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-01-24
 * Time: 16:06
 *
 * https://www.nowcoder.com/questionTerminal/9c32fe6e1dd545e5af6076b71b42f4d3
 *
 * 链接：https://www.nowcoder.com/questionTerminal/9c32fe6e1dd545e5af6076b71b42f4d3
 来源：牛客网

 接口可以多继承可以被多实现，因为接口中的方法都是抽象的，这些方法都被实现的类所实现，即使多个父接口中有同名的方法，
 在调用这些方法时调用的时子类的中被实现的方法，不存在歧义；同时，接口的中只有静态的常量，
 但是由于静态变量是在编译期决定调用关系的，即使存在一定的冲突也会在编译时提示出错；
 而引用静态变量一般直接使用类名或接口名，从而避免产生歧义，因此也不存在多继承的第一个缺点。
 对于一个接口继承多个父接口的情况也一样不存在这些缺点。所以接口可以多继承
 *
 * @author shixiangweii
 */
public interface Child1 extends Parent1, Parent2 {

}

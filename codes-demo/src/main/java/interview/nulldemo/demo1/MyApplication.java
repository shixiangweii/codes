package interview.nulldemo.demo1;

class Test {
    public static void hello() {
        System.out.println("hello");
    }
}

/**
 * https://www.nowcoder.com/questionTerminal/733630b017f74bf3bcf54dc8a82dc3cf
 *
 * 链接：https://www.nowcoder.com/questionTerminal/733630b017f74bf3bcf54dc8a82dc3cf
 来源：牛客网

 A就相当于Test.hello()
 值得一说的是有些人以为是空指针，这里你们所说的空指针必须是去引用堆对象才会有空指针
 而这个hello是static类型的，人家static的方法本身就没有指针，所以当然不会有空指针
 */
public class MyApplication {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Test test = null;
        test.hello();
    }
}
package com.sxw.code.reflect.schema;

import com.sxw.code.reflect.schema.entity.GovApp;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author shixi
 * https://blog.csdn.net/qq_21808961/article/details/80391029
 */
public class Main {

    static public void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<GovApp> govAppClass = GovApp.class;
        // 获取所有public field，-> 父类public的字段+本身public的字段
        Field[] fields = govAppClass.getFields();
        // 打印结果：只有 "uuid"
        Arrays.stream(fields).forEach(field -> {
            System.out.println(field.getName());
        });
        // 获取自身声明的field，不限修饰符
        fields = govAppClass.getDeclaredFields();
        // 打印结果：
        // url
        // unify
        Arrays.stream(fields).forEach(field -> {
            System.out.println(field.getName());
        });

        Method[] methods = govAppClass.getMethods();
        // 所有public的method， 所有父类public方法+本身public方法
        // 而Object默认是所有类父类，所以实际结果：
        // Object中public方法 + 父类public方法 + 本身public方法
        // public methods of this class
        // 就比如，你看上面的英文好像以为看懂了，类的public方法，但是不实际运行，你能提前料想到，还会输出父类的public方法，Object的public方法
        // 其实很多问题就是这样的，看别人的总结，看别人的抽象定义，好像字面上都看懂了，但是实际上，自己的认知水平还有盲点
        // 需要依靠实际操作，也就是工程效率来弥补，具体这个点，怎么去定义“public methods of this class”
        // 什么叫做“类的public方法”，是类本身，父类的算吗
        Arrays.stream(methods).forEach(method -> {
            System.out.println(method.getName());
        });
        // all the declared methods of this class
        // 光看定义，这个类声明的方法，你能想到，只要是这个类的，不管private,public,default,protected修饰的这个层面吗？
        // 还是那个问题，光看文字表面“all the declared methods of this class”，字面意思能“懂”
        // 但是，这个字面的背后包含的东西，你根本不知道
        // 而或许一些天才，聪明的人，就在于，他真的能理解这字面背后的东西，而不需要依靠实践，依靠工程效率
        // 比如，在他脑子里对“all the declared methods of this class”，
        // 他可能不需要运行、试验，就能联想到，需要是这个类声明的，不管private什么的修饰符
        methods = govAppClass.getDeclaredMethods();
        // 只有自身声明的方法
        Arrays.stream(methods).forEach(method -> {
            System.out.println(method.getName());
        });
        // public访问权限的构造器
        Constructor<?>[] constructors = govAppClass.getConstructors();
        Arrays.stream(constructors).forEach(constructor -> {
            System.out.println(constructor.getName());
        });

        constructors = govAppClass.getDeclaredConstructors();
        Arrays.stream(constructors).forEach(constructor -> {
            System.out.println(constructor.getName());
        });
        GovApp govApp = new GovApp();
        Field unify = govAppClass.getDeclaredField("unify");
        unify.setAccessible(true);
        // 只能作用，值类型字段
        unify.setInt(govApp, 1);
        Field url = govAppClass.getDeclaredField("url");
        url.setAccessible(true);
        Method setUrl = govAppClass.getDeclaredMethod("setUrl", String.class);
        setUrl.invoke(govApp, "123456");
        System.out.println(govApp);
    }
}

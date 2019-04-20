package interview.innerclass.demo2;

/**
 * https://www.nowcoder.com/questionTerminal/48524c47dd924887be6684b17175fa40
 */
public class OuterClass {
    private float f = 1.0f;
    //插入代码到这里
    private static float ff = 0f;

  static  class InnerClass1 {

 public  float func2() {
     System.out.println(ff);
          return 0f;
          //return f;
      }

        public static float func() {

            return 0f;
        }

  }


  /*  非静态内部类，不能有静态方法
     class InnerClass7 {

          public static float func() {

              return 0f;
          }*/

/*
内部类不是用static关键字修饰的内部类怎不能用static方法

静态内部类才可以声明静态方法

    class InnerClass1 {

        public static float func() {
        // 静态方法，不能引用实例字段
            return f;
        }
    }*/

/*    abstract class InnerClass2 {
// 抽象方法签名不对 抽象方法不可以有函数体
        public abstract float func() {
        }
    }*/

/*    static class InnerClass3 {
        protected static float func() {
        // 静态方法不可以使用非静态变量
            return f;
        }
    }*/
/*
内部类不是用static关键字修饰的内部类怎不能用static方法
静态内部类才可以声明静态方法
    public class InnerClass4 {
        static float func() {
         // 静态方法，不能引用实例字段
            return f;
        }
    }*/
}
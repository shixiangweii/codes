package com.sxw.code.oop.innerclass.demo3;

public class OutClass {

    public void display(final String name,String age){
        class InnerClass2 {
            void display(){
                System.out.println(name);
            }
        }
    }
        class InnerClass {
            void display(){

            }
        }

    public InnerClass getInnerClass(final int age, final String name) {
        return new InnerClass() {
            // 匿名类，是innerclass的子类
            int age_;
            String name_;
            int c_;

            //构造代码块完成初始化工作
            {
                if (0 < age && age < 200) {
                    age_ = age;
                    name_ = name;
                }
            }

            public String getName() {
                return name_;
            }

            public int getAge() {
                return age_;
            }
        };
    }

    public static void main(String[] args) {
        OutClass out = new OutClass();

        InnerClass inner_1 = out.getInnerClass(201, "chenssy");
   //     System.out.println(inner_1.getName());

        InnerClass inner_2 = out.getInnerClass(23, "chenssy");
     //   System.out.println(inner_2.getName());
    }
}
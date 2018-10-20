package com.sxw.code.serial;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-10-19
 * Time: 11:35
 *
 * @author shixiangweii
 */
public class SerialDemo {
    public static void main(String[] args) throws Exception {
        Foo obj = new Foo();
        Goo goo = new Goo();
        goo.setStr("123");
        obj.setGoo(goo);

        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("result.obj"));
        out.writeObject(obj);
        out.close();

        obj.setDesc("java coder");
        obj.setName(9527);
        goo.setStr("321");

        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(
                "result.obj"));
        Foo t = (Foo) oin.readObject();
        oin.close();

        System.out.println(t == obj);
        System.out.println(t.getGoo() ==  goo);

        System.out.println(t);
    }
}

package com.sxw.test.basejava.TestEqual;

public class Test {
    public static void main(String[] args) {
        Object o = new Object() {
            public boolean equals(Object obj) {
                return true;
            }
        };
        System.out.println(o.equals("Fred"));
    }
}
package com.sxw.spring.spel.ongl;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * https://www.jianshu.com/p/5537b2c86acd
 *
 *
 * @author shixi
 */
public class ObjDemo {

    public static void main(String[] args) {
        //初始化对象
        Account account = new Account("Deniro");
        account.setFootballCount(10);
        account.addFriend(new Friend("Jack"));

        //解析器
        ExpressionParser parser
                = new SpelExpressionParser();
        //解析上下文
        EvaluationContext context = new StandardEvaluationContext(account);

        //获取不同类型的属性
        String name = (String) parser.parseExpression("Name").getValue(context);
        System.out.println(name);
        int count = (Integer) parser.parseExpression("footballCount+1").getValue(context);
        System.out.println(count);

        //获取嵌套类中的属性
        String friend = (String) parser.parseExpression("friend.name").getValue(context);
        System.out.println(friend);
    }
}

class Account {
    private int footballCount;
    private Friend friend;
    private String name;

    Account(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    void addFriend(Friend friend) {
        this.friend = friend;
    }

    public int getFootballCount() {
        return footballCount;
    }

    void setFootballCount(int footballCount) {
        this.footballCount = footballCount;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }
}

class Friend {
    private String name;

    Friend(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
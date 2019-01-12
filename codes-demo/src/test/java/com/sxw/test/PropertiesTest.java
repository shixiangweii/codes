package com.sxw.test;

import org.junit.Test;

import java.util.Properties;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-01-12
 * Time: 18:53
 *
 * @author shixiangweii
 */
public class PropertiesTest {
    /**
     * 因为继承了hashTable Properties extends Hashtable<Object,Object>
     * 所以线程安全
     */
    @Test
    public void testProperties() {
        Properties props = new Properties();
        props.setProperty("092112116", "shixiangweii");
        System.out.println(props);
    }
}

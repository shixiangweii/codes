package com.sxw.commonpool2;

import org.apache.commons.pool2.impl.GenericObjectPool;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author shixiangweii
 */
public class StringBuilderDemo {

    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("123456789");

        ReaderUtil1 readerUtil1 = new ReaderUtil1();
        String s = readerUtil1.readToString(reader);
        System.out.println(s);

        ReaderUtil readerUtil = new ReaderUtil(new GenericObjectPool<StringBuffer>(new StringBufferFactory()));

        reader = new StringReader("123456789");
        // Stream closed
        s = readerUtil.readToString(reader);

        System.out.println(s);
    }
}

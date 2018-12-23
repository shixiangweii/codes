package com.sxw.code.classloader.chaofanwei2.server;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Date;

/**
 * Description: 继承自URLClassLoader，从指定的url加载class
 * User: shixiangweii
 * Date: 2018-12-23
 * Time: 15:24
 *
 * @author shixiangweii
 */
public class MyClassLoader extends URLClassLoader {
    private MyClassLoader loader = null;
    Date startDate = new Date();

    public MyClassLoader(URL[] urls) {
        //super(urls, Thread.currentThread().getContextClassLoader());
        super(urls);
    }

    public MyClassLoader(ClassLoader parent) {
        super(new URL[0], parent);
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    /*public void addJarFile(String jarfile) throws MalformedURLException {
        URL url = new URL("file:" + jarfile);
        addURL(url);
    }

    public void addDir(String path) throws MalformedURLException {
        path = "file:" + path;
        URL url = new URL(path);
        addURL(url);
    }*/

    @Override
    public String toString() {
        return super.toString() + ",time:" + startDate.toLocaleString();
    }
}

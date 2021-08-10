package org.zhiyi.coursecode.week1.classloader;

import sun.misc.Launcher;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class JvmClassLoaderPrintPath {

    public static void main(String[] args) {

        // 启动类加载器加载内容
        System.out.println("BootstrapClassloader :");
        URL[] bootStrapUrls = Launcher.getBootstrapClassPath().getURLs();
        Arrays.stream(bootStrapUrls).map(bootStrapUrl -> "==>" + bootStrapUrl).forEach(System.out::println);

        System.out.println("ExtClassloader :");
        // 获取当前类的应用类加载器的父加载器，注意是父加载器不是父类！ -> 拓展类加载器
        Object o = JvmClassLoaderPrintPath.class.getClassLoader().getParent();
//        System.out.println(o.getClass());
        URLClassLoader extClassLoader = (URLClassLoader) o;
        URL[] extUrls = extClassLoader.getURLs();
        Arrays.stream(extUrls).map(extUrl -> "==>" + extUrl).forEach(System.out::println);

        System.out.println("ApplicationClassloader :");
        // 获取应用类加载器
        URLClassLoader applicationUrls = (URLClassLoader) JvmClassLoaderPrintPath.class.getClassLoader();
        URL[] appUrls = applicationUrls.getURLs();
        Arrays.stream(appUrls).map(appUrl -> "==>" + appUrl).forEach(System.out::println);

    }
}

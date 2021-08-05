package org.zhiyi.coursecode.week1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SpringBootApplication
public class Week1Application {

    public static void main(String[] args) {
        try {
            Class clazz = new MyClassLoader().loadClass("Hello.xlass");
            if (clazz == null) {
                throw new ClassNotFoundException();
            }
            Method method = clazz.getDeclaredMethod("hello");
            Object helloObject = clazz.newInstance();
            method.invoke(helloObject);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        SpringApplication.run(Week1Application.class, args);
    }

}

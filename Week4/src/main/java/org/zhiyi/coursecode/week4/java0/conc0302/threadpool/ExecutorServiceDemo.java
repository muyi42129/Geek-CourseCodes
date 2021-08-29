
package org.zhiyi.coursecode.week4.java0.conc0302.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ExecutorServiceDemo {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(16);
        try {
            String str = executorService.submit(
                    () -> "I am a task, which submitted by the so called laoda, and run by those anonymous workers").get();

            System.out.println("str=" + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

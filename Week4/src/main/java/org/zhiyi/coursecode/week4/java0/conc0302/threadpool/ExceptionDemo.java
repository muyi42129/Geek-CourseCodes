package org.zhiyi.coursecode.week4.java0.conc0302.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExceptionDemo {
    
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
    
        try {
            Future<Double> future = executorService.submit(() -> {
                throw new RuntimeException("executorService.submit()");
            });
    
            double b = future.get();
            System.out.println(b);
            
        } catch (Exception ex) {
            // future.get() 会将线程执行期间捕获的异常抛出 get() 调用者
            System.out.println("catch submit");
            ex.printStackTrace();
        }

        try {
            executorService.execute(() -> {
                  throw new RuntimeException("executorService.execute()");
                });
        } catch (Exception ex) {
            System.out.println("catch execute");
            ex.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("Main Thread End!");
    }
    
}

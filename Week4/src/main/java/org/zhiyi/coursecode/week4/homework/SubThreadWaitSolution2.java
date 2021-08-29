package org.zhiyi.coursecode.week4.homework;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 2. 使用Callable Future + ExecutorService
 *
 */
public class SubThreadWaitSolution2 {

    public static void main(String[] args) throws Exception {
        Callable<String> callable = (Callable) () -> "solution 2 ";
        // 将Callable 封装成 RunnableFuture
        Future future = Executors.newCachedThreadPool().submit(callable);
        System.out.println(future.get());
    }
}

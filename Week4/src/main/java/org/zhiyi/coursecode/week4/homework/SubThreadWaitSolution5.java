package org.zhiyi.coursecode.week4.homework;

import java.util.concurrent.CompletableFuture;

/**
 * 5. 使用CompletableFuture开启异步任务线程，同步结果
 *
 */
public class SubThreadWaitSolution5 {
    //TODO CompletableFuture 目前使用还不太熟悉，需要进一步掌握 2021-08-29

    public static void main(String[] args) throws Exception {
        // 默认使用ForkJoinPool 可以修改线程池
        System.out.println(CompletableFuture.supplyAsync(() -> {
            System.out.println("子线程： " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "solution 5";
        })
//                .thenApply((a) -> "上一步执行结果: " + a)
                .join());

    }
}

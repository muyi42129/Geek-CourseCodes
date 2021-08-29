package org.zhiyi.coursecode.week4.homework;

import java.util.concurrent.FutureTask;

/**
 * 3. 使用FutureTask(Callable<V> callable) 作为子线程任务对象，通过get()阻塞Main线程，同步获取异步处理结果。
 */
public class SubThreadWaitSolution3 {

    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            Thread.sleep(2000);
            return "solution 3";
        });
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}

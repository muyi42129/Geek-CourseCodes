package org.zhiyi.coursecode.week4.homework;

import java.util.concurrent.FutureTask;

/**
 * 4. 使用FutureTask(Runnable runnable, V result)
 * 注意这个构造器通过 Executors.callable(runnable, result);
 * 将runnable 和 result 关联起来，但实际上两者没有联系
 * 故，为了取到 Runnable 中的运算结果，设置全局变量保存
 *
 * 注意与3.  {@link SubThreadWaitSolution3} 的区别；
 */
public class SubThreadWaitSolution4 {

    public static String result;
    public static void main(String[] args) throws Exception {
        FutureTask futureTask = new FutureTask(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SubThreadWaitSolution4.result = "solution 4";
        }, null);
        new Thread(futureTask).start();
        System.out.println(futureTask.get()); // null 目的是阻塞main线程
        System.out.println(result);
    }
}

package org.zhiyi.coursecode.week4.homework;

/**
 * 1. 使用Thread.join() + 全局变量
 *
 */
public class SubThreadWaitSolution1 {

    public static String result;
    public static void main(String[] args) throws InterruptedException {

        Thread subThread = new Thread(() -> {
            SubThreadWaitSolution1.result = "solution 1";
        }, "subThread");

        subThread.start();
        subThread.join();
        System.out.println(result);
    }

}

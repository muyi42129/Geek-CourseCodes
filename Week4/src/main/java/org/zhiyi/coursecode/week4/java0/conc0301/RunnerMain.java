
package org.zhiyi.coursecode.week4.java0.conc0301;

import java.io.IOException;

public class RunnerMain {

    public static void main(String[] args) throws IOException, InterruptedException {

        Runner1 runner1 = new Runner1();
        Thread thread1 = new Thread(runner1, "th-1");

        Runner2 runner2 = new Runner2();
        Thread thread2 = new Thread(runner2, "th-2");

        thread1.start();
        thread2.start();

        thread2.interrupt();  // i = true

        Thread.sleep(2000); // 意在让th-1, th-2 均在main方法执行完毕前执行任务完毕
        // 这里会发现，即使th-2在Runnable状态时被中断，当其Terminated后，被中断的状态将被重置。
        System.out.println("after 2000 ms th-2, state : " + thread2.getState()+"; isInterrupted(): "+thread2.isInterrupted());
        System.out.println(Thread.activeCount());

        // 打印当前线程组信息
        Thread.currentThread().getThreadGroup().list();
        // 打印当前线程所属线程组的父线程组中活动线程组个数
        System.out.println(Thread.currentThread().getThreadGroup().getParent().activeGroupCount());
        // 打印当前线程所属线程组的父线程组信息（树形，包含当前线程所属的线程组）
        Thread.currentThread().getThreadGroup().getParent().list();
    
        
    }
}

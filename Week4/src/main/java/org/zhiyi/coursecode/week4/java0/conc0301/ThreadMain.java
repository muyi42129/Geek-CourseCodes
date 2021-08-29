package org.zhiyi.coursecode.week4.java0.conc0301;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadMain {

    /**
     * A : 继承Thread #run()
     * B : 实现Runnable #run()
     * C : 实现Callable #call()
     *
     * 三个线程实现任务方法中均sleep(500);
     * @param args
     */
    public static void main(String[] args) {

        ThreadA threadA = new ThreadA();
        threadA.start();
        System.out.println("这是主线程：");

        ThreadB threadB = new ThreadB();
        new Thread(threadB).start();
        System.out.println("这是主线程：");

        ThreadC threadC = new ThreadC();
        /** 实现 RunnableFuture ==> 继承 Runnable + Future
         * 1. FutureTask 构造时需要一个 Callable<T> 对象或者 Runnable + <T> （内部构造成Callable<T>）
         * 2. 当 FutureTask 任务被某个线程执行，即实现Runnable能力，调用run()，实则调用Callable.call() 业务方法
         * 3. 当 FutureTask.get() 被执行时，将调用get()的线程阻塞直至call()方法返回。 具体的实现是 Treiber stack链表，无锁CAS自旋维护获取异步任务结果的线程
         * 4. 当 Callable.call() 业务方法执行完毕，对链表中被blocked线程 unblock 从而使其拿到异步任务结果，包括任务被取消、线程被中断等。
         *
         * 通过以上4点简单分析，可以看出：对于FutureTask任务，只有调用了Future.get()方法，阻塞才存在。
         */
        FutureTask<String> futureTask = new FutureTask<>(threadC);
        new Thread(futureTask).start();
        System.out.println("这是主线程:begin!");
        try {
            // 注意，futureTask.get() 将阻塞当前线程，直到得到异步任务返回值。无参数方法将
            System.out.println("得到的返回结果是:" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}

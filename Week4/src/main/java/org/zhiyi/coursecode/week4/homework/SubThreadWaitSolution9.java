package org.zhiyi.coursecode.week4.homework;

/**
 * 9. 使用 synchronized wait() notify()
 * main thread 和 sub thread 锁的对象相同，使用wait(), notify() 协调两线程任务处理
 */
public class SubThreadWaitSolution9 {

    static String result;

    public static void main(String[] args) throws InterruptedException {

        Object lock = new Object();
        // 在同步块中开启新线程，然后wait() 释放锁，等待其他持有锁的线程notify()
        synchronized (lock) {
            new Thread(new RunnableTask(lock)).start();
            lock.wait();
            System.out.println(result);
        }
    }

    static class RunnableTask implements Runnable {
        Object lock;

        public RunnableTask(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                try {
                    // pretend to compute
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                result = "solution 9";
                lock.notify();
            }
        }

    }

}

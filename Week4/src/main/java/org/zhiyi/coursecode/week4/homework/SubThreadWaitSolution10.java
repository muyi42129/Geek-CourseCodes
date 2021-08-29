package org.zhiyi.coursecode.week4.homework;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 10. 使用 ReentrantLock.Condition 协调 main thread 和 sub thread 持有、释放锁。
 */
public class SubThreadWaitSolution10 {

    static String result;

    public static void main(String[] args) {
        Lock reentrantLock = new ReentrantLock();
        Condition notComplete = reentrantLock.newCondition();
        try {
            reentrantLock.lock();
            new Thread(new RunnableTask<>(reentrantLock, notComplete)).start();
            // 释放锁，同时进入 WAIT 等待再次获取锁。
            notComplete.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        System.out.println(result);

    }

    static class RunnableTask<L extends Lock, T extends Condition> implements Runnable {

        L lock;
        T condition;

        public RunnableTask(L lock, T condition) {
            this.lock = lock;
            this.condition = condition;
        }

        @Override
        public void run() {
            try {
                lock.lock();
                // pretend to compute
                Thread.sleep(2000);
                result = "solution 10";
                // Wakes up one waiting thread.
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

}

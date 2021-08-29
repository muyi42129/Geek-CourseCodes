package org.zhiyi.coursecode.week4.homework;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * 11. 使用同步数据结构实现
 * ArrayBlockingQueue take();
 */
public class SubThreadWaitSolution11 {

    static ArrayBlockingQueue<String> queue;

    public static void main(String[] args) throws InterruptedException {

        queue = new ArrayBlockingQueue<>(1);
        new Thread(new RunnableTask(queue)).start();
        System.out.println(queue.take());
    }

    static class RunnableTask implements Runnable {

        ArrayBlockingQueue<String> queue;

        public RunnableTask(ArrayBlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                // pretend to compute
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queue.offer("solution 11");
        }
    }

}

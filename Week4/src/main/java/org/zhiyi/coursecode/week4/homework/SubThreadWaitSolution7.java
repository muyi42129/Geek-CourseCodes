package org.zhiyi.coursecode.week4.homework;

import java.util.concurrent.CountDownLatch;

/**
 * 7. Use a CountDownLatch(1).
 * Main thread WAIT until subThread countDown();
 *
 */
public class SubThreadWaitSolution7 {

    static String result;

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new RunnableTask(countDownLatch)).start();
        countDownLatch.await();
        System.out.println(result);
    }

    static class RunnableTask implements Runnable {

        CountDownLatch latch;

        public RunnableTask(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                // pretend to compute
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = "solution 7";
            latch.countDown();
        }
    }

}

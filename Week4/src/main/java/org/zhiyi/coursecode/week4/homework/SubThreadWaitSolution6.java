package org.zhiyi.coursecode.week4.homework;

import java.util.concurrent.Semaphore;

/**
 * 6. Use Semaphore(0).
 * Main thread can't get one permit and going to WAIT until subThread release(1);
 *
 */
public class SubThreadWaitSolution6 {

    static String result;

    public static void main(String[] args) throws Exception {
        Semaphore semaphore = new Semaphore(0);
        new Thread(new RunnableTask(semaphore)).start();
        semaphore.acquire(); // acquire(1)
        System.out.println(result);
    }

    static class RunnableTask implements Runnable{
        Semaphore semaphore;

        public RunnableTask(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                // pretend to compute
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = "solution 6";
            semaphore.release(); // release(1)
        }
    }

}

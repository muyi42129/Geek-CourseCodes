package org.zhiyi.coursecode.week4.homework;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 8. 使用 CyclicBarrier(2);
 * main thread 和 sub thread 均 await();
 *
 * 注意：这里的使用场景不太准确，cyclicBarrier 是可复用的，因此一般在执行循环调度任务中使用。
 */
public class SubThreadWaitSolution8 {

    static String result;

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            // 回调函数，当所有子任务到达barrier后，在其中一个线程内执行
            System.out.println(Thread.currentThread().getName() + " CyclicBarrier Runnable result : " + result);
        });
        new Thread(new RunnableTask(cyclicBarrier)).start();
        // 主线程等待其他子线程到达barrier
        System.out.println("main await() return : " + cyclicBarrier.await());
        System.out.println(result);
    }

    static class RunnableTask implements Runnable {

        CyclicBarrier cyclicBarrier;

        public RunnableTask(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                // pretend to compute
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = "solution 8";
            try {
                // 子线程等待其他子线程到达barrier
                System.out.println("sub await() return : " + cyclicBarrier.await());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}

package org.zhiyi.coursecode.week4.java0.conc0301.op;

public class WaitAndNotify {

    public static void main(String[] args) {

        /**
         * 生产者 - 消费者模型
         * 使用wait() notify() 实现货仓供需平衡。
         * 注意
         * 1.product() customer() 均为 MethodClass类 synchronized 非静态方法，则同一实例调用两个方法竞争的锁是相同的。
         * 2.若启动2个消费者 t2 t3, 持有锁的消费者t2 notifyAll()后调用wait()释放锁，t3 和 t1 均有可能获取到锁，若消费者获取到锁则没意义，只能继续调用wait()释放锁，等待生产者生产。
         * 所以这里也体现了 wait() 和 notify() 使用场景的局限性，当存在多个消费者时会存在浪费cpu资源的场景。
         */
        MethodClass methodClass = new MethodClass();
        Thread t1 = new Thread(() -> {
            try {
                methodClass.product();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            try {
                methodClass.customer();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "t2");
        Thread t3 = new Thread(() -> {
            try {
                methodClass.customer();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "t3");
        t1.start();
        t2.start();
        t3.start();

    }


}

class MethodClass {
    // 定义生产最大量
    private final int MAX_COUNT = 20;

    int productCount = 0;

    public synchronized void product() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":::run:::" + productCount);
            Thread.sleep(10);
            if (productCount >= MAX_COUNT) {
                System.out.println("货舱已满,,.不必再生产");

                wait();
            }else {
                productCount++;
            }

            notifyAll();
        }
    }

    public synchronized void customer() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":::run:::" + productCount);
            Thread.sleep(10);
            if (productCount <= 0) {
                System.out.println("货舱已无货...无法消费");
                wait();
            }else {
                productCount--;
            }

            notifyAll();
        }
    }
}

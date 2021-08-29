
package org.zhiyi.coursecode.week4.java0.conc0301.sync;

public class Thread1 implements Runnable {

    @Override
    public void run() {
        /**
         * 锁当前实例，
         * synchronized （this)
         * 1.仅能在非静态方法中使用，this为调用当前方法的实例对象，所以会有一下两点特征；
         * 2.相同实例调用此方法竞争同意一把锁，并发调用会产生锁等待；
         * 3.不同实例调用时，竞争的锁不同，不会产生锁等待。
         *
         * 本例中，两个线程 ta tb 初始化使用的 Runnable 对象均为 t1, 因此会出现锁等待 --两线程先后执行run();
         * 如果两个线程的 Runnable 实例不同，则调用此段代码块竞争的锁不同，不会出现锁等待 --两线程交替执行run();
         */
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
            }
            System.out.println(Thread.currentThread().getName() + " loop end ");
        }
    }

    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread ta = new Thread(t1, "A");
        Thread tb = new Thread(t1, "B");
        ta.start();
        tb.start();
    }
}

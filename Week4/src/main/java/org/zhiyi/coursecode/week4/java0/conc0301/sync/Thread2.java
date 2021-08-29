
package org.zhiyi.coursecode.week4.java0.conc0301.sync;

public class Thread2 {

    /**
     * 本例与org\zhiyi\coursecode\week4\java0\conc0301\sync\Thread1.java 类似，
     * 比较synchronized (this) 与 synchronized 修饰方法（非静态）时竞争的锁对象。
     * 对于 synchronized 修饰的方法，
     * 1）若为静态方法，则竞争的锁对象是Class对象，Class对象的实例只有1个，在JVM加载过程中从.class常量池加载至方法区（元数据区），ClassLoader 类加载特点之一：缓存加载
     * 2）若为非静态方法，则竞争的锁对象是调用该实例方法的实例对象，即同一对象调用此方法竞争的锁相同，不同对象调用此方法竞争的锁不同；
     *
     * 本例中，
     * m4t1() 使用 synchronized (this) 则锁的对象是调用该方法的实例对象；
     * m4t2() 使用 synchronized 修饰实例方法，则锁的对象是调用该方法的实例对象，同上。
     *
     * 故，
     * 若调用两方法的实例对象为同一对象，则两方法串行执行；
     * 若调用两方法的实例对象为不同对象，则两方法交替执行。
     * 本例串行执行。 修改为不会互相阻塞也很简单：
     * 1）从锁的对象出发，将m4t2()修改为static方法即可。
     * 2）从竞争锁的实例出发，将两线程run()中调用方法的实例修改为不同即可。
     * */

    public void m4t1() {
        synchronized (this) {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
            }
        }
    }

    public synchronized void m4t2() {
        int i = 5;
        while (i-- > 0) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
            }
        }
    }

    public static void main(String[] args) {
        final Thread2 myt2 = new Thread2();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                myt2.m4t1();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                myt2.m4t2();
            }
        }, "t2");
        t2.start();
        t1.start();
    }

}

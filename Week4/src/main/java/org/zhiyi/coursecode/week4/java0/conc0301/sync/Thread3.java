
package org.zhiyi.coursecode.week4.java0.conc0301.sync;

public class Thread3 {
    class Inner {
        private void m4t1() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : Inner.m4t1()=" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
            }
        }

        private void m4t2() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : Inner.m4t2()=" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
            }
        }
    }

    private void m4t1(Inner inner) {
        /**
         * 当 synchronized (obj) 时，线程竞争的锁取决于 obj
         * 即，obj 相同时线程会互相阻塞； obj 不同时线程竞争的锁不同，不会互相阻塞。
         * 所以当使用 对象锁 时，应该明确需要同步的场景，尽可能地降低锁的粒度。
         */
        synchronized (inner) { //使用对象锁
            inner.m4t1();
        }
    }

    private void m4t2(Inner inner) {
        inner.m4t2();
    }

    public static void main(String[] args) {
        final Thread3 myt3 = new Thread3();
        final Inner inner = myt3.new Inner();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                myt3.m4t1(inner);
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                myt3.m4t2(inner);
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}

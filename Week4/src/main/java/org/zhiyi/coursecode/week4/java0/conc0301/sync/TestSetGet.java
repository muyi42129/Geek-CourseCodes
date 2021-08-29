package org.zhiyi.coursecode.week4.java0.conc0301.sync;

public class TestSetGet {

    public static void main(String[] args) throws Exception {

        /**
         * get() set() 均为SetGet类中非静态 synchronized 方法，案例中调用 get() 和 set() 的实例都是 s
         * 即相同实例调用两个synchronized实例方法竞争的锁是同一把，这一点很重要，如果使用不同实例进行方法调用则不会出现锁等待。
         *
         * 分析：
         * 开启新线程 get() --sleep 10s 当前线程 set() --sleep 1s
         * 1.set() 执行完毕后 get() 获取到锁开始执行
         * 2.get() 线程任务执行完毕前，主线程已退出； 若要主线在t1线程后退出也很简单： t.join();
         */
        final SetGet s = new SetGet();
        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    s.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        long start = System.currentTimeMillis();
//        final SetGet s1 = new SetGet();
        s.set(10);
//        t.join();
        System.out.println(" 耗时： " + ( System.currentTimeMillis() - start ));

    }


    public static class SetGet {

        int a = 0;
        public synchronized void set(int v) throws Exception {
            System.out.println(Thread.currentThread().getName() +" setting " +v);
            Thread.sleep(5000);
            a = v;
            System.out.println(Thread.currentThread().getName() +" set " +v);
        }

        public synchronized int get() throws Exception {
            System.out.println(Thread.currentThread().getName() +" getting ");
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName() + " get ");
            return a;
        }

    }
}

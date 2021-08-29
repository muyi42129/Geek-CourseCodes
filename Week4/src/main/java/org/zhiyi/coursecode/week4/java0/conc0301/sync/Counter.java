package org.zhiyi.coursecode.week4.java0.conc0301.sync;

public class Counter {
    
    private int sum = 0;
    private Object lock = new Object();

    public void incr() {
        synchronized(lock) { // 关键点，synchronized 保证多线程环境下并发执行被包裹的代码段串行执行
            sum = sum + 1;
        }
    }
    public int getSum() {
        return sum;
    }
    
    public static void main(String[] args) throws InterruptedException {
        int loop = 10_0000; // 即100000 java7 新特性，增强可读性，注意不可以以 _ 开头或结尾

        // test single thread
        Counter counter = new Counter();
        for (int i = 0; i < loop; i++) {
            counter.incr();
        }

        System.out.println("single thread: " + counter.getSum());
    
        // test multiple threads
        final Counter counter2 = new Counter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < loop / 2; i++) {
                counter2.incr();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < loop / 2; i++) {
                counter2.incr();
            }
        });
        t1.start();
        t2.start();
        Thread.sleep(1000);

        System.out.println("multiple threads: " + counter2.getSum());
    
    
    }
}

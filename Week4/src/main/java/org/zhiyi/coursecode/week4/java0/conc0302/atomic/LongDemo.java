package org.zhiyi.coursecode.week4.java0.conc0302.atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class LongDemo {
    
    public static void main(String[] args) {
    
        final AtomicLong atomicLong = new AtomicLong();
        /**
         * 继承Striped64 内部维护一个@sun.misc.Contended Cell[]
         */
        final LongAdder longAdder = new LongAdder(); //TODO 分析设计与实现细节
        
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        atomicLong.getAndIncrement();
                        longAdder.increment();
                    }
                }
            }).start();
        }
        
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("atomicLong=" + atomicLong.get());
        System.out.println("longAdder =" + longAdder.sum());

    }
}

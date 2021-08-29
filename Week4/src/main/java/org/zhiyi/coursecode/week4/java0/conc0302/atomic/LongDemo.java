package org.zhiyi.coursecode.week4.java0.conc0302.atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class LongDemo {
    
    public static void main(String[] args) {
    
        final AtomicLong atomicLong = new AtomicLong();
        /**
         * �̳�Striped64 �ڲ�ά��һ��@sun.misc.Contended Cell[]
         */
        final LongAdder longAdder = new LongAdder(); //TODO ���������ʵ��ϸ��
        
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

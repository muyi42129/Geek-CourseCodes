package org.zhiyi.coursecode.week4.java0.conc0303.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentHashMapDemo {
    
    public static void main(String[] args) {
        demo1();

        System.out.println("-------------------------------------------");
        Map concurrentHashMap = new ConcurrentHashMap();
        System.out.println(concurrentHashMap.put("a", "a")); // null
        System.out.println(concurrentHashMap.put("a", "b")); // a
        System.out.println(concurrentHashMap.put("a", "b")); // b
        System.out.println(concurrentHashMap.put("b", "b")); // null
        System.out.println("-------------------------------------------");
        System.out.println(concurrentHashMap.putIfAbsent("c", "a")); // null
        System.out.println(concurrentHashMap.putIfAbsent("c", "b")); // a
        System.out.println(concurrentHashMap.get("c"));
        System.out.println(concurrentHashMap.putIfAbsent("d", "b")); // b
        System.out.println(concurrentHashMap.putIfAbsent("d", "c")); // null
        System.out.println("-------------------------------------------");
        HashMap hash = new HashMap();
        System.out.println(hash.put("a", "a"));
        System.out.println(hash.put("a", "b"));

    }

    public static void demo1() {
        final Map<String, AtomicInteger> count = new ConcurrentHashMap<>();
        final CountDownLatch endLatch = new CountDownLatch(2);
        Runnable task = () -> {
            AtomicInteger oldValue;
            for (int i = 0; i < 5; i++) {
                oldValue = count.get("a");
                if (null == oldValue) {
                    AtomicInteger zeroValue = new AtomicInteger(0);

                    /**
                     * �� put ����һ�������� putVal , �� putIfAbsent ����ʱ���������������� true
                     * ����
                     * 1����key�Ѵ�����map�У��򷵻ص�ǰvalue �����ٸ���value
                     * 2�����������򷵻�null Ȼ��put��ǰvalue
                     */
                    oldValue = count.putIfAbsent("a", zeroValue);
                    if (null == oldValue) {
                        System.out.println(Thread.currentThread().getName() + " : putIfAbsent success");
                        oldValue = zeroValue;
                    }
                }
                oldValue.incrementAndGet();
            }
            endLatch.countDown();
        };
        new Thread(task).start();
        new Thread(task).start();
        
        try {
            endLatch.await();
            System.out.println(count.get("a"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

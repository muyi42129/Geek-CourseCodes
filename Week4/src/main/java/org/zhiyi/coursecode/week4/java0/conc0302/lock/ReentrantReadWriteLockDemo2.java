
package org.zhiyi.coursecode.week4.java0.conc0302.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo2 {

    private final Map<String, Object> map = new HashMap<>();

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public Object readWrite(String key) {
        Object value = null;
//        System.out.println("1.首先开启读锁去缓存中取数据");
        rwLock.readLock().lock();
        try {
            value = map.get(key);
            if (value == null) {
//                System.out.println("2.数据不存在，则释放读锁，开启写锁");
                rwLock.readLock().unlock();
                rwLock.writeLock().lock();
                try {
                    if (map.get(key) == null) {
                        System.out.println("write one time for key: " + key);
                        value = key + "_";
                        map.put(key, value);
                    }
                } finally {
//                    System.out.println("3.释放写锁");
                    rwLock.writeLock().unlock();
                }
//                System.out.println("4.开启读锁");
                rwLock.readLock().lock();
                return map.get(key);
            }
        } finally {
//            System.out.println("5.释放读锁");
            rwLock.readLock().unlock();
        }
        return value;
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockDemo2 demo2 = new ReentrantReadWriteLockDemo2();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(()->{
                System.out.println("read key value is " + demo2.readWrite("key_" + finalI/2));

            }).start();
        }
    }

}

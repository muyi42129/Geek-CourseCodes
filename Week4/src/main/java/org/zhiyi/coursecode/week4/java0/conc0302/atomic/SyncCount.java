
package org.zhiyi.coursecode.week4.java0.conc0302.atomic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncCount {

    private int num = 0;

    private Lock lock = new ReentrantLock(true);

    /**
     * 自增操作前加锁，自增操作后解锁。
     * @return
     */
    public int add() {
        try {
            lock.lock();
            return num++;
        } finally {
            lock.unlock();
        }
    }

    public int getNum() {
        return num;
    }
}

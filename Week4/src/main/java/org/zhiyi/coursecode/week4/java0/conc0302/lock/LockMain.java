
package org.zhiyi.coursecode.week4.java0.conc0302.lock;

public class LockMain {

    public static void main(String[] args) {
        /**
         * 模拟死锁
         * add() 和 lockMethod() 均存在锁嵌套，进入外层synchronized后，需要等待对方持有的锁释放。
         */
        Count3 count3 = new Count3();
        ThreadA threadA = new ThreadA(count3);
        threadA.setName("线程A");
        threadA.start();

        ThreadB threadB = new ThreadB(count3);
        threadB.setName("线程B");
        threadB.start();

    }

}

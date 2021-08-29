
package org.zhiyi.coursecode.week4.java0.conc0302.atomic;

public class AtomicMain {

    public static void main(String[] args) {
        final SyncCount count = new SyncCount();
        final Count count1 = new Count();
        // 开启100个线程执行add()
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        count1.add();
                        count.add();
                    }
                }
            }).start();
        }

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("num=" + count1.getNum());
        System.out.println("num=" + count1.getNum());
        System.out.println("num=" + count1.getNum());
        System.out.println("num=" + count1.getNum());
        System.out.println("num=" + count.getNum());
    }

}

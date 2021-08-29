
package org.zhiyi.coursecode.week4.java0.conc0301.sync;

public class Thread1 implements Runnable {

    @Override
    public void run() {
        /**
         * ����ǰʵ����
         * synchronized ��this)
         * 1.�����ڷǾ�̬������ʹ�ã�thisΪ���õ�ǰ������ʵ���������Ի���һ������������
         * 2.��ͬʵ�����ô˷�������ͬ��һ�������������û�������ȴ���
         * 3.��ͬʵ������ʱ������������ͬ������������ȴ���
         *
         * �����У������߳� ta tb ��ʼ��ʹ�õ� Runnable �����Ϊ t1, ��˻�������ȴ� --���߳��Ⱥ�ִ��run();
         * ��������̵߳� Runnable ʵ����ͬ������ô˶δ���龺��������ͬ������������ȴ� --���߳̽���ִ��run();
         */
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
            }
            System.out.println(Thread.currentThread().getName() + " loop end ");
        }
    }

    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread ta = new Thread(t1, "A");
        Thread tb = new Thread(t1, "B");
        ta.start();
        tb.start();
    }
}


package org.zhiyi.coursecode.week4.java0.conc0301;

import java.io.IOException;

public class RunnerMain {

    public static void main(String[] args) throws IOException, InterruptedException {

        Runner1 runner1 = new Runner1();
        Thread thread1 = new Thread(runner1, "th-1");

        Runner2 runner2 = new Runner2();
        Thread thread2 = new Thread(runner2, "th-2");

        thread1.start();
        thread2.start();

        thread2.interrupt();  // i = true

        Thread.sleep(2000); // ������th-1, th-2 ����main����ִ�����ǰִ���������
        // ����ᷢ�֣���ʹth-2��Runnable״̬ʱ���жϣ�����Terminated�󣬱��жϵ�״̬�������á�
        System.out.println("after 2000 ms th-2, state : " + thread2.getState()+"; isInterrupted(): "+thread2.isInterrupted());
        System.out.println(Thread.activeCount());

        // ��ӡ��ǰ�߳�����Ϣ
        Thread.currentThread().getThreadGroup().list();
        // ��ӡ��ǰ�߳������߳���ĸ��߳����л�߳������
        System.out.println(Thread.currentThread().getThreadGroup().getParent().activeGroupCount());
        // ��ӡ��ǰ�߳������߳���ĸ��߳�����Ϣ�����Σ�������ǰ�߳��������߳��飩
        Thread.currentThread().getThreadGroup().getParent().list();
    
        
    }
}

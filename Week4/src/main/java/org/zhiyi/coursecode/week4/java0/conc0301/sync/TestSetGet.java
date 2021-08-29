package org.zhiyi.coursecode.week4.java0.conc0301.sync;

public class TestSetGet {

    public static void main(String[] args) throws Exception {

        /**
         * get() set() ��ΪSetGet���зǾ�̬ synchronized �����������е��� get() �� set() ��ʵ������ s
         * ����ͬʵ����������synchronizedʵ����������������ͬһ�ѣ���һ�����Ҫ�����ʹ�ò�ͬʵ�����з��������򲻻�������ȴ���
         *
         * ������
         * �������߳� get() --sleep 10s ��ǰ�߳� set() --sleep 1s
         * 1.set() ִ����Ϻ� get() ��ȡ������ʼִ��
         * 2.get() �߳�����ִ�����ǰ�����߳����˳��� ��Ҫ������t1�̺߳��˳�Ҳ�ܼ򵥣� t.join();
         */
        final SetGet s = new SetGet();
        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    s.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        long start = System.currentTimeMillis();
//        final SetGet s1 = new SetGet();
        s.set(10);
//        t.join();
        System.out.println(" ��ʱ�� " + ( System.currentTimeMillis() - start ));

    }


    public static class SetGet {

        int a = 0;
        public synchronized void set(int v) throws Exception {
            System.out.println(Thread.currentThread().getName() +" setting " +v);
            Thread.sleep(5000);
            a = v;
            System.out.println(Thread.currentThread().getName() +" set " +v);
        }

        public synchronized int get() throws Exception {
            System.out.println(Thread.currentThread().getName() +" getting ");
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName() + " get ");
            return a;
        }

    }
}

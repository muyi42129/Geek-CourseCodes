package org.zhiyi.coursecode.week4.java0.conc0301;

public class DaemonThread {
    
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
                try {
                    Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                Thread t = Thread.currentThread();
                System.out.println("当前线程:" + t.getName());
        };
        Thread thread = new Thread(task);
        thread.setName("test-thread-1");
        // 注意这里设置为守护线程
        // The Java Virtual Machine exits when the only threads running are all daemon threads.
        thread.setDaemon(true);
        thread.start();

        //Thread.sleep(5500);
    }
    
    
}

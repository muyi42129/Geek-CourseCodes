package org.zhiyi.coursecode.week4.java0.conc0301.op;

public class Join {
    
    public static void main(String[] args) {
        Object oo = new Object();
    
        MyThread thread1 = new MyThread("thread1 -- ");
//        oo = thread1;
        thread1.setOo(oo);
        thread1.start();
        
        synchronized (oo) {  // 这里用oo或thread1/this
            for (int i = 0; i < 100; i++) {
                if (i == 20) {
                    try {
                        oo.wait(0); // wait 将释放当前线程持有的锁，进入阻塞状态，等待其他线程 notify
//                        thread1.join(); // 当前线程等待 thread1 执行完毕，如果两个线程锁同一个对象，将死锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " -- " + i);
            }
        }
    }
    
}

class MyThread extends Thread {
    
    private String name;
    private Object oo;
    
    public void setOo(Object oo) {
        this.oo = oo;
    }
    
    public MyThread(String name) {
        this.name = name;
    }
    
    @Override
    public void run() {
        synchronized (oo) { // 这里用oo或this，效果不同：
            // 1.锁oo则与主线程中synchronized 锁的对象相同，会BLOCKED至主线程释放锁
            // 2.锁this则无持有调用该方法实例对象锁的线程，直接获取到锁，执行同步块中代码
            for (int i = 0; i < 100; i++) {
                System.out.println(name + i);
            }
//            oo.notify();
        }
    }
    
}
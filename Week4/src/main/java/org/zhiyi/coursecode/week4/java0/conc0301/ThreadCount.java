package org.zhiyi.coursecode.week4.java0.conc0301;

public class ThreadCount {

    public static void main(String[] args) throws InterruptedException {
        // 父线程组，系统线程组信息
        System.out.println("system："+Thread.currentThread().getThreadGroup().getParent());
        /**
         * ### 父线程组信息
         * java.lang.ThreadGroup[name=system,maxpri=10]
         *     Thread[Reference Handler,10,system]
         *     Thread[Finalizer,8,system]
         *     Thread[Signal Dispatcher,9,system]
         *     Thread[Attach Listener,5,system]
         *
         *     ### 以下是当前线程组信息
         *     java.lang.ThreadGroup[name=main,maxpri=10]   线程组名称，最大
         *         Thread[main,5,main]
         *         Thread[Monitor Ctrl-Break,5,main]
         *
         * IDE debug时，线程信息中也会有体现，与上面打印的内容是完全一致的。
         */
        Thread.currentThread().getThreadGroup().getParent().list();

        System.out.println();

        // 当前线程组，main线程组信息
        System.out.println("main："+Thread.currentThread().getThreadGroup());
        /**
         * java.lang.ThreadGroup[name=main,maxpri=10]
         *     Thread[main,5,main]
         *     Thread[Monitor Ctrl-Break,5,main]
         */
        Thread.currentThread().getThreadGroup().list();
    }
}

package org.zhiyi.coursecode.week4.java0.conc0303.future;

import java.util.Random;
import java.util.concurrent.*;

public class FutureDemo1 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        // ��װ�� FutureTask implements of RunnableFuture �����̳߳�ִ������󷵻�
        Future<Integer> result = executor.submit(() -> {
            Thread.sleep(2000);
            return new Random().nextInt();
        });
        executor.shutdown();
        try {
            System.out.println("result:" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
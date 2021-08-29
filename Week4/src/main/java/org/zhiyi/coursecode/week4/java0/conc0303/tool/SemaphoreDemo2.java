package org.zhiyi.coursecode.week4.java0.conc0303.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo2 {
        
        private final static int threadCount = 20;
        
        public static void main(String[] args) throws Exception {
            
            ExecutorService exec = Executors.newCachedThreadPool();
            
            final Semaphore semaphore = new Semaphore(5);

            // 请求20 次
            for (int i = 0; i < threadCount; i++) {
                final int threadNum = i;
                exec.execute(() -> {
                    try {
                        // 获取多个许可，退化成串行执行，每次获取许可后，剩余许可数量在本次任务未释放前，不足调用下次任务使用
                        semaphore.acquire(3);
                        test(threadNum);
                        // 释放多个许可
                        semaphore.release(3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            exec.shutdown();
        }
        
        private static void test(int threadNum) throws Exception {
            System.out.println("id:"+threadNum+","+Thread.currentThread().getName());
            Thread.sleep(1000);
        }
    }

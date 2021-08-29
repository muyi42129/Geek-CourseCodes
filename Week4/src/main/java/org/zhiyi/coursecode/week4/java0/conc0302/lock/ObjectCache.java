
package org.zhiyi.coursecode.week4.java0.conc0302.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ObjectCache<T> {

    public interface ObjectFactory<T> {
        T makeObject();
    }

    class Node {
        T obj;
        Node next;
    }

    final int capacity;
    final ObjectFactory<T> factory;
    final Lock lock = new ReentrantLock();
    final Semaphore semaphore;
    private Node head;
    private Node tail;

    public ObjectCache(int capacity, ObjectFactory<T> factory) {
        this.capacity = capacity;
        this.factory = factory;
        this.semaphore = new Semaphore(this.capacity);
        this.head = null;
        this.tail = null;
    }

    public T getObject() throws InterruptedException {
        semaphore.acquire();
        return getNextObject();
    }

    /**
     * ������ͷ���Ƴ��ڵ�
     * @return
     */
    private T getNextObject() {
        try {
            lock.lock();
            if (head == null) {
                return factory.makeObject();
            } else {
                Node ret = head;
                head = head.next;
                if (head == null) tail = null;
                ret.next = null;//help GC
                return ret.obj;
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * ������β������ڵ�
     * @param t
     */
    private void returnObjectToPool(T t) {
        try {
            lock.lock();
            Node node = new Node();
            node.obj = t;
            if (tail == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }

        } finally {
            lock.unlock();
        }
    }

    public void returnObject(T t) {
        returnObjectToPool(t);
        System.out.println(Thread.currentThread().getName() + " release one permit and cached one obj on linked list, hashCode " + t.hashCode());
        semaphore.release(); // Releases a permit, increasing the number of available permits by one.
    }

    public static void main(String[] args) throws InterruptedException {
        ObjectCache cache = new ObjectCache<>(5, ()-> {
            Object o = new Object();
            System.out.println(Thread.currentThread().getName() + " now have permit, but no objs on linked list, so new one : " + o.hashCode());
            return o;
        });

        // �ͷ�5����ɣ�������5������ ע�⣬��ʼ������������� Release ����ɿɵ��ӣ����ֵ��2^31 -1
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                cache.returnObject(new Object());
            }).start();
        }

        Thread.sleep(2000);

        // ����ȡ��10������
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    Object o = cache.getObject();
                    System.out.println(Thread.currentThread().getName() + " get one obj, hashCode " + o.hashCode());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

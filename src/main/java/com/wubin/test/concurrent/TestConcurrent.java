package com.wubin.test.concurrent;

import cn.hutool.core.date.DateUtil;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestConcurrent {

    public static void main(String[] args) {
//        cyclicBarrier();
//        countDownLatch();
//        semaphore();
//        reentrantLock();

        linkedBlockingQueue();
    }

    //CyclicBarrier
    public static void cyclicBarrier() {
        int count = 50;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count,
                //最后一个进入的线程执行
                () -> System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName() + " : 结束"));
        for (int i = 0; i < count; ++i) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName());
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName());
    }

    //CountDownLatch
    public static void countDownLatch() {
        int count = 50;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; ++i) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName());
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName());
    }

    //Semaphore
    public static void semaphore() {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 5; ++i) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    //ReentrantLock
    public static void reentrantLock() {
        Lock lock = new ReentrantLock();
        for (int i = 1; i <= 5; ++i) {
            new Thread(() -> {
                try {
                    lock.lock();
                    System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    //LinkedBlockingQueue先进先出
    //put()添加一个元素，队列已满，阻塞
    //add()添加一个元素，队列已满，异常java.lang.IllegalStateException: Queue full
    //offer()添加一个元素，队列已满，返回false
    //take()移除并返回头元素，队列为空，阻塞
    //remove()移除并返回头元素，队列为空，异常java.util.NoSuchElementException
    //poll()移除并返回头元素，队列为空，返回null
    //element()返回头元素，队列为空，异常java.util.NoSuchElementException
    //peek()返回头元素，队列为空，返回null
    public static void linkedBlockingQueue() {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(100);
        new Thread(() -> {
            for (int i = 0; i < 10; ++i) {
                try {
                    queue.put(String.valueOf(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //ConcurrentLinkedQueue

    //com.lmax disruptor
    //com.dinstone beanstalkc

}

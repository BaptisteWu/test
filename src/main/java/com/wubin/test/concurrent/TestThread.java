package com.wubin.test.concurrent;

import cn.hutool.core.date.DateUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;
import com.google.common.util.concurrent.Striped;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;

public class TestThread {

    public static void test() {
    }

    // 实现Runnable和Callable接口的方式，如需访问当前线程，须调用Thread.currentThread()
    // 继承Thread类的方式，如需访问当前线程，可以用this

    //synchronized悲观锁、独占锁
    //CAS乐观锁、自旋锁、无锁
    //Vector、StringBuffer、HashTable不推荐用，因为它们无脑加锁

    public static void main(String[] args) {
//        callable();

//        rowLock();
//        deadLock();

//        error1();
//        error2();

//        limit1();
//        limit2();

//        testHashMap();

//        waitNotify();

//        singleThreadExecutor();
//        fixedThreadPool();
//        cachedThreadPool();
//        scheduledThreadPool();
    }

    //创建线程
    public static void callable() {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            try {
                System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "success";
        });
        new Thread(futureTask).start();
        try {
            // futureTask.get()会阻塞线程
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName());
    }

    //行锁
    public static void rowLock() {
        int count = 2000;
        String key = "test";
        Map<String, Integer> map = new HashMap<>();
        map.put(key, 0);
        Striped<Lock> striped = Striped.lazyWeakLock(200);

        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; ++i) {
            new Thread(() -> {
//                Lock lock = striped.get(1);
//                lock.lock();

//                synchronized (new String("1")) {
//                synchronized (String.valueOf(1)) {

//                synchronized (String.valueOf(1).intern()) {
//                synchronized (Integer.valueOf(1)) {
                synchronized ("1") {
                    Integer value = map.get(key);
                    map.put(key, value + 1);
                    countDownLatch.countDown();
                }

//                lock.unlock();
            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map.get(key));
    }

    //死锁
    public static void deadLock() {
        Object o1 = new Object();
        Object o2 = new Object();
        new Thread(() -> {
            synchronized (o1) {
                System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName() + " : o1");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName() + " : o2");
                }
            }
        }).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            synchronized (o2) {
                System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName() + " : o2");
                synchronized (o1) {
                    System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName() + " : o1");
                }
            }
        }).start();
    }

    //锁后抛出异常，synchronized会解锁
    public static void error1() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            synchronized ("1") {
                System.out.println(111);
                throw new RuntimeException();
            }
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized ("1") {
                System.out.println(222);
                countDownLatch.countDown();
            }
        }).start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(333);
    }

    //锁后抛出异常，Lock不会解锁
    public static void error2() {
        Striped<Lock> striped = Striped.lazyWeakLock(200);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            Lock lock = striped.get(1);
            lock.lock();
            System.out.println(111);
            throw new RuntimeException();
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Lock lock = striped.get(1);
            lock.lock();
            System.out.println(222);
            countDownLatch.countDown();
            lock.unlock();
        }).start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(333);
    }

    //重复提交，线程不安全
    public static void limit1() {
        Cache<String, Object> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .build();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        String key = "test";
        for (int i = 0; i < 50; ++i) {
            executor.execute(() -> {
                if (cache.getIfPresent(key) != null) {
                    System.out.println(Thread.currentThread().getName() + " : 111");
                } else {
                    cache.put(key, 0);
                    System.out.println(Thread.currentThread().getName() + " : 222");
                }
            });
        }
        executor.shutdown();
    }

    //重复提交，线程安全
    public static void limit2() {
        Map<String, Object> cache = new ConcurrentHashMap<>(200);
//        HashMap<String, Object> cache = new HashMap<>(200);
        String key = "test";
        for (int i = 0; i < 50; ++i) {
            new Thread(() -> {
                if (cache.putIfAbsent(key, 0) != null) {
                    System.out.println(Thread.currentThread().getName() + " : 111");
                } else {
                    System.out.println(Thread.currentThread().getName() + " : 222");
                }
            }).start();
        }
    }

    //HashMap线程不安全模拟
    public static void testHashMap() {
//        Map<String, Object> map = Collections.synchronizedMap(new HashMap<>());
//        Map<String, Object> map = new ConcurrentHashMap<>();
        Map<String, Object> map = new HashMap<>();
        for (int i = 1; i <= 100; ++i) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), "");
                System.out.println(map);
            }).start();
        }
    }

    //线程池：单线程
    public static void singleThreadExecutor() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; ++i) {
            executor.execute(() -> {
                try {
                    System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }

    //线程池：固定最大线程数
    public static void fixedThreadPool() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; ++i) {
            executor.execute(() -> {
                try {
                    System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }

    //线程池：根据JVM动态设置
    public static void cachedThreadPool() {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; ++i) {
            executor.execute(() -> {
                try {
                    System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }

    //线程池：定时
    public static void scheduledThreadPool() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName());
        //延迟3秒执行
        executor.schedule(() ->
                System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName()), 3, TimeUnit.SECONDS);
        //延迟5秒执行，后每隔1秒执行
        executor.scheduleAtFixedRate(() ->
                System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName()), 5, 1, TimeUnit.SECONDS);
    }

    //wait()和notify()必须在同步块中调用
    //wait()和notify()必须持有同一对象锁
    public static void waitNotify() {
        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName());
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName());
            }
        }).start();
        new Thread(() -> {
            synchronized (o) {
                System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                o.notify();
                System.out.println(DateUtil.now() + " : " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}

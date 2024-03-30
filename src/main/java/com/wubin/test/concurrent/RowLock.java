package com.wubin.test.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RowLock {

    private final Map<String, Object> MAP = new ConcurrentHashMap<>();

    public synchronized void lock(String key) {
        try {
            while (MAP.containsKey(key)) {
                wait();
            }
            MAP.put(key, "");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void unlock(String key) {
        while (MAP.containsKey(key)) {
            MAP.remove(key);
            notifyAll();
        }
    }

}

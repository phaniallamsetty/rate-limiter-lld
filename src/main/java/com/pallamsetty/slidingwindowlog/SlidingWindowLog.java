package com.pallamsetty.slidingwindowlog;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SlidingWindowLog {
    private TreeMap<Long, Boolean> swLog;
    private int capacity;
    private int windowInMillis;

    public SlidingWindowLog(int capacity, int windowInMillis) {
        swLog = new TreeMap<>();
        this.capacity = capacity;
        this.windowInMillis = windowInMillis;
        System.out.println();
    }

    public void addNewRequestToLog() {
        long timestamp = System.currentTimeMillis();
        removeOutdatedTimestamps(timestamp);
        swLog.put(timestamp, false);
        swLog.put(timestamp, isAccepted());
    }

    private void removeOutdatedTimestamps(long timestamp) {
        List<Long> flagToDelete = new ArrayList<>();
        for(Map.Entry<Long, Boolean> kvPair : swLog.entrySet()) {
            long diff = timestamp - kvPair.getKey();
            if(diff > windowInMillis) {
                flagToDelete.add(kvPair.getKey());
            } else {
                break;
            }
        }

        for(long ts: flagToDelete) {
            swLog.remove(ts);
        }
    }

    private boolean isAccepted() {
        return swLog.size() <= capacity;
    }

    public int getLogSize() {
        printLog();
        return swLog.size();
    }

    private void printLog() {
        System.out.println("Log:");
        System.out.println("==============================");

        for(Map.Entry<Long, Boolean> kvPair: swLog.entrySet()) {
            System.out.println(kvPair.getKey() + " --> " + kvPair.getValue());
        }
        System.out.println();
    }
}

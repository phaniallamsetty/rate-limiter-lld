package com.pallamsetty.tokenbucket;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TokenBucket {
    private int bucketCapacity;
    private int tokensInBucket;
    private int refillRate;

    public TokenBucket(int tokens, int refillRate) {
        setTokensInBucket(tokens);
        setRefillRate(refillRate);
        startScheduler();
    }
    public void refillBucket() {
        tokensInBucket = bucketCapacity;
        displayStateAndTimestamp();
        System.out.println();
    }

    public boolean processRequest() {
        if(tokensInBucket > 0) {
            tokensInBucket--;
            displayStateAndTimestamp();
            return true;
        } else {
            System.out.println("Reached the max limit: Error 429");
        }
        displayStateAndTimestamp();
        return false;
    }

    private void startScheduler() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            refillBucket();
        };

        scheduler.scheduleAtFixedRate(task, 0, refillRate, TimeUnit.SECONDS);
    }

    private void setTokensInBucket(int tokens) {
        if(tokens == 0) {
            this.bucketCapacity = 5;
        } else {
            this.bucketCapacity = tokens;
        }
    }

    private void setRefillRate(int refillRate) {
        if(refillRate == 0) {
            this.refillRate = 15;
        } else {
            this.refillRate = refillRate;
        }
    }

    private void displayStateAndTimestamp() {
        System.out.println("Tokens in Bucket: " + tokensInBucket + " / " + bucketCapacity);
        System.out.println("Timestamp: " + System.currentTimeMillis());
        System.out.println();
    }
}

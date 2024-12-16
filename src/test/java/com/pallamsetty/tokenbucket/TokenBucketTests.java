package com.pallamsetty.tokenbucket;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TokenBucketTests {
    private TokenBucket tokenBucket;
    ScheduledExecutorService scheduler;

    @Test
    public void testTokenBucket1() throws InterruptedException {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        tokenBucket = new TokenBucket(4, 30);

        scheduler.schedule(() -> {
            System.out.println("Delayed task executed");
        }, 3, TimeUnit.SECONDS);

        // Continue executing other methods
        assertTrue(tokenBucket.processRequest());
        Thread.sleep(5000);

        assertTrue(tokenBucket.processRequest());
        Thread.sleep(5000);

        assertTrue(tokenBucket.processRequest());
        Thread.sleep(5000);

        assertTrue(tokenBucket.processRequest());
        Thread.sleep(5000);

        assertFalse(tokenBucket.processRequest());
        Thread.sleep(5000);

        assertFalse(tokenBucket.processRequest());
        Thread.sleep(5000);

        // Bucket refilled
        assertTrue(tokenBucket.processRequest());
        Thread.sleep(5000);

        scheduler.shutdown();
    }
}

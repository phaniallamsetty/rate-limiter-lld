package com.pallamsetty.slidingwindowlog;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SlidingWindowLogTests {
    private SlidingWindowLog swl;

    @Test
    public void testAddNewRequestToLog1() throws InterruptedException {
        swl = new SlidingWindowLog(2, 60000);
        Thread.sleep(1000);
        swl.addNewRequestToLog();
        assertEquals(1, swl.getLogSize());
        Thread.sleep(30000);
        swl.addNewRequestToLog();
        assertEquals(2, swl.getLogSize());
        Thread.sleep(20000);
        swl.addNewRequestToLog();
        assertEquals(3, swl.getLogSize());
        Thread.sleep(50000);
        swl.addNewRequestToLog();
        assertEquals(2, swl.getLogSize());
    }
}

package com.pallamsetty.fixedwindowcounter;

import com.pallamsetty.helpers.APIReq;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FixedWindowCounter {
    private final int TIME_WINDOW;
    private int COUNTER;
    private int currentCount;

    public FixedWindowCounter(int timeWindowInSeconds, int counter) {
        this.TIME_WINDOW = timeWindowInSeconds;
        this.COUNTER = counter;

        startResetScheduler();
    }

    public String processNewRequest(APIReq req) {
        // If the number of requests has already reached the COUNTER value, rate-limit the request.
        // Else, process the request.
        if(currentCount == COUNTER) {
            return "Rate Limited. Error 429.";
        }

        currentCount++;
        return "Request has been processed: " + req.toString();
    }

    private void startResetScheduler() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = this::resetTimeWindowAndCounter;

        scheduler.scheduleAtFixedRate(task, 0, TIME_WINDOW, TimeUnit.SECONDS);
    }

    private void resetTimeWindowAndCounter() {
        currentCount = 0;
        System.out.println("Time Window and Counter haven been reset");
    }
}

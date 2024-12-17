package com.pallamsetty.leakingbucket;

import com.pallamsetty.helpers.APIReq;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LeakingBucket {
    private Queue<APIReq> bucket;
    private int processingRateInSeconds;
    private final int bucketMaxSize;
    private final int numProcessedRequests;

    public LeakingBucket(int bucketMaxSize, int processingRateInSeconds, int numProcessedRequests) throws InterruptedException {
        this.bucketMaxSize = bucketMaxSize;
        this.processingRateInSeconds = processingRateInSeconds;
        this.numProcessedRequests = numProcessedRequests;
        bucket = new LinkedList<>();
        Thread.sleep(this.processingRateInSeconds);
        processRequests();
    }

    public String addRequestToBucket(APIReq req) {
        if(isBucketFree()) {
            addToBucket(req);
            return "Request added to bucket";
        }

        return "Request Rate Limited. Error 429.";
    }

    private boolean isBucketFree() {
        return bucket.size() < bucketMaxSize;
    }

    private void addToBucket(APIReq req) {
        bucket.offer(req);
        System.out.println("Request " + req.getReqId() + " added to bucket.");
    }

    private void processRequests() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            System.out.println("Processing cycle Started: ");
            processRequestsInBucket();
            System.out.println("Processing cycle ended");
        };

        scheduler.scheduleAtFixedRate(task, 0, processingRateInSeconds, TimeUnit.SECONDS);
    }

    private void processRequestsInBucket() {
        int counter = Math.min(bucketMaxSize, bucket.size());
        for(int i = 0; i < counter; i++) {
            APIReq currentReq = bucket.poll();
            System.out.println();
            System.out.println("Request processing: ");
            System.out.println("Request Id: " + currentReq.getReqId());
            System.out.println("Request url: " + currentReq.getUrl());
            System.out.println("Request request type: " + currentReq.getRequestType());
            System.out.println("--Request processing completed--");
        }
    }
}

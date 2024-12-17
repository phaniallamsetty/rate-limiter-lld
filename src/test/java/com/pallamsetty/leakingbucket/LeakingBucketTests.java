package com.pallamsetty.leakingbucket;

import com.pallamsetty.helpers.APIReq;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeakingBucketTests {
    private LeakingBucket leakingBucket;

    @Test
    public void testAddRequestsToBucket1() throws InterruptedException {
        String requestAddedMessage = "Request added to bucket";
        String errorMessage = "Request Rate Limited. Error 429.";
        leakingBucket = new LeakingBucket(10, 20, 7);
        assertEquals(requestAddedMessage, leakingBucket.addRequestToBucket(new APIReq(1, "google.com", "GET")));
        assertEquals(requestAddedMessage, leakingBucket.addRequestToBucket(new APIReq(2, "microsoft.com", "POST")));
        assertEquals(requestAddedMessage, leakingBucket.addRequestToBucket(new APIReq(3, "example.com", "PUT")));
        assertEquals(requestAddedMessage, leakingBucket.addRequestToBucket(new APIReq(4, "anotherexample.com", "DELETE")));
        assertEquals(requestAddedMessage, leakingBucket.addRequestToBucket(new APIReq(5, "onemoreexample.com", "OPTIONS")));
        assertEquals(requestAddedMessage, leakingBucket.addRequestToBucket(new APIReq(6, "restendpoint.com", "GET")));
        assertEquals(requestAddedMessage, leakingBucket.addRequestToBucket(new APIReq(7, "whateverendpoint.com", "POST")));
        assertEquals(requestAddedMessage, leakingBucket.addRequestToBucket(new APIReq(8, "howmanymore.com", "PUT")));
        assertEquals(requestAddedMessage, leakingBucket.addRequestToBucket(new APIReq(9, "iamoutofdummywebsitenames.com", "GET")));
        assertEquals(requestAddedMessage, leakingBucket.addRequestToBucket(new APIReq(10, "jerryseinfeld.com", "PUT")));
        assertEquals(errorMessage, leakingBucket.addRequestToBucket(new APIReq(11, "georgegetsratelimited.com", "POST")));
        assertEquals(errorMessage, leakingBucket.addRequestToBucket(new APIReq(12, "elaineisoutaswell.com", "GET")));
        assertEquals(errorMessage, leakingBucket.addRequestToBucket(new APIReq(13, "kramerisackmodel.com", "POST")));
        Thread.sleep(60000);
    }
}

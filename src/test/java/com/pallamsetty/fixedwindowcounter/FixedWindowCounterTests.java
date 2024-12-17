package com.pallamsetty.fixedwindowcounter;

import com.pallamsetty.helpers.APIReq;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FixedWindowCounterTests {
    private FixedWindowCounter fwc;

    @Test
    public void testProcessNewRequest1() throws InterruptedException {
        String successMessagePrefix = "Request has been processed: ";
        String errorMsg = "Rate Limited. Error 429.";
        fwc = new FixedWindowCounter(10, 3);
        APIReq api = new APIReq(1, "google.com", "GET");
        assertEquals(successMessagePrefix + api.toString(), fwc.processNewRequest(api));
        api = new APIReq(2, "microsoft.com", "POST");
        assertEquals(successMessagePrefix + api.toString(), fwc.processNewRequest(api));
        api = new APIReq(3, "expedia.com", "PUT");
        assertEquals(successMessagePrefix + api.toString(), fwc.processNewRequest(api));
        api = new APIReq(4, "apple.com", "DELETE");
        assertEquals(errorMsg, fwc.processNewRequest(api));
        Thread.sleep(10000);

        api = new APIReq(5, "google.com", "GET");
        assertEquals(successMessagePrefix + api.toString(), fwc.processNewRequest(api));
        api = new APIReq(6, "microsoft.com", "POST");
        assertEquals(successMessagePrefix + api.toString(), fwc.processNewRequest(api));

        Thread.sleep(10000);
        Thread.sleep(5000);
        api = new APIReq(7, "google.com", "GET");
        assertEquals(successMessagePrefix + api.toString(), fwc.processNewRequest(api));
        api = new APIReq(8, "microsoft.com", "POST");
        assertEquals(successMessagePrefix + api.toString(), fwc.processNewRequest(api));
        api = new APIReq(9, "expedia.com", "PUT");
        assertEquals(successMessagePrefix + api.toString(), fwc.processNewRequest(api));
        Thread.sleep(5000);
        api = new APIReq(10, "google.com", "GET");
        assertEquals(successMessagePrefix + api.toString(), fwc.processNewRequest(api));
        api = new APIReq(11, "microsoft.com", "POST");
        assertEquals(successMessagePrefix + api.toString(), fwc.processNewRequest(api));
        api = new APIReq(12, "expedia.com", "PUT");
        assertEquals(successMessagePrefix + api.toString(), fwc.processNewRequest(api));
    }
}

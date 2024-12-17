package com.pallamsetty.leakingbucket;

public class APIReq {
    private int reqId;
    private String url;
    private String requestType;

    public APIReq(int reqId, String url, String requestType) {
        this.reqId = reqId;
        this.url = url;
        this.requestType = requestType;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}

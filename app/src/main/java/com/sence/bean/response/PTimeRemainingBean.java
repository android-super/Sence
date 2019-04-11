package com.sence.bean.response;

public class PTimeRemainingBean {

    /**
     * time : 1554780569
     * payTime : 1400
     * autoCancelTime : 1800
     */

    private String time;
    private String payTime;
    private String autoCancelTime;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getAutoCancelTime() {
        return autoCancelTime;
    }

    public void setAutoCancelTime(String autoCancelTime) {
        this.autoCancelTime = autoCancelTime;
    }
}

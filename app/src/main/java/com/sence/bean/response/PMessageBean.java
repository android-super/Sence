package com.sence.bean.response;

public class PMessageBean {

    /**
     * message_red : 0
     * system_msg_red : 1
     *  system_ntc_red : 0
     * service_uid : 4
     * service_name : aaa
     */

    private String message_red;
    private String system_msg_red;
    private String system_ntc_red;
    private String service_uid;
    private String service_name;

    public String getMessage_red() {
        return message_red;
    }

    public void setMessage_red(String message_red) {
        this.message_red = message_red;
    }

    public String getSystem_msg_red() {
        return system_msg_red;
    }

    public void setSystem_msg_red(String system_msg_red) {
        this.system_msg_red = system_msg_red;
    }

    public String getSystem_ntc_red() {
        return system_ntc_red;
    }

    public void setSystem_ntc_red(String system_ntc_red) {
        this.system_ntc_red = system_ntc_red;
    }

    public String getService_uid() {
        return service_uid;
    }

    public void setService_uid(String service_uid) {
        this.service_uid = service_uid;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }
}

package com.sence.bean.response;

public class POrderDetailsBean {

    /**
     * id : 1
     * aid : 1
     * oid : 1
     * addtime : 0000-00-00 00:00:00
     * status : 等待买家付款
     * cmoney : 0.00
     * pmoney : 0.00
     * needpay : 33.00
     * gmoney : 0.00
     * address : {"username":"","phone":"","address":"11111","area":""}
     */

    private String id;
    private String aid;
    private String oid;
    private String addtime;
    private String status;
    private String cmoney;
    private String pmoney;
    private String needpay;
    private String gmoney;
    private AddressBean address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCmoney() {
        return cmoney;
    }

    public void setCmoney(String cmoney) {
        this.cmoney = cmoney;
    }

    public String getPmoney() {
        return pmoney;
    }

    public void setPmoney(String pmoney) {
        this.pmoney = pmoney;
    }

    public String getNeedpay() {
        return needpay;
    }

    public void setNeedpay(String needpay) {
        this.needpay = needpay;
    }

    public String getGmoney() {
        return gmoney;
    }

    public void setGmoney(String gmoney) {
        this.gmoney = gmoney;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public static class AddressBean {
        /**
         * username :
         * phone :
         * address : 11111
         * area :
         */

        private String username;
        private String phone;
        private String address;
        private String area;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }
    }
}

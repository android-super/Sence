package com.sence.bean.result;

import java.util.List;

public class ManageAddressBean {

    /**
     * status : 1
     * msg : 请求成功
     * data : [{"id":"3","username":"12aaa01","phone":"18500121621","area":"北京市 朝阳区","address":"高碑店东方福唐","zipcode":"100000","tel":"185****1621"},{"id":"2","username":"12aaa01","phone":"18500121621","area":"北京市 朝阳区","address":"高碑店东方福唐","zipcode":"100000","userAddress":"北京市 朝阳区高碑店东方福唐","tel":"185****1621"}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 3
         * username : 12aaa01
         * phone : 18500121621
         * area : 北京市 朝阳区
         * address : 高碑店东方福唐
         * zipcode : 100000
         * tel : 185****1621
         * userAddress : 北京市 朝阳区高碑店东方福唐
         */

        private String id;
        private String username;
        private String phone;
        private String area;
        private String address;
        private String zipcode;
        private String tel;
        private String userAddress;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }
    }
}

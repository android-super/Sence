package com.sence.bean.response;

public class PDefaultAddressBean {


    /**
     * list : {"id":"20","username":"冷雨","phone":"18735610120","area":"安徽省安庆市枞阳县","address":"你看着","zipcode":"052668","tel":"187****0120"}
     */

    private ListBean list;

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 20
         * username : 冷雨
         * phone : 18735610120
         * area : 安徽省安庆市枞阳县
         * address : 你看着
         * zipcode : 052668
         * tel : 187****0120
         */

        private String id;
        private String username;
        private String phone;
        private String area;
        private String address;
        private String zipcode;
        private String tel;

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
    }
}

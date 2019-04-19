package com.sence.bean.response;

/**
 * Created by zwy on 2019/3/28.
 * package_name is com.sence.bean.response
 * 描述:SenceGit
 */
public class PUserInfoBean {

    /**
     * uid : 4
     * user_name : 17600183077
     * nick_name : 风散
     * avatar : /Public/Uploads/avatar/2019-03-19/5c90d83ae25de.jpg
     * sex : 女
     * money : 0.00
     * address :
     * last_login : 1553064547
     * is_kol : 0
     * follow_num : 1
     * fans_num : 1
     * note_num : 1
     */
    private String upuid;
    private String inviteUsername;
    private String uid;
    private String user_name;
    private String nick_name;
    private String avatar;
    private String sex;
    private String money;
    private String address;
    private String last_login;
    private String is_kol;
    private String follow_num;
    private String fans_num;
    private String note_num;
    private OrderInfoBean order_info;

    public String getUpuid() {
        return upuid;
    }

    public void setUpuid(String upuid) {
        this.upuid = upuid;
    }

    public String getInviteUsername() {
        return inviteUsername;
    }

    public void setInviteUsername(String inviteUsername) {
        this.inviteUsername = inviteUsername;
    }

    public OrderInfoBean getOrder_info() {
        return order_info;
    }

    public void setOrder_info(OrderInfoBean order_info) {
        this.order_info = order_info;
    }

    public static class OrderInfoBean {
        /**
         * wait_pay : 0
         * wait_send : 0
         * wait_confirm : 0
         * wait_evaluate : 0
         */

        private String wait_pay;
        private String wait_send;
        private String wait_confirm;
        private String wait_evaluate;

        public String getWait_pay() {
            return wait_pay;
        }

        public void setWait_pay(String wait_pay) {
            this.wait_pay = wait_pay;
        }

        public String getWait_send() {
            return wait_send;
        }

        public void setWait_send(String wait_send) {
            this.wait_send = wait_send;
        }

        public String getWait_confirm() {
            return wait_confirm;
        }

        public void setWait_confirm(String wait_confirm) {
            this.wait_confirm = wait_confirm;
        }

        public String getWait_evaluate() {
            return wait_evaluate;
        }

        public void setWait_evaluate(String wait_evaluate) {
            this.wait_evaluate = wait_evaluate;
        }
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getIs_kol() {
        return is_kol;
    }

    public void setIs_kol(String is_kol) {
        this.is_kol = is_kol;
    }

    public String getFollow_num() {
        return follow_num;
    }

    public void setFollow_num(String follow_num) {
        this.follow_num = follow_num;
    }

    public String getFans_num() {
        return fans_num;
    }

    public void setFans_num(String fans_num) {
        this.fans_num = fans_num;
    }

    public String getNote_num() {
        return note_num;
    }

    public void setNote_num(String note_num) {
        this.note_num = note_num;
    }
}

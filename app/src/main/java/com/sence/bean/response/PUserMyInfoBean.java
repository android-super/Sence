package com.sence.bean.response;

import java.util.List;

public class PUserMyInfoBean {


    /**
     * uid : 19
     * user_name : 17316224497
     * nick_name : o～糖菓菓o_O
     * avatar : /Public/Uploads/header/2019-04-22/oH3Cg1N6vRkmU8vZ8RSI0CPgUc6U.jpg
     * details : 暂无标签
     * autograph : TA 还没有什么想说的
     * usertoken : 1915559263999991
     * focus_num : 2
     * fans_num : 3
     * is_focus : 0
     * is_kol : 1
     * vid :
     * is_read : 0
     * inviteCode : C90F08EC5
     * goods_info : [{"id":"23","name":"2019最新款耳机机构行家有同居体育等法规和小伙伴幸福感","introduce":"","describe":"&lt;p&gt;&lt;img src=&quot;/ueditor/php/upload/image/20190423/1555991384928083.jpg&quot; title=&quot;1555991384928083.jpg&quot; alt=&quot;餐具详情[1].jpg&quot;/&gt;222222&lt;img src=&quot;/ueditor/php/upload/image/20190423/1555990678748578.png&quot; title=&quot;1555990678748578.png&quot; alt=&quot;厨具[1].png&quot;/&gt;&lt;/p&gt;","img":"/Public/Uploads/Goods/2019-04-23/5cbe8b5ddb8db.png","price":"650.00"},{"id":"24","name":"天天向上的红酒 215g 刷了卡给快递的结果打开两个","introduce":"","describe":"&lt;p&gt;&lt;img src=&quot;/ueditor/php/upload/image/20190423/1555991554141234.jpg&quot; title=&quot;1555991554141234.jpg&quot; alt=&quot;餐具详情[1].jpg&quot;/&gt;&lt;/p&gt;","img":"/Public/Uploads/Goods/2019-04-23/5cbe8c053be23.png","price":"1080.00"},{"id":"25","name":"好好学习的健身器基于哦黄金矿工花椒粉胡椒粉和南非和甲方","introduce":"","describe":"&lt;p&gt;&lt;img src=&quot;/ueditor/php/upload/image/20190423/1555991694583193.jpg&quot; title=&quot;1555991694583193.jpg&quot; alt=&quot;餐具详情[1].jpg&quot;/&gt;&lt;/p&gt;","img":"/Public/Uploads/Goods/2019-04-23/5cbe8c9063292.jpg","price":"14000.00"},{"id":"27","name":"臭臭的香水打发士大夫的绯闻多多沟通的地方地方的","introduce":"","describe":"&lt;p&gt;&lt;img src=&quot;/ueditor/php/upload/image/20190423/1555991966822816.jpg&quot; title=&quot;1555991966822816.jpg&quot; alt=&quot;餐具详情[1].jpg&quot;/&gt;&lt;/p&gt;","img":"/Public/Uploads/Goods/2019-04-23/5cbe8d9f8ec6c.png","price":"599.00"}]
     * is_have_service : 0
     * is_shield : 0
     * vgroup_name :
     * avatarUrl : http://sence.forhour.com//Public/Uploads/header/2019-04-22/oH3Cg1N6vRkmU8vZ8RSI0CPgUc6U.jpg
     */

    private String uid;
    private String user_name;
    private String nick_name;
    private String avatar;
    private String details;
    private String autograph;
    private String usertoken;
    private String focus_num;
    private String fans_num;
    private String is_focus;
    private String is_kol;
    private String vid;
    private String is_read;
    private String inviteCode;
    private String is_have_service;
    private String is_shield;
    private String vgroup_name;
    private String avatarUrl;
    private List<GoodsInfoBean> goods_info;

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public String getUsertoken() {
        return usertoken;
    }

    public void setUsertoken(String usertoken) {
        this.usertoken = usertoken;
    }

    public String getFocus_num() {
        return focus_num;
    }

    public void setFocus_num(String focus_num) {
        this.focus_num = focus_num;
    }

    public String getFans_num() {
        return fans_num;
    }

    public void setFans_num(String fans_num) {
        this.fans_num = fans_num;
    }

    public String getIs_focus() {
        return is_focus;
    }

    public void setIs_focus(String is_focus) {
        this.is_focus = is_focus;
    }

    public String getIs_kol() {
        return is_kol;
    }

    public void setIs_kol(String is_kol) {
        this.is_kol = is_kol;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getIs_read() {
        return is_read;
    }

    public void setIs_read(String is_read) {
        this.is_read = is_read;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getIs_have_service() {
        return is_have_service;
    }

    public void setIs_have_service(String is_have_service) {
        this.is_have_service = is_have_service;
    }

    public String getIs_shield() {
        return is_shield;
    }

    public void setIs_shield(String is_shield) {
        this.is_shield = is_shield;
    }

    public String getVgroup_name() {
        return vgroup_name;
    }

    public void setVgroup_name(String vgroup_name) {
        this.vgroup_name = vgroup_name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<GoodsInfoBean> getGoods_info() {
        return goods_info;
    }

    public void setGoods_info(List<GoodsInfoBean> goods_info) {
        this.goods_info = goods_info;
    }

    public static class GoodsInfoBean {
        /**
         * id : 23
         * name : 2019最新款耳机机构行家有同居体育等法规和小伙伴幸福感
         * introduce :
         * describe : &lt;p&gt;&lt;img src=&quot;/ueditor/php/upload/image/20190423/1555991384928083.jpg&quot; title=&quot;1555991384928083.jpg&quot; alt=&quot;餐具详情[1].jpg&quot;/&gt;222222&lt;img src=&quot;/ueditor/php/upload/image/20190423/1555990678748578.png&quot; title=&quot;1555990678748578.png&quot; alt=&quot;厨具[1].png&quot;/&gt;&lt;/p&gt;
         * img : /Public/Uploads/Goods/2019-04-23/5cbe8b5ddb8db.png
         * price : 650.00
         */

        private String id;
        private String name;
        private String introduce;
        private String describe;
        private String img;
        private String price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}

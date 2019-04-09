package com.sence.bean.response;

/**
 * Created by zwy on 2019/4/8.
 * package_name is com.sence.bean.response
 * 描述:SenceGit
 */
public class PGoodBean {

    /**
     * id : 20
     * name : [买一送一]麻辣小螃蟹香辣蟹大闸蟹零食罐装全母海鲜熟食即食醉蟹
     * img : /Public/Uploads/Goods/2019-03-28/5c9c87c863804.jpg
     * price : 49
     * saveMoney : 49
     */

    private String id;
    private String name;
    private String img;
    private String price;
    private String saveMoney;

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

    public String getSaveMoney() {
        return saveMoney;
    }

    public void setSaveMoney(String saveMoney) {
        this.saveMoney = saveMoney;
    }
}

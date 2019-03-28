package com.sence.bean.response;

/**
 * Created by zwy on 2019/3/27.
 * package_name is com.sence.bean.response
 * 描述:SenceGit
 */
public class PBusRecommendBean {

    /**
     * id : 1
     * name : 一号商品
     * introduce : 方法大放丁放
     * price : 4
     * vprice : 5
     * img :
     */

    private String id;
    private String name;
    private String introduce;
    private String price;
    private String vprice;
    private String img;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVprice() {
        return vprice;
    }

    public void setVprice(String vprice) {
        this.vprice = vprice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

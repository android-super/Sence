package com.sence.bean.response;

/**
 * Created by zwy on 2019/3/27.
 * package_name is com.sence.bean.response
 * 描述:SenceGit
 */
public class PGoodListBean {

    /**
     * name : 一号商品
     * img : /Public/Uploads/shares/5a126e5bf2368.png
     * price : 10
     * id : 1
     */

    private String name;
    private String img;
    private String price;
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

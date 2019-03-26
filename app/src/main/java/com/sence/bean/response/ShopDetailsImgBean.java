package com.sence.bean.response;

public class ShopDetailsImgBean {
    private String img;
    private int img1;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getImg1() {
        return img1;
    }

    public void setImg1(int img1) {
        this.img1 = img1;
    }

    public ShopDetailsImgBean(String img, int img1) {
        this.img = img;
        this.img1 = img1;
    }
}

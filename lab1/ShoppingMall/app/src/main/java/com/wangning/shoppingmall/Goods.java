package com.wangning.shoppingmall;

/**
 * 商品
 */
public class Goods {

    /**
     * 商品名
     */
    private String name;

    /**
     * 商品价格
     */
    private String price;

    /**
     * 图片资源 id
     */
    private int imageId;

    public Goods(String name, String price, int imageId) {
        this.name = name;
        this.price = price;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getPrice() {
        return price;
    }
}

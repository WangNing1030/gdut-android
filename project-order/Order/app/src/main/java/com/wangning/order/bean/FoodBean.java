package com.wangning.order.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class FoodBean implements Serializable {
    // 序列化时保持FoodBean类版本的兼容性
    private static final long serialVersionUID = 1L;
    /**
     * 菜的id
     */
    private int foodId;
    /**
     * 菜的名称
     */
    private String foodName;
    /**
     * 菜的口味
     */
    private String taste;
    /**
     * 月售量
     */
    private int saleNum;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 添加到购物车的数量
     */
    private int count;
    /**
     * 菜的图片
     */
    private String foodPic;

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFoodPic() {
        return foodPic;
    }

    public void setFoodPic(String foodPic) {
        this.foodPic = foodPic;
    }
}

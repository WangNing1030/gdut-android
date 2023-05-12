package com.wangning.order.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ShopBean implements Serializable {
    // 序列化时保持ShopBean类版本的兼容性
    private static final long serialVersionUID = 1L;
    /**
     * 店铺id
     */
    private int id;
    /**
     * 店铺名称
     */
    private String shopName;
    /**
     * 月售数量
     */
    private int saleNum;
    /**
     * 起送价格
     */
    private BigDecimal offerPrice;
    /**
     * 配送费用
     */
    private BigDecimal distributionCost;
    /**
     * 福利
     */
    private String welfare;
    /**
     * 配送时间
     */
    private int time;
    /**
     * 店铺图片
     */
    private String shopPic;
    /**
     * 店铺公告
     */
    private String shopNotice;
    /**
     * 菜单列表
     */
    private List<FoodBean> foodList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }

    public BigDecimal getDistributionCost() {
        return distributionCost;
    }

    public void setDistributionCost(BigDecimal distributionCost) {
        this.distributionCost = distributionCost;
    }

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getShopPic() {
        return shopPic;
    }

    public void setShopPic(String shopPic) {
        this.shopPic = shopPic;
    }

    public String getShopNotice() {
        return shopNotice;
    }

    public void setShopNotice(String shopNotice) {
        this.shopNotice = shopNotice;
    }

    public List<FoodBean> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<FoodBean> foodList) {
        this.foodList = foodList;
    }

}

package com.castis.winter.util;

import java.util.Date;

public class PurchaseData {
    
    String productName;
    Date purchasedDate;
    int price;
    boolean isUsingCoupon;
    String productType;
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Date getPurchasedDate() {
        return purchasedDate;
    }
    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public boolean isUsingCoupon() {
        return isUsingCoupon;
    }
    public void setUsingCoupon(boolean isUsingCoupon) {
        this.isUsingCoupon = isUsingCoupon;
    }
    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }
}

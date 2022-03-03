package com.example.demo.testLineManWongnai;

public class Promotion {
    private double discountByPercentage = 0;
    private double discount = 0;
    private boolean isDiscount = false;
    private boolean isDiscountByPercentage = false;

    public Promotion(double discountByPercentage, double discount, boolean isDiscount, boolean isDiscountByPercentage) {
        this.discountByPercentage = discountByPercentage;
        this.discount = discount;
        this.isDiscount = isDiscount;
        this.isDiscountByPercentage = isDiscountByPercentage;
    }

    public double getDiscountByPercentage() {
        return discountByPercentage;
    }

    public void setDiscountByPercentage(double discountByPercentage) {
        this.discountByPercentage = discountByPercentage;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public boolean isDiscount() {
        return isDiscount;
    }

    public void setDiscount(boolean discount) {
        isDiscount = discount;
    }

    public boolean isDiscountByPercentage() {
        return isDiscountByPercentage;
    }

    public void setDiscountByPercentage(boolean discountByPercentage) {
        isDiscountByPercentage = discountByPercentage;
    }
}

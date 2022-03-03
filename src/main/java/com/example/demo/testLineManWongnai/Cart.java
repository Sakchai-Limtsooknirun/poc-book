package com.example.demo.testLineManWongnai;


public class Cart {
    private String productPrice;
    private Double price;
    private Promotion promotion;
    private int quantity = 1;

    public Cart(String productPrice, Double price, Promotion promotion, Integer quantity) {
        this.productPrice = productPrice;
        this.price = price;
        this.promotion = promotion;
        this.quantity = quantity;
    }

    public Cart(String productPrice, Double price, Promotion promotion) {
        this.productPrice = productPrice;
        this.price = price;
        this.promotion = promotion;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

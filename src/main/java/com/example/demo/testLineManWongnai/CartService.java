package com.example.demo.testLineManWongnai;

import java.util.List;

public class CartService {
    private List<Cart> lineItemCart;


    public Double totalPrice() {
        double totalPrice = 0;
        if (lineItemCart == null || lineItemCart.isEmpty()) return 0d;

        for (Cart lineItem : lineItemCart) {
            if (lineItem.getPromotion() != null) {
                if (lineItem.getPromotion().isDiscountByPercentage()) {
                    totalPrice += (lineItem.getPrice() - (lineItem.getPrice() * lineItem.getPromotion().getDiscountByPercentage())) * lineItem.getQuantity();
                } else if (lineItem.getPromotion().isDiscount()) {
                    totalPrice += (lineItem.getPrice() - lineItem.getPromotion().getDiscount()) * lineItem.getQuantity();
                }
            } else totalPrice += lineItem.getPrice() * lineItem.getQuantity();
        }
        return totalPrice;
    }


    public List<Cart> getLineItemCart() {
        return lineItemCart;
    }

    public void setLineItemCart(List<Cart> lineItemCart) {
        this.lineItemCart = lineItemCart;
    }
}

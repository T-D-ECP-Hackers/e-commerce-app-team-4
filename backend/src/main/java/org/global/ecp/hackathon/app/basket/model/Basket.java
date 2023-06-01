package org.global.ecp.hackathon.app.basket.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Basket {

    private List<BasketProduct> basketProducts;
    private Integer totalProducts;

    public Basket(final List<BasketProduct> basketProducts, final Integer totalProducts) {

        this.basketProducts = basketProducts;
        this.totalProducts = totalProducts;
    }

    public void resetTotalProducts() {

        totalProducts = 0;
    }
}

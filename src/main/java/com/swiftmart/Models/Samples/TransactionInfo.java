package com.swiftmart.Models.Samples;

import lombok.Data;

import java.util.List;

@Data
public class TransactionInfo
{

    private List<CartProductInfo> cartProducts;
    private Double total;

    public Double getTotal()
    {
        cartProducts.forEach((CartProductInfo productInfo) -> {
            total += productInfo.getSubTotal();
        });

        return total;
    }
}

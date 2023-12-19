package com.swiftmart.Models.Samples;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TransactionInfo
{

    private List<CartProductInfo> cartProducts;

    public Double getTotal()
    {
        return cartProducts.stream().mapToDouble(CartProductInfo::getSubTotal).sum();
    }

}

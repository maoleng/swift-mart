package com.swiftmart.Models.Samples;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swiftmart.Models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TransactionInfo
{

    @JsonIgnore
    private List<CartProductInfo> cartProducts;
    private User user;

    public Double getTotal()
    {
        return cartProducts.stream().mapToDouble(CartProductInfo::getSubTotal).sum();
    }

}

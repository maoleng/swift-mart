package com.swiftmart.Models.Samples;

import com.swiftmart.Models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartProductInfo
{

    private Product product;
    private int quantity;

}

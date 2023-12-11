package com.swiftmart.Models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("orders_products")
@Data
public class OrderProduct
{

    @DBRef
    private Order _order;

    @DBRef
    private Product _product;

    private String name;
    private Integer amount;
    private Double price;

}

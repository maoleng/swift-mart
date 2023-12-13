package com.swiftmart.Models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("orders_products")
@Data
public class OrderProduct
{

    private String orderId;
    private String productId;
    private String name;
    private Integer amount;
    private Double price;

}

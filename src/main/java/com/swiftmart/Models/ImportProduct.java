package com.swiftmart.Models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("imports_products")
@Data
public class ImportProduct
{

    private String importId;
    private String productId;
    private String name;
    private Integer amount;
    private Double price;

}

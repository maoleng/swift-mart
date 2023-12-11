package com.swiftmart.Models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("imports_products")
@Data
public class ImportProduct
{

    @DBRef
    private Import _import;

    @DBRef
    private Product _product;

    private String name;
    private Integer amount;
    private Double price;

}

package com.swiftmart.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("products")
@Data
public class Product
{

    @Id
    private String _id;

    private String name;
    private String sku;
    private Double price;
    private String description;
    @DBRef
    private Category _category;
    private Date createdAt;

}

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
    private String image = "https://cdn-icons-png.flaticon.com/512/4129/4129528.png";
    private String categoryId;
    private Date createdAt = new Date();

    private int quantity;
    private Category category;
    private Double importPrice;

    public String getQRCode()
    {
        return "https://chart.googleapis.com/chart?cht=qr&chs=500&chl=" + _id;
    }

}

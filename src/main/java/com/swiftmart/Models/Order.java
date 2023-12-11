package com.swiftmart.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("orders")
@Data
public class Order
{

    @Id
    private String _id;

    @DBRef
    private User _user;
    @DBRef
    private User _sale;
    @DBRef
    private String _location;
    private Float total;
    private String status;
    private String paymentMethod;
    private Double receivedMoney;
    private Double changedMoney;
    private Date createdAt;
}

package com.swiftmart.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

public class Import
{

    @Id
    private String _id;

    @DBRef
    private User _user;
    private Double total;
    private Date createdAt;

}

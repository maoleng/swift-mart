package com.swiftmart.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("users")
@Data
public class User
{

    @Id
    private String _id;

    private String name;
    private String email;
    private String username;
    private String phone;
    private String address;
    private String role;
    private String avatar;
    private String status;

    @DBRef
    private Location _location;

    private Date sentAt;
    private Date createdAt;

}

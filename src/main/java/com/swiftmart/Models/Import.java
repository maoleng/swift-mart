package com.swiftmart.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("imports")
@Data
public class Import
{

    @Id
    private String _id;
    private String userId;
    private Double total;
    private Date createdAt;

}

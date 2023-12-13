package com.swiftmart.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("images")
@Data
public class Image
{


    @Id
    private String _id;
    private String source;
    private String productId;

}

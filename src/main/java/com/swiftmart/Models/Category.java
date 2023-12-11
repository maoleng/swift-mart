package com.swiftmart.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("categories")
@Data
public class Category
{

    @Id
    private String _id;

    private String name;

}

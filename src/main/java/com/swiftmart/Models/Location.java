package com.swiftmart.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("locations")
@Data
public class Location
{

    @Id
    private String _id;
    private String address;

}

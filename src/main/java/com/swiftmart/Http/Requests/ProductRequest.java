package com.swiftmart.Http.Requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest
{

    @NotEmpty(message = "Please provide image")
    private String image;

    @NotEmpty(message = "Please provide name")
    private String name;

    @NotEmpty(message = "Please provide price")
    private String price;

    @NotEmpty(message = "Please provide category")
    private String category;

    @NotEmpty(message = "Please provide description")
    private String description;

}

package com.swiftmart.Http.Requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest
{

    @NotEmpty(message = "Please provide email")
    private String email;

    @NotEmpty(message = "Please provide name")
    private String name;

    private String phone;
    private String avatar;
    private String address;
}

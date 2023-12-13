package com.swiftmart.Http.Requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest
{

    @NotEmpty(message = "Please provide username")
    private String username;

    @NotEmpty(message = "Please provide password")
    private String password;

}

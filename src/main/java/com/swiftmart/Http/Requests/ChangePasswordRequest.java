package com.swiftmart.Http.Requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest
{

    @NotEmpty(message = "Please provide password")
    private String password;

    @NotEmpty(message = "Please provide old password")
    private String oldPassword;

}

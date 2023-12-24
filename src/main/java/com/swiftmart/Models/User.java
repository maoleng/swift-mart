package com.swiftmart.Models;

import com.swiftmart.Enums.UserStatus;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.Date;

@Document("users")
@Component
@Data
public class User
{

    @Id
    private String _id;

    private String name;
    private String email;
    private String username;
    private String phone;
    private String password;
    private String address;
    private String role;
    private String avatar;
    private String status;
    private String locationId;
    private Location location;
    private Date sentAt;
    private Date createdAt;

    public String getStatusCard()
    {
        if (status == null) {
            return "";
        }
        String cardClass = "";
        if (status.equals(UserStatus.ACTIVE.name())) {
            cardClass = "badge-success";
        } else if (status.equals(UserStatus.INACTIVE.name())) {
            cardClass = "badge-info";
        } else if (status.equals(UserStatus.DISABLE.name())) {
            cardClass = "badge-danger";
        } else if (status.equals(UserStatus.CHANGING_PASSWORD.name())) {
            cardClass = "badge-light";
        }

        return "<span class=\"badge rounded-pill " + cardClass + "\">" + this.status + "</span>";
    }

    public String getLoginCode(String salt)
    {
        return BCrypt.hashpw(get_id() + getSentAt(), salt);
    }

}

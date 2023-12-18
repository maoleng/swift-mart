package com.swiftmart.Models;

import com.swiftmart.Enums.OrderStatus;
import com.swiftmart.Enums.UserStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("orders")
@Data
public class Order
{

    @Id
    private String _id;

    private String userId;
    private String saleId;
    private String locationId;
    private Double total;
    private String status;
    private String paymentMethod;
    private Double receivedMoney;
    private Double changedMoney;
    private Date createdAt;

    public String getStatusCard()
    {
        String cardClass ="";
        if (status.equals(OrderStatus.SUCCESS.name())) {
            cardClass = "badge-success";
        } else if (status.equals(OrderStatus.FAILED.name())) {
            cardClass = "badge-danger";
        }

        return "<span class=\"badge rounded-pill " + cardClass + "\">" + this.status + "</span>";
    }

}

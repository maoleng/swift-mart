package com.swiftmart.Models;

import com.swiftmart.Enums.OrderStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
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
    private Double gaveBackMoney;
    private Date createdAt;

    private User user;
    private User sale;

    public String getStatusCard()
    {
        if (status == null) {
            return "";
        }
        String cardClass = "";
        if (status.equals(OrderStatus.SUCCESS.name())) {
            cardClass = "badge-success";
        } else if (status.equals(OrderStatus.FAILED.name())) {
            cardClass = "badge-danger";
        }

        return "<span class=\"badge rounded-pill " + cardClass + "\">" + this.status + "</span>";
    }

    public boolean isAfter10MinsFromCreatedAt()
    {
        Date createdAt = this.getCreatedAt();
        if (createdAt == null) {
            return false;
        }
        Calendar now = Calendar.getInstance();
        Calendar createdAtPlus10Mins = Calendar.getInstance();
        createdAtPlus10Mins.setTime(createdAt);
        createdAtPlus10Mins.add(Calendar.MINUTE, 10);

        return now.after(createdAtPlus10Mins);
    }

}

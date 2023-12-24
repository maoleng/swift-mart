package com.swiftmart.Models.Samples;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swiftmart.Models.Order;
import com.swiftmart.Models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TransactionInfo
{

    @JsonIgnore
    private List<CartProductInfo> cartProducts;
    private User user;
    private Order order;

    public Double getTotal()
    {
        return cartProducts.stream().mapToDouble(CartProductInfo::getSubTotal).sum();
    }

    public String getPaymentUrl()
    {
        if (order == null) return "";

        return "https://api.vietqr.io/image/970415-0984124239-8uTkd8i.jpg?amount=" + order.getTotal() + "&addInfo=" + order.get_id();
    }

}

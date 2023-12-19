package com.swiftmart.Models.Samples;

import com.swiftmart.Models.Order;
import com.swiftmart.Models.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderInfo
{

    private Order order;
    private List<OrderProduct> orderProducts;

}

package com.swiftmart.Http.Controllers.Api;

import com.swiftmart.Http.Controllers.BaseController;
import com.swiftmart.Models.OrderProduct;
import com.swiftmart.Services.OrderProductService;
import com.swiftmart.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order")
public class OrderController extends BaseController
{

    @Autowired
    public OrderController(OrderService orderService)
    {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public Object show(@PathVariable("orderId") String orderId)
    {
        boolean c = authorizeApi(); if (! c) return false;

        return orderService.getOrderInfo(orderId);
    }

}

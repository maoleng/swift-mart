package com.swiftmart.Http.Controllers;

import com.swiftmart.Models.Order;
import com.swiftmart.Services.CategoryService;
import com.swiftmart.Services.OrderService;
import com.swiftmart.Services.ProductService;
import com.swiftmart.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/statistic")
public class StatisticController extends BaseController
{

    protected StatisticController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String index(Model model, HttpServletRequest request)
    {
        String c = authorize(); if (c != null) return c;

        Map<String, Object> orderInfo = orderService.getOrdersWithInfo(request.getParameter("date"));
        List<Order> orders = (List<Order>) orderInfo.get("orders");
        Integer orderCount = (Integer) orderInfo.get("orderCount");
        Integer successOrderCount = (Integer) orderInfo.get("successOrderCount");
        Double revenue = (Double) orderInfo.get("revenue");

        model.addAttribute("orders", orders);
        model.addAttribute("orderCount", orderCount);
        model.addAttribute("successOrderCount", successOrderCount);
        model.addAttribute("revenue", revenue);

        return "statistic/index";
    }

}

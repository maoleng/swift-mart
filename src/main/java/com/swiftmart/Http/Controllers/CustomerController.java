package com.swiftmart.Http.Controllers;

import com.swiftmart.Enums.UserRole;
import com.swiftmart.Models.User;
import com.swiftmart.Services.OrderService;
import com.swiftmart.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController extends BaseController
{

    @Autowired
    public CustomerController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping(value = "/")
    public String index(Model model)
    {
        String c = authorize(); if (c != null) return c;

        List<User> users = userService.getUsersWithLocations(UserRole.CUSTOMER);
        model.addAttribute("users", users);

        return "customer/index";
    }

    @GetMapping(value = "/{id}")
    public String show(@PathVariable("id") String id, Model model)
    {
        String c = authorize(); if (c != null) return c;

        model.addAttribute("user", userService.findBy_id(id));
        model.addAttribute("orders", orderService.getOrdersByUserId(id));

        return "customer/show";
    }

}

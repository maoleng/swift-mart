package com.swiftmart.Http.Controllers;

import com.swiftmart.Enums.UserRole;
import com.swiftmart.Models.User;
import com.swiftmart.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController extends BaseController
{

    @Autowired
    public CustomerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String index(Model model)
    {
        String c = authorize(); if (c != null) return c;

        List<User> users = userService.getUsersWithLocations(UserRole.CUSTOMER);
        model.addAttribute("users", users);

        return "customer/index";
    }

}

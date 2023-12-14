package com.swiftmart.Http.Controllers;

import com.swiftmart.Models.User;
import com.swiftmart.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController
{

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String index(Model model)
    {
        List<User> users = userService.getUsersWithLocations();
        model.addAttribute("users", users);

        return "user/index";
    }
}

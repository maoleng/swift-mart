package com.swiftmart.Http.Controllers;

import com.swiftmart.Enums.UserRole;
import com.swiftmart.Enums.UserStatus;
import com.swiftmart.Http.Requests.UserRequest;
import com.swiftmart.Models.User;
import com.swiftmart.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        String c = authorize(); if (c != null) return c;

        List<User> users = userService.getUsersWithLocations();
        model.addAttribute("users", users);

        return "user/index";
    }

    @PostMapping(value = "/")
    public String store(@Valid UserRequest request)
    {
        String c = authorize(UserRole.ADMIN, UserRole.MANAGER); if (c != null) return c;

        userService.create(request);

        return "redirect:/user/";
    }

    @PostMapping(value = "/update")
    public String update(UserRequest request)
    {
        String c = authorize(); if (c != null) return c;

        userService.update(authed(), request);

        return "redirect:/user/";
    }


}

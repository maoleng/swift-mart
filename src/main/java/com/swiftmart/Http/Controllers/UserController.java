package com.swiftmart.Http.Controllers;

import com.swiftmart.Models.Location;
import com.swiftmart.Models.User;
import com.swiftmart.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class UserController extends BaseController
{

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String index()
    {
        log(userService.getUsersByLocationName("Ho Chi Minh"));

        return "theme/master";
    }
}

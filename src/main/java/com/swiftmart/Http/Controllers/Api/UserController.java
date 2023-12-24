package com.swiftmart.Http.Controllers.Api;

import com.swiftmart.Http.Controllers.BaseController;
import com.swiftmart.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("apiUserController")
@RequestMapping(value = "/api/user")
public class UserController extends BaseController
{

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/")
    public Object show(HttpServletRequest request)
    {
        boolean c = authorizeApi(); if (! c) return false;

        return userService.findByPhone(request.getParameter("phone"));
    }

}

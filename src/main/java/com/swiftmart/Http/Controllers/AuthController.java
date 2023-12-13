package com.swiftmart.Http.Controllers;


import com.swiftmart.Http.Requests.LoginRequest;
import com.swiftmart.Http.Requests.RegisterRequest;
import com.swiftmart.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/auth")
public class AuthController extends BaseController
{

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value= "/login")
    public String login()
    {
        return "auth/login";
    }

    @GetMapping(value= "/register")
    public String register()
    {
        return "auth/register";
    }

    @PostMapping(value= "/login")
    public String login(@Valid LoginRequest request)
    {
        return userService.authenticate(request) ? "theme/master" : "redirect:/auth/login";
    }

    @PostMapping(value= "/register")
    public String register(@Valid RegisterRequest request)
    {
        userService.saveUser(request);

        return "redirect:/auth/login";
    }

}

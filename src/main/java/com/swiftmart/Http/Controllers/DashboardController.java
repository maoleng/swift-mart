package com.swiftmart.Http.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class DashboardController extends BaseController
{

    @GetMapping(value = "/")
    public String index()
    {
        return notLogin() ? "redirect:/auth/login" : "theme/master";
    }

}

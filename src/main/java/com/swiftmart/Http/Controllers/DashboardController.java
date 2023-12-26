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
        String c = authorize(); if (c != null) return c;

        return "redirect:/transaction/";
    }

}

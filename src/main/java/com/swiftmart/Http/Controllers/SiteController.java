package com.swiftmart.Http.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class SiteController extends BaseController
{

    @GetMapping(value = "/error")
    public String error()
    {
        return "error";
    }

    @GetMapping(value = "/me")
    public String me()
    {
        String c = authorize(); if (c != null) return c;

        return "me";
    }

}

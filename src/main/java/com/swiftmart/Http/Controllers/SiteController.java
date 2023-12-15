package com.swiftmart.Http.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/error")
public class SiteController extends BaseController
{

    @GetMapping(value = "/")
    public String index()
    {
        return "error";
    }

}

package com.swiftmart.Http.Controllers.Api;

import com.swiftmart.Http.Controllers.BaseController;
import com.swiftmart.Services.OrderService;
import com.swiftmart.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("apiProductController")
@RequestMapping(value = "/api/product")
public class ProductController extends BaseController
{

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping("/")
    public Object index()
    {
        boolean c = authorizeApi(); if (! c) return false;

        return productService.getProductNames();
    }

}

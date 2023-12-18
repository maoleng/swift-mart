package com.swiftmart.Http.Controllers;

import com.swiftmart.Http.Requests.ProductRequest;
import com.swiftmart.Models.Product;
import com.swiftmart.Services.CategoryService;
import com.swiftmart.Services.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/product")
public class ProductController extends BaseController
{

    protected ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/")
    public String index(Model model)
    {
        String c = authorize(); if (c != null) return c;

        model.addAttribute("products", productService.getProductsWithInfo());

        return "product/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model)
    {
        String c = authorize(); if (c != null) return c;

        model.addAttribute("product", productService.findBy_id(id));
        model.addAttribute("categories", categoryService.findAll());

        return "product/edit";
    }

    @GetMapping(value = "/create")
    public String create(Model model)
    {
        String c = authorize(); if (c != null) return c;

        model.addAttribute("categories", categoryService.findAll());

        return "product/create";
    }

    @PostMapping(value = "/edit/{id}")
    public String update(@PathVariable("id") String id, @Valid ProductRequest request)
    {
        String c = authorize(); if (c != null) return c;

        productService.update(id, request);

        return "redirect:/product/edit/" + id + "?success=Update product successfully";
    }

    @PostMapping(value = "/")
    public String store(@Valid ProductRequest request)
    {
        String c = authorize(); if (c != null) return c;

        productService.create(request);

        return "redirect:/product/?success=Create product successfully";
    }

    @PostMapping(value = "/{id}")
    public String delete(@PathVariable("id") String id)
    {
        String c = authorize(); if (c != null) return c;

        boolean isSuccess = productService.destroy(id);
        String msg = isSuccess ?
                "success=Delete product successfully" :
                "error=Failed to delete product, product is in order or import";

        return "redirect:/product/?" + msg;
    }

}

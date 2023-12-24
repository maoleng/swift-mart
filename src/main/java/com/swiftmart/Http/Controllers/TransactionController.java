package com.swiftmart.Http.Controllers;

import com.swiftmart.Models.Order;
import com.swiftmart.Models.Samples.CartProductInfo;
import com.swiftmart.Models.Samples.PaymentInfo;
import com.swiftmart.Models.Samples.TransactionInfo;
import com.swiftmart.Models.User;
import com.swiftmart.Services.OrderService;
import com.swiftmart.Services.ProductService;
import com.swiftmart.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/transaction")
public class TransactionController extends BaseController
{

    @Autowired
    public TransactionController(UserService userService, ProductService productService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping(value = "/")
    public String index(HttpServletRequest request, Model model)
    {
        String c = authorize(); if (c != null) return c;

        HttpSession session = request.getSession();
        String state = (String) session.getAttribute("state");


        TransactionInfo transaction = (TransactionInfo) session.getAttribute("transaction");
        if (transaction == null) transaction = new TransactionInfo(new ArrayList<>(), new User());
        model.addAttribute("transaction", transaction);

        if (state == null) {
            List<CartProductInfo> productInfos = transaction.getCartProducts();
            model.addAttribute("productInfos", productInfos);

            return "transaction/index";
        }

        if (state.equals("fill-user-info")) {
            User user = transaction.getUser();
            List<Order> orders = new ArrayList<>();
            if (user.get_id() != null) {
                orders = orderService.getOrdersByUserId(user.get_id());
            }
            model.addAttribute("orders", orders);
            model.addAttribute("user", user);

            return "transaction/fill-user-info";
        }

        PaymentInfo payment = (PaymentInfo) session.getAttribute("payment");
        if (payment == null) {
            return "transaction/payment";
        }

        return "transaction/index";
    }

    @GetMapping(value = "/next")
    public String next(HttpServletRequest request)
    {
        request.getSession().setAttribute("state", request.getParameter("step"));

        return "redirect:/transaction/";
    }



}

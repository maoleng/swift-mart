package com.swiftmart.Http.Controllers;

import com.swiftmart.Models.Samples.CartProductInfo;
import com.swiftmart.Models.Samples.PaymentInfo;
import com.swiftmart.Models.Samples.TransactionInfo;
import com.swiftmart.Models.User;
import com.swiftmart.Services.OrderService;
import com.swiftmart.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/transaction")
public class TransactionController extends BaseController
{

    @Autowired
    public TransactionController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping(value = "/")
    public String index(HttpServletRequest request, Model model)
    {
        String c = authorize(); if (c != null) return c;

        HttpSession session = request.getSession();
        String state = (String) session.getAttribute("state");


        TransactionInfo transaction = (TransactionInfo) session.getAttribute("transaction");
        if (transaction == null) {
            transaction = new TransactionInfo();
        }
        if (state == null) {
            List<CartProductInfo> productInfos = transaction.getCartProducts();
            model.addAttribute("productInfos", productInfos);

            return "transaction/index";
        }

        User customer = (User) session.getAttribute("customer");
        if (customer == null) {
            return "transaction/user-info";
        }

        PaymentInfo payment = (PaymentInfo) session.getAttribute("payment");
        if (payment == null) {
            return "transaction/payment";
        }

        return "transaction/index";
    }

}

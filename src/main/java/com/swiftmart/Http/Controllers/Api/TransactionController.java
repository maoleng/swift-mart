package com.swiftmart.Http.Controllers.Api;

import com.swiftmart.Http.Controllers.BaseController;
import com.swiftmart.Models.Product;
import com.swiftmart.Models.Samples.CartProductInfo;
import com.swiftmart.Models.Samples.PaymentInfo;
import com.swiftmart.Models.Samples.TransactionInfo;
import com.swiftmart.Models.User;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("apiTransactionController")
@RequestMapping(value = "/api/transaction")
public class TransactionController extends BaseController
{

    @Autowired
    public TransactionController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @PostMapping("/add-to-cart")
    public Object addToCart(HttpServletRequest request)
    {
        boolean c = authorizeApi(); if (! c) return false;

        HttpSession session = request.getSession();
        TransactionInfo transaction = (TransactionInfo) session.getAttribute("transaction");
        if (transaction == null) transaction = new TransactionInfo(new ArrayList<>());

        Product product = productService.findByName(request.getParameter("name"));
        List<CartProductInfo> productInfos = transaction.getCartProducts();

        CartProductInfo productInfo = productInfos.stream()
                .filter(cartProductInfo -> cartProductInfo.getProduct().equals(product))
                .findFirst()
                .orElse(null);
        if (productInfo == null) {
            CartProductInfo newCartItem = new CartProductInfo(product, 1);
            productInfos.add(newCartItem);
        } else {
            int qty = 0;
            if (request.getParameter("qty") != null) {
                qty = Integer.parseInt(request.getParameter("qty"));
            } else if (request.getParameter("type").equals("plus")) {
                qty = productInfo.getQuantity() + qty;
            }
            productInfo.setQuantity(qty);
            if (qty == 0) {
                productInfos.remove(productInfo);
            }
        }
        transaction.setCartProducts(productInfos);
        session.setAttribute("transaction", transaction);

        return productInfo;
    }

}

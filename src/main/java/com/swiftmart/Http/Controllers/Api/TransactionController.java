package com.swiftmart.Http.Controllers.Api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swiftmart.Http.Controllers.BaseController;
import com.swiftmart.Models.Product;
import com.swiftmart.Models.Order;
import com.swiftmart.Models.Samples.CartProductInfo;
import com.swiftmart.Models.Samples.TransactionResponse.TransactionRecord;
import com.swiftmart.Models.Samples.TransactionResponse.TransactionResponse;
import com.swiftmart.Models.Samples.TransactionInfo;
import com.swiftmart.Models.User;
import com.swiftmart.Services.OrderService;
import com.swiftmart.Services.ProductService;
import com.swiftmart.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController("apiTransactionController")
@RequestMapping(value = "/api/transaction")
public class TransactionController extends BaseController
{

    @Value("${app.payment.endpoint}")
    private String endpoint;

    @Value("${app.payment.key}")
    private String apiKey;

    @Autowired
    public TransactionController(UserService userService, ProductService productService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @PostMapping("/add-to-cart")
    public Object addToCart(HttpServletRequest request)
    {
        boolean c = authorizeApi(); if (! c) return false;

        HttpSession session = request.getSession();
        TransactionInfo transaction = (TransactionInfo) session.getAttribute("transaction");
        if (transaction == null) transaction = new TransactionInfo(new ArrayList<>(), new User(), new Order());

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

        Map<String, Object> data = new HashMap<>();
        data.put("transaction", transaction);
        data.put("productInfo", productInfo);

        return data;
    }

    @PostMapping(value = "/fill-user-info")
    public Object fillUserInfo(HttpServletRequest request)
    {
        boolean c = authorizeApi(); if (! c) return false;

        HttpSession session = request.getSession();
        TransactionInfo transaction = (TransactionInfo) session.getAttribute("transaction");

        User user = userService.findBy_id(request.getParameter("userId"));
        if (user == null) return false;
        transaction.setUser(user);

        Order order = orderService.create(authed(), user, transaction.getTotal());
        transaction.setOrder(order);

        session.setAttribute("transaction", transaction);

        return true;
    }

    @PostMapping(value = "/validate")
    public Object validateTransaction(HttpServletRequest request)
    {
        boolean c = authorizeApi(); if (! c) return false;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Apikey " + apiKey);
        String responseBody = (new RestTemplate()).exchange(
                endpoint,
                HttpMethod.GET,
                new HttpEntity<>("", headers),
                String.class
        ).getBody();
        TransactionResponse response;
        try {
            response = (new ObjectMapper()).readValue(responseBody, TransactionResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpSession session = request.getSession();
        TransactionInfo transaction = (TransactionInfo) session.getAttribute("transaction");
        Order order = transaction.getOrder();

        if (order.isAfter10MinsFromCreatedAt()) {
            session.setAttribute("transaction", null);
            session.setAttribute("state", null);

            orderService.updateTimeout(order);

            Map<String, Object> data = new HashMap<>();
            data.put("status", false);

            return data;
        }

        int total = transaction.getTotal().intValue();
        String orderId = transaction.getOrder().get_id();
        List<TransactionRecord> records = response.getData().getRecords();
        boolean status = records.stream().anyMatch(record -> record.getAmount() >= total && record.getDescription().contains(orderId));
        if (status) {
            session.setAttribute("transaction", null);
            session.setAttribute("state", null);

            orderService.updatePaymentBankSuccess(order);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("status", status ? true : null);

        return data;
    }

}

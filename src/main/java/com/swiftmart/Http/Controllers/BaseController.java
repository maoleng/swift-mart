package com.swiftmart.Http.Controllers;

import com.swiftmart.Enums.UserRole;
import com.swiftmart.Enums.UserStatus;
import com.swiftmart.Models.User;
import com.swiftmart.Services.CategoryService;
import com.swiftmart.Services.ProductService;
import com.swiftmart.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

abstract public class BaseController
{
    protected UserService userService;
    protected ProductService productService;
    protected CategoryService categoryService;

    final Logger logger = LoggerFactory.getLogger(getClass());
    @SafeVarargs
    public final <T> void log(T... messages)
    {
        System.out.println("================================================");
        for (T message : messages) {
            System.out.println(message);
        }
        System.out.println("================================================");
    }

    protected void saveSession(User user)
    {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
        session.setAttribute("authed", user);
    }

    protected boolean alreadyLogin()
    {
        return authed() != null;
    }

    protected String authorize(UserRole ...roles)
    {
        User user = authed();
        if (user == null) {
            return "redirect:/auth/login";
        }
        if (user.getStatus().equals(UserStatus.CHANGING_PASSWORD.name())) {
            return "redirect:/auth/create-password";
        }

        if (roles.length > 0) {
            for (UserRole role : roles) {
                if (user.getRole().equals(role.name())) {
                    return null;
                }
            }

            return "redirect:/error?code=403&message=You are not allowed to access this page";
        }

        return null;
    }


    protected User authed()
    {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();

        return (User) session.getAttribute("authed");
    }

}

package com.swiftmart.Http.Controllers;

import com.swiftmart.Enums.UserStatus;
import com.swiftmart.Http.Requests.LoginRequest;
import com.swiftmart.Http.Requests.RegisterRequest;
import com.swiftmart.Models.User;
import com.swiftmart.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping(value = "/auth")
public class AuthController extends BaseController
{

    @Autowired
    public AuthController(UserService userService)
    {
        this.userService = userService;
    }

    @Value("${app.salt}")
    private String salt;

    @GetMapping(value= "/login")
    public String login()
    {
        return alreadyLogin() ? "redirect:/" : "auth/login";
    }

    @PostMapping(value= "/login")
    public String login(@Valid LoginRequest request, HttpServletRequest rq)
    {
        User authed = userService.authenticate(request);
        if (authed == null) {
            return "redirect:/auth/login?error=Wrong username or password";
        }
        if (authed.getStatus().equals(UserStatus.DISABLE.name())) {
            return "redirect:/auth/login?error=Your account is disabled, please try again contact your manager.";
        }
        if (authed.getStatus().equals(UserStatus.INACTIVE.name())) {
            String code = rq.getParameter("code");
            if (code.isBlank()) {
                return "redirect:/auth/login?code=" + code + "&error=Please login by clicking on the link in your email";
            }

            Calendar now = Calendar.getInstance();
            now.add(Calendar.MINUTE, -1);
            Date oneMinBefore = now.getTime();
            if (oneMinBefore.after(authed.getSentAt())) {
                return "redirect:/auth/login?code=" + code + "&error=Link is expired, please tell manager for resending";
            }

            if (! code.equals(authed.getLoginCode(salt))) {
                return "redirect:/auth/login?code=" + code + "&error=Wrong link";
            }

            authed.setStatus(UserStatus.CHANGING_PASSWORD.name());
            userService.repository.getUserRepository().save(authed);
        }

        saveSession(authed);

        return "redirect:/";
    }

}

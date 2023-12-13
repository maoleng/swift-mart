package com.swiftmart.Http.Controllers;

import com.swiftmart.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract public class BaseController
{
    protected UserService userService;

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


}

package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Alex
 * @date 2021/11/2 17:01
 */
@Controller
public class LoginController {

    @RequestMapping("toLogin")
    public String toLogin() {
        return "login";
    }

}

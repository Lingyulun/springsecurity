package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 挥霍的人生
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(){
        return "/index";
    }
    @RequestMapping("/admin")
    public String admin(){
        return "/admin";
    }
    @RequestMapping("/user")
    public String user(){
        return "/user";
    }
}

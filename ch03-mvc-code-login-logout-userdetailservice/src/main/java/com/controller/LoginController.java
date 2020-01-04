package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 挥霍的人生
 */
@Controller
public class LoginController {
    @RequestMapping("/loginView")
    public String loginView(){
        return "loginview";
    }
    @RequestMapping("/loginsuccess")
    public String loginsuccess(){
        return "loginsuccess";
    }


    @RequestMapping("/loginfail")
    public String loginFail(){
        return "loginfail";
    }
}

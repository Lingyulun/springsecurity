package com.controller.api;

import com.vo.ResponseVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 挥霍的人生
 */
@RestController
public class ApiDemoController {
    @RequestMapping("/api/index")
    public ResponseVO index(){
        return new ResponseVO("200","api/index",null);
    }

    @RequestMapping("/api/user")
    public ResponseVO user(){
        return new ResponseVO("200","api/user",null);
    }

    @RequestMapping("/api/admin")
    public ResponseVO admin(){
        return new ResponseVO("200","api/admin",null);
    }
}

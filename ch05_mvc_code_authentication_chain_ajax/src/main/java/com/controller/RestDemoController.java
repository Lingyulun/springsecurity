package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vo.ResponseVO;

/**
 * @author 挥霍的人生
 */
@RestController
public class RestDemoController {
    @RequestMapping("/api/query")
    public ResponseVO responseVo(){
        ResponseVO vo=new ResponseVO("200", "admin", "guanlidata");
        return vo;
    }
}

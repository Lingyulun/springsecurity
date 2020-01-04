package com.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 挥霍的人生
 */
public class Main {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String result=passwordEncoder.encode("123");
        System.out.println("result = " + result);
    }
}

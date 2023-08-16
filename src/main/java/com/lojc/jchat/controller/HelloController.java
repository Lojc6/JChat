package com.lojc.jchat.controller;

/*
 *@Auther Lojc
 *@Date 2023/8/2
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping("/hello")
    public String hello(){
        return "hello JChat";
    }

}

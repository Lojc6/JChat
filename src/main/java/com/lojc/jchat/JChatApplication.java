package com.lojc.jchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.lojc","org.n3r.idworker"})
public class JChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(JChatApplication.class, args);
    }

}

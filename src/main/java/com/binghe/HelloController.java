package com.binghe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        System.out.println("요청 처리 (HandlerAdapter)");
        return "Hello " + "binghe";
    }

}

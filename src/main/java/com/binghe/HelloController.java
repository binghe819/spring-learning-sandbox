package com.binghe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // RequestMapping을 사용하고 아무런 HTTP 메서드를 정의하지 않으면 모든 메서드를 처리할 수 있다.
    @RequestMapping("/hello")
    public String hello() {
        return "Hello";
    }

    // RequestMapping의 method를 이용해서 원하는 HTTP 메서드만을 처리할 수 있다.
    @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
    public String test() {
        return "Test";
    }


}

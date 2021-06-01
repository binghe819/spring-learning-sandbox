package com.binghe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OptionsController {

    @GetMapping("/options/test")
    public String optionsGet() {
        return "hello";
    }

    @PostMapping("/options/test")
    public String helloPost() {
        return "hello";
    }
}

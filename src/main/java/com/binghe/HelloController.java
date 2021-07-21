package com.binghe;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<Response> hello(@RequestParam String name) {
        return ResponseEntity.ok(new Response(name));
    }

    @PostMapping("/hello")
    public ResponseEntity<Response> helloPost(@RequestBody Request request) {
        String message = request.getMessage();
        System.out.println("####" + message);
        return ResponseEntity.ok(new Response(message));
    }
}

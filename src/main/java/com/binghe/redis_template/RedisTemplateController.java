package com.binghe.redis_template;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RedisTemplateController {

    private RedisTemplateService redisTemplateService;

    public RedisTemplateController(RedisTemplateService redisTemplateService) {
        this.redisTemplateService = redisTemplateService;
    }

    @GetMapping("/redis/template")
    public ResponseEntity<String> redist(@RequestParam String key, @RequestParam String value) {
        String response = redisTemplateService.save(key, value);
        return ResponseEntity.ok().body(response);
    }

}

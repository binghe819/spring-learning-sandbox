package com.binghe.method_arguments;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 * @제목 : WebRequest
 *
 * @내용 : Servlet API를 직접 사용하지 않고 요청 또는 응답 자체에 접근 가능한 API.
 *        Servlet API (`HttpServletRequest`, `HttpServletResponse`)를 감싸고 있다고 생각하면 된다.
 */
@RestController
public class WebRequestController {

    @GetMapping("/test/webrequest")
    public String webRequestGet(WebRequest request) {
        String test = request.getHeader("test");
        return "WebRequest Test - " + test;
    }

    @PostMapping("/test/webrequest")
    public String webRequestPost(WebRequest request) {
        return "WebRequest Test - ";
    }
}

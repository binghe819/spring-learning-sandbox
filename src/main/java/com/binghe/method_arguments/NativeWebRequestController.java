package com.binghe.method_arguments;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * @제목 : NativeWebRequest
 *
 * @내용 : Servlet API를 직접 사용하지 않고 요청 또는 응답 자체에 접근 가능한 API.
 *        Servlet API (`HttpServletRequest`, `HttpServletResponse`)를 감싸고 있다고 생각하면 된다.
 */
@RestController
public class NativeWebRequestController {

    @GetMapping("/test/nativewebrequest")
    public String nativeWebReqeuest(NativeWebRequest nativeWebRequest) {
        String test = nativeWebRequest.getHeader("test");
        return "NativeWebRequest Test - " + test;
    }
}

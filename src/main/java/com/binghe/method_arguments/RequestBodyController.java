package com.binghe.method_arguments;

import com.binghe.domain.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @제목 : @RequestBody
 *
 * @내용 : 요청 본문(body)에 들어있는 데이터를 HttpMessageConverter를 통해 변환한 객체로 받아올 수 있다.
 *        Spring MVC 설정을 해주어야 사용이 가능하다.
 */
@RestController
public class RequestBodyController {

    @PostMapping("/test/requestbody")
    public ResponseEntity<Event> requestBody(@RequestBody Event event) {
        return ResponseEntity.ok(event);
    }
}

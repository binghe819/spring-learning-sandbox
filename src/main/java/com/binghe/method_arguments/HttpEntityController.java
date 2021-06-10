package com.binghe.method_arguments;

import com.binghe.domain.Event;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpEntityController {

    @PostMapping("/test/httpentity")
    public Event httpEntitiy(HttpEntity<Event> request) {
        String contentType = request.getHeaders().getContentType().toString();
        System.out.println("@@@@ HttpEntity Header에서 Content-type 꺼낸 결과 : " + contentType);
        return request.getBody();
    }
}

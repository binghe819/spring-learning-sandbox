package com.binghe;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediaTypeController {

    // consume: 특정한 타입의 데이터를 담고 있는 요청만 처리하는 핸들러 (요청의 Content-type으로 필터링한다)
    @RequestMapping(value = "/mediatype/test/consume", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String consume() {
        return "mediatype-consume";
    }

    // produce: 특정한 타입의 응답을 만드는 핸들러 (요청의 Accept로 필터링한다)
    @RequestMapping(value = "/mediatype/test/produce", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String produce() {
        return "mediatype-produce";
    }
}

package com.binghe.method_arguments;

import com.binghe.domain.Event;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @제목 : @RequestParam
 *
 * @내용 : 요청 매개변수(쿼리 매개변수)와 폼 데이터를 메서드 아규먼트로 받아올 수 있다.
 */
@RestController
public class RequestParamController {

    @GetMapping("/test/requestparam")
    public Event requestParam(@RequestParam(value = "id") Integer id, @RequestParam(value = "name") String name) {
        return new Event(id, name);
    }

    @GetMapping("/test/requestparam/default")
    public Event requestParamDefault(@RequestParam(required = false, defaultValue = "0") Integer id) {
        return new Event(id, "default");
    }

    @GetMapping("/test/requestparam/map")
    public Event requestParamByMap(@RequestParam Map<String, String> param) {
        Integer id = Integer.parseInt(param.get("id"));
        String name = param.get("name");
        return new Event(id, name);
    }

    @GetMapping("/test/requestparam/binding")
    public Event requestParamByBinding(Event event) {
        return event;
    }
}

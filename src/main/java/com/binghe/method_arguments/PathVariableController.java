package com.binghe.method_arguments;

import com.binghe.domain.Event;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @제목 : @PathVariable
 *
 * @내용 : 요청 URI 패턴의 일부를 핸들러 메서드 아규먼트로 받는 방법
 *        Optional을 통해 값이 존재할 때와 존재하지 않을 때의 분기를 나눌 수 있다.
 */

@RestController
public class PathVariableController {

    @GetMapping("/test/pathvariable/{id}")
    public Event pathVariable(@PathVariable Integer id) {
        Event event = new Event(id, "binghe");
        return event;
    }

    @GetMapping("/test/pathvariable/optional/{id}")
    public Event pathVariableOptional(@PathVariable Optional<String> id) {
        if (id.isEmpty()) {
            return new Event(-1, "empty");
        }
        return new Event(Integer.parseInt(id.get()), "binghe");
    }
}

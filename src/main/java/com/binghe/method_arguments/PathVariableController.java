package com.binghe.method_arguments;

import com.binghe.domain.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @제목 : @PathVariable
 *
 * @내용 :
 */

@RestController
public class PathVariableController {

    @GetMapping("/test/pathvariable/{id}")
    public Event pathVariable(@PathVariable Integer id) {
        Event event = new Event(id, "binghe");
        return event;
    }
}

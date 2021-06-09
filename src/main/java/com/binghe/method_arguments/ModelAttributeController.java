package com.binghe.method_arguments;

import com.binghe.domain.Event;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @제목 : @ModelAttribute
 *
 * @내용 : 여러 곳에 있는 단순 타입 데이터를 복합 타입 객체로 받아오거나 해당 객체를 새로 만들 때 사용할 수 있다. (요청 매개변수)
 *
 */

@RestController
public class ModelAttributeController {

    @PostMapping("/test/modelattribute")
    public Event modelAttribute(@ModelAttribute Event event) {
        return event;
    }
}

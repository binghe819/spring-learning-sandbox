package com.binghe.method_arguments;

import com.binghe.domain.Event;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BindingResultController {

    @PostMapping("/test/binding/modelattribute")
    public ResponseEntity<Event> bingdingResultModelAttribute(@ModelAttribute @Valid Event event, BindingResult bindingResult ) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(event);
    }
}

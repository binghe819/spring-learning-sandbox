package com.binghe;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EventValidator implements Validator {

    // 어떤 타입의 객체를 검증할 때 사용할 것인지 결정하는 메서드
    @Override
    public boolean supports(Class<?> clazz) {
        // return Event.class.equals(clazz);
        // return Event.class.isstanceOf(clazz);
        return Event.class.isAssignableFrom(clazz);
    }

    // 실제 검증 로직을 이 안에서 구현한다.
    @Override
    public void validate(Object target, Errors errors) {
        // 구현할 때 ValidatorUtils를 사용하면 편리하다.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "notempty", "Empty title is not allowed");
    }
}

package com.binghe;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

/**
 * 지금은 잘 사용하지 않는 PropertyEditor의 구현체
 * 문제는 getValue와 setValue의 값이 모두 PropertyEditor가 가지고 있는 상태값이다 (스레드 안전하지 않다) -> 빈으로 등록하면 안된다
 */
public class EventEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        Event event = (Event) getValue();
        return event.getId().toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new Event(Integer.parseInt(text)));
    }
}

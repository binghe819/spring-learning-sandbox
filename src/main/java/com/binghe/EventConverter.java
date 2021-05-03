package com.binghe;

import org.springframework.core.convert.converter.Converter;

/**
 * converter
 */
public class EventConverter {

    /**
     * String -> Event Converter
     */
    public static class StringToEventConverter implements Converter<String, Event> {

        @Override
        public Event convert(String source) {
            return new Event(Integer.parseInt(source));
        }
    }

    /**
     * Event -> String Converter
     */
    public static class EventToStringConverter implements Converter<Event, String> {

        @Override
        public String convert(Event source) {
            return source.getId().toString();
        }
    }
}

package com.binghe.domain;

public class Event {

    private Integer id;
    private String name;

    public Event() {
    }

    public Event(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

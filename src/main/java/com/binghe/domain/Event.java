package com.binghe.domain;

import javax.validation.constraints.Min;

public class Event {

    @Min(1)
    private Integer id;
    private String name;

    public Event() {
    }

    public Event(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

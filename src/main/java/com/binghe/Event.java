package com.binghe;

public class Event {

    private Integer id;

    private String title;

    public Event() {
    }

    public Event(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}

package com.binghe.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class User {

    @NotBlank(message = "이름은 없을 수 없습니다.")
    private String name;

    @AssertTrue
    private boolean working;

    @Min(value = 1, message = "나이는 1살보다 적을 수 없습니다.")
    @Max(value = 150, message = "나이는 150살보다 많을 수 없습니다.")
    private int age;

    @Email(message = "이메일 형식이어야 합니다.")
    private String email;

    @Valid
    private List<Article> articles;

    public User() {
    }

    public User(String name, boolean working, int age, String email) {
        this.name = name;
        this.working = working;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}

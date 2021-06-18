package com.binghe.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class Article {

    @NotBlank(message = "게시물의 제목은 비어있을 수 없습니다.")
    private String title;
    @NotBlank(message = "게시물의 내용은 비어있을 수 없습니다.")
    private String content;
    @NotNull
    private LocalDateTime createdAt;

    public Article() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

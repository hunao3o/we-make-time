package com.gaenodap3.wmtapi.dto;

import com.gaenodap3.wmtapi.domain.Posts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    public Posts toEntity() {
        Posts posts = new Posts(title, content, author);
        return posts;
    }
}
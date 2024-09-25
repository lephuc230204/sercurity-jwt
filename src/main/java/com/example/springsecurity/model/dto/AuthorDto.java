package com.example.springsecurity.model.dto;

import com.example.springsecurity.model.entity.Author;
import com.example.springsecurity.model.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDto {
    private long id;
    private String name;

    public static AuthorDto from(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .build();
    }
}

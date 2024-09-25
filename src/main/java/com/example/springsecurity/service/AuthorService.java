package com.example.springsecurity.service;

import com.example.springsecurity.model.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    AuthorDto createAuthor(AuthorDto request);
    AuthorDto getAuthorById(Long id);
    List<AuthorDto> getAuthors();
    AuthorDto updateAuthor(Long id, AuthorDto request);
    void deleteAuthor(Long id);

}

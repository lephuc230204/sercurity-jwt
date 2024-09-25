package com.example.springsecurity.service;

import com.example.springsecurity.model.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto request);
    BookDto getBookById(Long id);
    List<BookDto> getAllBooks();
    BookDto updateBook(Long id, BookDto request);
    void deleteBook(Long id);
}

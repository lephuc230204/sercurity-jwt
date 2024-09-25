package com.example.springsecurity.controllers;

import com.example.springsecurity.model.dto.BookDto;
import com.example.springsecurity.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v4/books")
@CrossOrigin("*")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookController {
    BookService bookService;

    @PostMapping("/add-new-book")
    public ResponseEntity<BookDto> createAuthor(@RequestBody BookDto request) {
        return ResponseEntity.ok(bookService.createBook(request));
    }

    // Lấy thông tin tác giả theo ID
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getAuthorById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    // Lấy danh sách tất cả tác giả
    @GetMapping
    public ResponseEntity<List<BookDto>> getAuthors() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // Cập nhật thông tin tác giả
    @PutMapping("/update-book/{id}")
    public ResponseEntity<BookDto> updateAuthor(@PathVariable("id") Long id, @RequestBody BookDto request) {
        return ResponseEntity.ok(bookService.updateBook(id, request));
    }

    // Xóa tác giả theo ID
    @DeleteMapping("/delete-book/{id}")
    String deleteAuthor(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "Book deleted";
    }
}

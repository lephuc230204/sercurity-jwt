package com.example.springsecurity.controllers;


import com.example.springsecurity.model.dto.BookDto;
import com.example.springsecurity.model.dto.UserDto;
import com.example.springsecurity.model.payload.ChangePasswordForm;
import com.example.springsecurity.model.payload.UserForm;
import com.example.springsecurity.service.BookService;
import com.example.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final BookService bookService;

    // Cập nhật thông tin cá nhân
    @PutMapping("/update/me")
    public ResponseEntity<String> updateCurrentUser(@RequestBody UserForm form, Principal principal) {String result = userService.updateMe(principal, form);
        return ResponseEntity.ok(result);
    }
    // cập nhật mk
    @PatchMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordForm form, Principal principal) {String newPassWord = userService.changePassword(form, principal);
        return ResponseEntity.ok(newPassWord);
    }
    // Lấy thông tin người dùng hiện tại
    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(Principal principal) { UserDto user = userService.getMe(principal);
        return ResponseEntity.ok(user);
    }
    // thêm sách
    @PostMapping("/book/add-new-book")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto request) {
        return ResponseEntity.ok(bookService.createBook(request));
    }

    // Lấy thông tin sách theo ID
    @GetMapping("/book/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    // Lấy danh sách tất cả sách

    @GetMapping("/book")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // Cập nhật thông tin sách
    @PutMapping("/book/update-book/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") Long id, @RequestBody BookDto request) {
        return ResponseEntity.ok(bookService.updateBook(id, request));
    }

    // Xóa sách theo ID
    @DeleteMapping("/book/delete-book/{id}")
    String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "Book deleted";
    }
}

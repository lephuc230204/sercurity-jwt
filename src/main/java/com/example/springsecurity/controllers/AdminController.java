package com.example.springsecurity.controllers;


import com.example.springsecurity.model.dto.AuthorDto;
import com.example.springsecurity.model.dto.CategoryDto;
import com.example.springsecurity.model.dto.UserDto;
import com.example.springsecurity.model.payload.UserForm;
import com.example.springsecurity.service.AuthorService;
import com.example.springsecurity.service.CategoryService;
import com.example.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final AuthorService authorService;
    @Autowired
    private final CategoryService categoryService;


    // XEM
    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() { List<UserDto> users = userService.getAll();
        return ResponseEntity.ok(users);
    }
    // XÓA
    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {String result = userService.delete(id);
        return ResponseEntity.ok(result);
    }
    // SỬA
    @PutMapping("/update-user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserForm form) {
        String result = userService.update(id, form);
        return ResponseEntity.ok(result);
    }
    // XEM CHI TẾT
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {UserDto user = userService.getById(id);
        return ResponseEntity.ok(user);
    }
    // THÊM
    @PostMapping("/author/add-new-author")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto request) {
        return ResponseEntity.ok(authorService.createAuthor(request));
    }

    // Lấy thông tin tác giả theo ID
    @GetMapping("/author/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    // Lấy danh sách tất cả tác giả
    @GetMapping("/author")
    public ResponseEntity<List<AuthorDto>> getAuthors() {
        return ResponseEntity.ok(authorService.getAuthors());
    }

    // Cập nhật thông tin tác giả
    @PutMapping("/author/update-author/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto request) {
        return ResponseEntity.ok(authorService.updateAuthor(id, request));
    }

    // Xóa tác giả theo ID
    @DeleteMapping("/author/delete-author/{id}")
    String deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
        return "Author deleted";
    }

    // Tạo tác giả mới
    @PostMapping("/category/add-new-category")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto request) {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

    // Lấy thông tin tác giả theo ID
    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    // Lấy danh sách tất cả tác giả
    @GetMapping("category")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    // Cập nhật thông tin tác giả
    @PutMapping("/category/update-category/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDto request) {
        return ResponseEntity.ok(categoryService.updateCategory(id, request));
    }

    // Xóa tác giả theo ID
    @DeleteMapping("/category/delete-category/{id}")
    String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return "Category deleted";
    }
    }
//
//    // SEARCH
//    @GetMapping("/search")
//    public ResponseEntity<List<UserDto>> searchUsers(@RequestParam String query) {List<UserDto> users = userService.searchUser(query);
//        return ResponseEntity.ok(users);
//    }


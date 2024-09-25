package com.example.springsecurity.service.impl;

import com.example.springsecurity.model.dto.BookDto;
import com.example.springsecurity.model.entity.Author;
import com.example.springsecurity.model.entity.Book;
import com.example.springsecurity.model.entity.Category;
import com.example.springsecurity.model.entity.User;
import com.example.springsecurity.repository.AuthorRepository;
import com.example.springsecurity.repository.BookRepository;
import com.example.springsecurity.repository.CategoryRepository;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;
    AuthorRepository authorRepository;
    UserRepository userRepository;
    CategoryRepository categoryRepository;

    @Override
    public BookDto createBook(BookDto request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        if (bookRepository.existsByTitle(request.getTitle())) {
            throw new IllegalArgumentException("Book with this title already exists");
        }

        Book book = BookDto.toEntity(request, author, user, category);
        return BookDto.from(bookRepository.save(book));
    }

    @Override
    public BookDto getBookById(Long id) {
        return bookRepository.findById(id)
                .map(BookDto::from)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookDto::from)
                .toList();
    }

    @Override
    public BookDto updateBook(Long id, BookDto request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(request.getTitle());
        book.setDescription(request.getDescription());
        book.setImage(request.getImage());
        book.setPostDate(request.getPostDate());
        book.setStatus(request.getStatus());
        book.setAmount(request.getAmount());
        book.setRequireApproval(request.isRequireApproval());
        book.setAuthor(author);
        book.setUser(user);
        book.setCategory(category);

        return BookDto.from(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book with ID " + id + " not found.");
        }
        bookRepository.deleteById(id);
    }
}

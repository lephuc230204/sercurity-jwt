package com.example.springsecurity.service.impl;

import com.example.springsecurity.model.dto.AuthorDto;
import com.example.springsecurity.model.entity.Author;
import com.example.springsecurity.repository.AuthorRepository;
import com.example.springsecurity.service.AuthorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorServiceImpl implements AuthorService {
    AuthorRepository authorRepository;

    @Override
    public AuthorDto createAuthor(AuthorDto request) {
        if (authorRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("Author with the name '" + request.getName() + "' already exists.");
        }

        Author author = Author.builder()
                .name(request.getName())
                .build();

        Author savedAuthor = authorRepository.save(author);
        return AuthorDto.from(savedAuthor);
    }

    @Override
    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author with ID " + id + " not found."));
        return AuthorDto.from(author);
    }

    @Override
    public List<AuthorDto> getAuthors() {
        return authorRepository.findAll().stream()
                .map(AuthorDto::from)
                .toList();
    }

    @Override
    public AuthorDto updateAuthor(Long id, AuthorDto request) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author with ID " + id + " not found."));

        // Cập nhật thông tin author trực tiếp
        author.setName(request.getName());

        Author updatedAuthor = authorRepository.save(author);
        return AuthorDto.from(updatedAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new IllegalArgumentException("Author with ID " + id + " does not exist.");
        }
        authorRepository.deleteById(id);
    }
}

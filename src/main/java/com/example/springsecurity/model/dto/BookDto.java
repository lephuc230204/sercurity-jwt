package com.example.springsecurity.model.dto;

import com.example.springsecurity.model.entity.Author;
import com.example.springsecurity.model.entity.Book;
import com.example.springsecurity.model.entity.Category;
import com.example.springsecurity.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private long id;
    private String title;
    private String description;
    private String image;
    private Date postDate;
    private String status;
    private Integer amount;
    private boolean requireApproval;
    private long userId;
    private long categoryId;
    private long authorId;

    // Phương thức tĩnh chuyển từ Book entity sang BookDto
    public static BookDto from(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .image(book.getImage())
                .postDate(book.getPostDate())
                .status(book.getStatus())
                .amount(book.getAmount())
                .requireApproval(book.isRequireApproval())
                .userId(book.getUser().getId())
                .categoryId(book.getCategory().getId())
                .authorId(book.getAuthor().getId())
                .build();
    }

    // Phương thức tĩnh chuyển từ BookDto sang Book entity
    public static Book toEntity(BookDto dto, Author author, User user, Category category) {
        return Book.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .image(dto.getImage())
                .postDate(dto.getPostDate())
                .status(dto.getStatus())
                .amount(dto.getAmount())
                .requireApproval(dto.isRequireApproval())
                .author(author)
                .user(user)
                .category(category)
                .build();
    }
}

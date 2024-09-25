package com.example.springsecurity.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String image;
    private Date postDate;
    private String status;
    private Integer amount;

    @Column(nullable = true)
    private boolean requireApproval;

    // Quan hệ Many-to-One với User
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    // Quan hệ Many-to-One với Category
    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    // Quan hệ Many-to-One với Author
    @ManyToOne
    @JoinColumn(name = "authorId", nullable = false)
    private Author author;

    // Trả về userId từ đối tượng User
    public long getUserId() {
        return user != null ? user.getId() : 0;
    }

    // Trả về categoryId từ đối tượng Category
    public long getCategoryId() {
        return category != null ? category.getId() : 0;
    }

    // Trả về authorId từ đối tượng Author
    public long getAuthorId() {
        return author != null ? author.getId() : 0;
    }
}

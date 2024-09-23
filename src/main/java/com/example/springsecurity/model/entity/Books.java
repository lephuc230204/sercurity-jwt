package com.example.springsecurity.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    private String title;

    @ManyToOne
    @JoinColumn(name = "AuthorID", nullable = false)
    private Author author;

    private String description;

    @ManyToOne
    @JoinColumn(name = "CategoryID", nullable = false)
    private Category category;

    private String image;

    private LocalDate postedDate;

    private String status;

    private Integer amount;

    private Boolean requireApproval;
}

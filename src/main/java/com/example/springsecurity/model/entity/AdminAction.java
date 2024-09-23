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
@Table(name = "AdminActions")
public class AdminAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actionId;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(nullable = false)
    private String actionType;

    @Column(nullable = false)
    private Long targetId;

    @Column(nullable = false)
    private LocalDate actionDate;

}

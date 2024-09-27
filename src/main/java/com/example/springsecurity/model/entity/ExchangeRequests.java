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
@Table(name = "ExchangeRequests")
public class ExchangeRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "BookID", nullable = false)
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RequestStatus requestStatus;

    private LocalDate requestDate;

    private LocalDate exchangeDate;

    // Enum for status
    public enum RequestStatus {
        PENDING, APPROVED, REJECTED, CANCELLED
    }
}

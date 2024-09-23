package com.example.springsecurity.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "RoomChats")
public class RoomChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "CreatorID", nullable = false)
    private User creator;

    private LocalDate createAt;

    @OneToMany(mappedBy = "roomChat")
    private List<Messages> messages;

    @OneToMany(mappedBy = "roomChat")
    private List<WebSocketConnection> webSocketConnections;
}

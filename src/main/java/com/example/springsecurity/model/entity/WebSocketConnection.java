package com.example.springsecurity.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "WebSocketConnections")
public class WebSocketConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "RoomChatID", nullable = false)
    private RoomChat roomChat;

    private String status;
}

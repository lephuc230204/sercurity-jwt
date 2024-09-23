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
@Table(name = "Messages")
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "SenderID", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "ReceiverID", nullable = false)
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "RoomChatID", nullable = false)
    private RoomChat roomChat;

    private String content;

    private LocalDate sentDate;

    private String readStatus;
}

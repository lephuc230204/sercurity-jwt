package com.example.springsecurity.model.dto;

import com.example.springsecurity.model.entity.Book;
import com.example.springsecurity.model.entity.ExchangeRequests;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Builder
@Data
public class ExchangeRequestDto {
    private Long bookId;
    private Long userID;
    private String requestStatus;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime requestDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime exchangeDate;

    public static ExchangeRequestDto from(ExchangeRequests exchangeRequest) {
        return ExchangeRequestDto.builder()
                .userID(exchangeRequest.getUser().getId())
                .bookId(exchangeRequest.getBook().getId())
                .requestStatus(exchangeRequest.getRequestStatus().toString())
                .build();
    }
}
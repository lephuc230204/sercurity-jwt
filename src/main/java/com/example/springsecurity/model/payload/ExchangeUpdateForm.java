package com.example.springsecurity.model.payload;

import com.example.springsecurity.model.entity.ExchangeRequests;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExchangeUpdateForm {
    private Long bookid;
    private ExchangeRequests.RequestStatus requestStatus;
    private LocalDate updateDate;
}

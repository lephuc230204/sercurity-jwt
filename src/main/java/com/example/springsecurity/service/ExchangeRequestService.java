package com.example.springsecurity.service;

import com.example.springsecurity.model.dto.ExchangeRequestDto;
import com.example.springsecurity.model.entity.ExchangeRequests;
import com.example.springsecurity.model.payload.ExchangeUpdateForm;

import java.security.Principal;
import java.util.List;

public interface ExchangeRequestService {
    ExchangeRequestDto createExchangeRequest(Principal principal, Long bookId);
    ExchangeRequestDto updateExchangeRequest(Principal principal, ExchangeUpdateForm form, Long id);
    List<ExchangeRequestDto> checkExchangeRequest(Principal principal);
    List<ExchangeRequests> getExchangeRequestsByBookOwnerId(List<Long> bookIds);
    List<ExchangeRequestDto> getExchangeRequestsByBookOwner(Principal principal);


}

package com.example.springsecurity.controllers;

import com.example.springsecurity.model.dto.ExchangeRequestDto;
import com.example.springsecurity.model.payload.ExchangeUpdateForm;
import com.example.springsecurity.service.ExchangeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user/exchange-requests")
public class ExchangeRequestController {
    @Autowired
    private ExchangeRequestService exchangeRequestService;

    @PostMapping("/add/{bookId}")
    public ResponseEntity<ExchangeRequestDto> createRequest(@PathVariable Long bookId, Principal principal) {
        ExchangeRequestDto exchangeRequestDTO = exchangeRequestService.createExchangeRequest(principal, bookId);
        return new ResponseEntity<>(exchangeRequestDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{ExchangeId}")
    public ResponseEntity<ExchangeRequestDto> updateRequest(@RequestBody ExchangeUpdateForm form, @PathVariable Long ExchangeId, Principal principal) {
        ExchangeRequestDto updatedRequest = exchangeRequestService.updateExchangeRequest(principal, form, ExchangeId);
        return ResponseEntity.ok(updatedRequest);
    }

    @GetMapping("/checkYourOrder")
    public ResponseEntity<List<ExchangeRequestDto>> checkRequest(Principal principal) {List<ExchangeRequestDto> exchangeRequests = exchangeRequestService.checkExchangeRequest(principal);
        return ResponseEntity.ok(exchangeRequests);
    }

    @GetMapping("/checkYourBookOrdered")
    public ResponseEntity<List<ExchangeRequestDto>> checkRequestBook(Principal principal) {List<ExchangeRequestDto> exchangeRequests = exchangeRequestService.getExchangeRequestsByBookOwner(principal);
        return ResponseEntity.ok(exchangeRequests);
    }
}

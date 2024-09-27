package com.example.springsecurity.repository;

import com.example.springsecurity.model.entity.ExchangeRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExchangeRequestRepository extends JpaRepository<ExchangeRequests, Long> {
    // Bạn có thể thêm các phương thức tùy chỉnh nếu cần
    List<ExchangeRequests> findByUserId(Long userId);
    List<ExchangeRequests> findByBookIdIn(List<Long> bookIds);
}

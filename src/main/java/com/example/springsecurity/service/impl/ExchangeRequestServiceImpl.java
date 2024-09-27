package com.example.springsecurity.service.impl;

import com.example.springsecurity.model.dto.UserDto;
import com.example.springsecurity.model.entity.Book;
import com.example.springsecurity.model.entity.ExchangeRequests;
import com.example.springsecurity.model.entity.User;
import com.example.springsecurity.model.payload.ExchangeUpdateForm;
import com.example.springsecurity.repository.BookRepository;
import com.example.springsecurity.repository.ExchangeRequestRepository;
import com.example.springsecurity.model.dto.ExchangeRequestDto;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.service.ExchangeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeRequestServiceImpl implements ExchangeRequestService {

    @Autowired
    private ExchangeRequestRepository exchangeRequestRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private BookServiceImpl bookServiceImpl;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    public ExchangeRequestDto createExchangeRequest(Principal principal, Long bookId) {
        // Tìm người dùng dựa trên tên người dùng
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Book book = bookRepository.findById(bookId).orElse(null);


        // Tạo yêu cầu trao đổi
        ExchangeRequests request = new ExchangeRequests();
        request.setUser(user);
        request.setBook(book);
        request.setRequestStatus(ExchangeRequests.RequestStatus.PENDING);
        request.setRequestDate(LocalDate.now());

        // Lưu yêu cầu trao đổi vào cơ sở dữ liệu
        ExchangeRequests newRequest = exchangeRequestRepository.save(request);

        // Sử dụng phương thức from() để chuyển đổi sang DTO
        return ExchangeRequestDto.from(newRequest);
    }

    public ExchangeRequestDto updateExchangeRequest(Principal principal, ExchangeUpdateForm form, Long id) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Tìm yêu cầu trao đổi theo ID
        ExchangeRequests existingRequest = exchangeRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exchange request not found"));

        if (user.getId().equals(existingRequest.getUser().getId())) {
            // Cập nhật thông tin từ các tham số đầu vào
            if (form.getRequestStatus() != null) {
                existingRequest.setRequestStatus(form.getRequestStatus());
            }
            if (form.getUpdateDate() != null) {
                existingRequest.setExchangeDate(form.getUpdateDate());
            }

            // Lưu yêu cầu trao đổi đã cập nhật vào cơ sở dữ liệu
            ExchangeRequests updatedRequest = exchangeRequestRepository.save(existingRequest);

            // Sử dụng phương thức from() để chuyển đổi sang DTO
            return ExchangeRequestDto.from(updatedRequest);
        } else {
            System.out.println("User ID " + user.getId() + " does not match the request user ID " + existingRequest.getUser().getId());
            return null;
        }
    }

    public List<ExchangeRequestDto> checkExchangeRequest(Principal principal) {
        Long userId = userServiceImpl.getMe(principal).getId();
        List<ExchangeRequests> exchangeRequests = exchangeRequestRepository.findByUserId(userId);

        // In ra số lượng ExchangeRequest tìm thấy
        System.out.println("Found " + exchangeRequests.size() + " exchange requests for user ID: " + userId);

        // Sử dụng phương thức from() để chuyển đổi các ExchangeRequest thành ExchangeRequestDto
        return exchangeRequests.stream()
                .map(ExchangeRequestDto::from)
                .collect(Collectors.toList());
    }

    public List<ExchangeRequests> getExchangeRequestsByBookOwnerId(List<Long> bookIds) {
        return exchangeRequestRepository.findByBookIdIn(bookIds);
    }

    public List<ExchangeRequestDto> getExchangeRequestsByBookOwner(Principal principal) {
        // Lấy ID người dùng từ email
        UserDto userDto = userServiceImpl.getMe(principal);
        // Tạo đối tượng User từ UserDto
        User user = new User();
        user.setId(userDto.getId());
        // Tìm tất cả sách của người dùng này
        List<Book> books = bookServiceImpl.findBooksByUser(user);

        // Lấy danh sách các ID sách
        List<Long> bookIds = books.stream()
                .map(Book::getId)
                .collect(Collectors.toList());

        // Tìm tất cả yêu cầu trao đổi liên quan đến các ID sách này
        List<ExchangeRequests> exchangeRequests = getExchangeRequestsByBookOwnerId(bookIds);

        // Sử dụng phương thức from() để chuyển đổi các ExchangeRequest thành ExchangeRequestDto
        return exchangeRequests.stream()
                .map(ExchangeRequestDto::from)
                .collect(Collectors.toList());
    }
}
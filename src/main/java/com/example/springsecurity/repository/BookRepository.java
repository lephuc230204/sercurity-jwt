package com.example.springsecurity.repository;

import com.example.springsecurity.model.dto.UserDto;
import com.example.springsecurity.model.entity.Book;
import com.example.springsecurity.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitle(String title);
    List<Book> findByUser(User user);
}

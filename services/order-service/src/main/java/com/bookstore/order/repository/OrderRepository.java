package com.bookstore.order.repository;

import com.bookstore.order.entity.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<BookOrder, Long> {
    List<BookOrder> findByUserId(Long userId);
}


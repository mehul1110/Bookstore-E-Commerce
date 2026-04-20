package com.bookstore.order.service;

import com.bookstore.order.entity.BookOrder;
import com.bookstore.order.entity.OrderItem;
import com.bookstore.order.entity.OrderStatus;
import com.bookstore.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public BookOrder createDefaultOrder() {
        List<BookOrder> orders = orderRepository.findByUserId(1L);
        if (!orders.isEmpty()) {
            return orders.get(0);
        }

        BookOrder order = new BookOrder();
        order.setId(1001L);
        order.setUserId(1L);
        order.setStatus(OrderStatus.PENDING);
        OrderItem item = new OrderItem();
        item.setProductId(1L);
        item.setQuantity(1);
        item.setUnitPrice(BigDecimal.valueOf(599));
        item.setOrder(order);
        order.getItems().add(item);
        return order;
    }
}

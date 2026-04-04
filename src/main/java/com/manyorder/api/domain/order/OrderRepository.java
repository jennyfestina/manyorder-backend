package com.manyorder.api.domain.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manyorder.api.domain.customer.Customer;
import com.manyorder.api.domain.merchant.Merchant;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByMerchant(Merchant merchant);
    Optional<Order> findByMerchantAndId(Merchant merchant, Long id);
    List<Order> findByMerchantAndStatus(Merchant merchant, OrderStatus status);
    List<Order> findByMerchantAndCreatedAtBetween(Merchant merchant, LocalDateTime start, LocalDateTime end);
    
    List<Order> findByCustomer(Customer customer);
    Optional<Order> findByCustomerAndId(Customer customer, Long id);
    List<Order> findByCustomerAndStatusIn(Customer customer, List<OrderStatus> statuses);

    List<Order> findByStatus(OrderStatus status);
    List<Order> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
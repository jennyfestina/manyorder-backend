package com.manyorder.api.domain.payment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manyorder.api.domain.order.Order;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByOrder(Order order);
}
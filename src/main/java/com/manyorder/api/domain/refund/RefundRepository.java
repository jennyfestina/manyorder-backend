package com.manyorder.api.domain.refund;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manyorder.api.domain.order.Order;

public interface RefundRepository extends JpaRepository<Refund, Long> {
    List<Refund> findByOrder(Order order); //ReturnType<Entity> methodName(Parameters);
}
package com.manyorder.api.domain.shipping;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manyorder.api.domain.order.Order;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {
    List<Shipping> findByOrder(Order order);
}
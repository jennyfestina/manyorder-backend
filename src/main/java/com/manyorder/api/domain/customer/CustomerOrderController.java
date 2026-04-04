package com.manyorder.api.domain.customer;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manyorder.api.domain.order.CreateCustomerOrderRequest;
import com.manyorder.api.domain.order.OrderResponse;
import com.manyorder.api.domain.order.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer/orders")
public class CustomerOrderController {

    private final OrderService orderService;

    public CustomerOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Customer order history
    @GetMapping
    public List<OrderResponse> getOrders(@RequestParam Long customerId) {
        return orderService.getOrdersByCustomerId(customerId)
                .stream()
                .map(orderService::toResponse)
                .toList();
    }

    // One of customer's own orders
    @GetMapping("/detail")
    public OrderResponse getOrder(
            @RequestParam Long customerId,
            @RequestParam Long orderId
    ) {
        return orderService.toResponse(
                orderService.getOrderByCustomerId(customerId, orderId)
        );
    }

    // Customer active orders
    @GetMapping("/active")
    public List<OrderResponse> getActiveOrders(@RequestParam Long customerId) {
        return orderService.getActiveOrdersByCustomerId(customerId)
                .stream()
                .map(orderService::toResponse)
                .toList();
    }


    // Customer create order
    @PostMapping
    public OrderResponse createOrder(@Valid @RequestBody CreateCustomerOrderRequest request) {
        return orderService.createCustomerOrder(request);
    }
}

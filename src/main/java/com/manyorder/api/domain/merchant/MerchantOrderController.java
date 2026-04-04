package com.manyorder.api.domain.merchant;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manyorder.api.domain.order.CreateMerchantOrderRequest;
import com.manyorder.api.domain.order.OrderResponse;
import com.manyorder.api.domain.order.OrderService;
import com.manyorder.api.domain.order.OrderStatus;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/merchant/orders")
public class MerchantOrderController {

    private final OrderService orderService;

    public MerchantOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // All orders for merchant
    @GetMapping
    public List<OrderResponse> getOrders(@RequestParam Long merchantId) {
        return orderService.getOrdersByMerchantId(merchantId)
                .stream()
                .map(orderService::toResponse)
                .toList();
    }

    // One order
    @GetMapping("/detail")
    public OrderResponse getOrder(
            @RequestParam Long merchantId,
            @RequestParam Long orderId
    ) {
        return orderService.toResponse(
                orderService.getOrderByMerchantId(merchantId, orderId)
        );
    }

    // Filter by status
    @GetMapping("/status")
    public List<OrderResponse> getOrdersByStatus(
            @RequestParam Long merchantId,
            @RequestParam OrderStatus status
    ) {
        return orderService.getOrdersByMerchantAndStatus(merchantId, status)
                .stream()
                .map(orderService::toResponse)
                .toList();
    }

    // Merchant Create Order (Manually)
    @PostMapping
    public OrderResponse createOrder(@Valid @RequestBody CreateMerchantOrderRequest request) {
        return orderService.createMerchantOrder(request);
    }
}
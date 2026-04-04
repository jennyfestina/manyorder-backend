package com.manyorder.api.domain.order;

import java.time.LocalDateTime;

public class OrderResponse {

    private Long id;
    private Long customerId;
    private String customerName;
    private Long merchantId;
    private String merchantName;
    private OrderStatus status;
    private LocalDateTime createdAt;

    public OrderResponse(
            Long id,
            Long customerId,
            String customerName,
            Long merchantId,
            String merchantName,
            OrderStatus status,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
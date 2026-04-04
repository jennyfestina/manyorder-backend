package com.manyorder.api.domain.order;

import jakarta.validation.constraints.NotNull;

public class CreateCustomerOrderRequest {

    @NotNull
    private Long customerId;
    
    @NotNull
    private Long merchantId;

    public CreateCustomerOrderRequest() {
    }

    public CreateCustomerOrderRequest(Long customerId, Long merchantId) {
        this.customerId = customerId;
        this.merchantId = merchantId;
    }


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}
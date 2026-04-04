package com.manyorder.api.domain.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateMerchantOrderRequest {

    @NotNull
    private Long merchantId;

    @NotBlank
    private String customerName;
    private String email;
    private String phoneNumber;

    public CreateMerchantOrderRequest() {
    }

    public CreateMerchantOrderRequest(Long merchantId, String customerName, String email, String phoneNumber) {
        this.merchantId = merchantId;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
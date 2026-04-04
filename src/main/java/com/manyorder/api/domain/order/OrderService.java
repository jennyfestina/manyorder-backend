package com.manyorder.api.domain.order;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.manyorder.api.domain.customer.Customer;
import com.manyorder.api.domain.customer.CustomerRepository;
import com.manyorder.api.domain.merchant.Merchant;
import com.manyorder.api.domain.merchant.MerchantRepository;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MerchantRepository merchantRepository;
    private final CustomerRepository customerRepository;

    public OrderService(
        OrderRepository orderRepository,
        MerchantRepository merchantRepository,
        CustomerRepository customerRepository) {

        this.orderRepository = orderRepository;
        this.merchantRepository = merchantRepository;
        this.customerRepository = customerRepository;
    }

    // Merchant

    public List<Order> getOrdersByMerchantId(Long merchantId) {
        Merchant merchant = getMerchantById(merchantId);
        return orderRepository.findByMerchant(merchant);
    }

    public Order getOrderByMerchantId(Long merchantId, Long orderId) {
        Merchant merchant = getMerchantById(merchantId);

        return orderRepository.findByMerchantAndId(merchant, orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getOrdersByMerchantAndStatus(Long merchantId, OrderStatus status) {
        Merchant merchant = getMerchantById(merchantId);
        return orderRepository.findByMerchantAndStatus(merchant, status);
    }

    public List<Order> getOrdersByMerchantAndDateRange(Long merchantId, LocalDateTime start, LocalDateTime end) {
        Merchant merchant = getMerchantById(merchantId);
        return orderRepository.findByMerchantAndCreatedAtBetween(merchant, start, end);
    }

    // Customer

    public List<Order> getOrdersByCustomerId(Long customerId) {
        Customer customer = getCustomerById(customerId);
        return orderRepository.findByCustomer(customer);
    }

    public Order getOrderByCustomerId(Long customerId, Long orderId) {
        Customer customer = getCustomerById(customerId);

        return orderRepository.findByCustomerAndId(customer, orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getActiveOrdersByCustomerId(Long customerId) {
        Customer customer = getCustomerById(customerId);

        List<OrderStatus> activeStatuses = List.of(
                OrderStatus.UNFULFILLED,
                OrderStatus.READY,
                OrderStatus.OUT_FOR_DELIVERY
        );

        return orderRepository.findByCustomerAndStatusIn(customer, activeStatuses);
    }

    // Admin

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersForAdminByMerchantId(Long merchantId) {
        Merchant merchant = getMerchantById(merchantId);
        return orderRepository.findByMerchant(merchant);
    }

    public List<Order> getOrdersForAdminByCustomerId(Long customerId) {
        Customer customer = getCustomerById(customerId);
        return orderRepository.findByCustomer(customer);
    }

    public List<Order> getOrdersForAdminByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    public List<Order> getOrdersForAdminByDateRange(LocalDateTime start, LocalDateTime end) {
        return orderRepository.findByCreatedAtBetween(start, end);
    }

    // Shared helpers

    private Merchant getMerchantById(Long merchantId) {
        return merchantRepository.findById(merchantId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Merchant not found"));
    }

    private Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    public OrderResponse toResponse(Order order) {
    return new OrderResponse(
            order.getId(),
            order.getCustomer().getId(),
            order.getCustomer().getFullName(),
            order.getMerchant().getId(),
            order.getMerchant().getName(),
            order.getStatus(),
            order.getCreatedAt()
    );
    }   

    // Customer Create Order
   public OrderResponse createCustomerOrder(CreateCustomerOrderRequest request) {
    Customer customer = getCustomerById(request.getCustomerId());
    Merchant merchant = getMerchantById(request.getMerchantId());

    if (!customer.getMerchant().getId().equals(merchant.getId())) {
        throw new RuntimeException("Customer does not belong to this merchant");
    }

    Order order = new Order(customer, merchant);
    order.setStatus(OrderStatus.UNFULFILLED);

    Order savedOrder = orderRepository.save(order);
    return toResponse(savedOrder);
    }

    // Merchant Create Order (Manually)
    public OrderResponse createMerchantOrder(CreateMerchantOrderRequest request) {
    Merchant merchant = getMerchantById(request.getMerchantId());

    Customer customer = new Customer(
            merchant,
            request.getCustomerName(),
            request.getEmail(),
            request.getPhoneNumber()
    );

    Customer savedCustomer = customerRepository.save(customer);

    Order order = new Order(savedCustomer, merchant);
    order.setStatus(OrderStatus.UNFULFILLED);

    Order savedOrder = orderRepository.save(order);
    return toResponse(savedOrder);
    }

    
    
}


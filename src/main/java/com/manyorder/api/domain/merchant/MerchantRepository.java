package com.manyorder.api.domain.merchant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manyorder.api.domain.user.User;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    List<Merchant> findByOwner(User owner);
    long countByOwner(User owner);
}
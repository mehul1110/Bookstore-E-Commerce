package com.bookstore.customer.service;

import com.bookstore.customer.entity.CustomerProfile;
import com.bookstore.customer.repository.CustomerProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerProfileService {

    private final CustomerProfileRepository customerProfileRepository;

    public CustomerProfileService(CustomerProfileRepository customerProfileRepository) {
        this.customerProfileRepository = customerProfileRepository;
    }

    public CustomerProfile currentProfile() {
        return customerProfileRepository.findByUserId(1L).orElseGet(() -> {
            CustomerProfile profile = new CustomerProfile();
            profile.setId(1L);
            profile.setUserId(1L);
            profile.setPhone("+91-9999999999");
            profile.setPreference("paperback");
            return profile;
        });
    }
}


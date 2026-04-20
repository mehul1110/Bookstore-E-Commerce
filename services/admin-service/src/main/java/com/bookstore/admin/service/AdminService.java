package com.bookstore.admin.service;

import com.bookstore.admin.entity.Admin;
import com.bookstore.admin.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> admins() {
        List<Admin> admins = adminRepository.findAll();
        if (!admins.isEmpty()) {
            return admins;
        }
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setEmail("admin@bookstore.com");
        admin.setRole("ADMIN");
        return List.of(admin);
    }
}


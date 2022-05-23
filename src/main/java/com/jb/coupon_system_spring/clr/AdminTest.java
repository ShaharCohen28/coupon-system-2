package com.jb.coupon_system_spring.clr;

import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Customer;
import com.jb.coupon_system_spring.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@RequiredArgsConstructor
public class AdminTest implements CommandLineRunner {
    private final AdminService adminService;
    @Override
    public void run(String... args) throws Exception {
        addCompanies();
        addCustomers();
    }

    private void addCustomers() {
        for (int counter = 0; counter < 3; counter++) {
            Customer customer= Customer
                    .builder()
                    .firstName("customer"+counter)
                    .lastName("customer"+counter)
                    .email("customer"+counter+"@test.com")
                    .password("customer")
                    .build();
            adminService.addCustomer(customer);
        }
    }

    private void addCompanies() {
        for (int counter = 0; counter < 3; counter++) {
            Company company= Company
                    .builder()
                    .name("company"+counter)
                    .email("company"+counter+"@test.com")
                    .password("company")
                    .build();
            adminService.addCompany(company);
        }
    }
}

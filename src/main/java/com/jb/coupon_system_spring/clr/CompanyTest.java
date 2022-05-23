package com.jb.coupon_system_spring.clr;

import com.jb.coupon_system_spring.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@RequiredArgsConstructor
public class CompanyTest implements CommandLineRunner {
    private final CompanyService companyService;
    @Override
    public void run(String... args) throws Exception {

    }
}

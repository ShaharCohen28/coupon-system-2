package com.jb.coupon_system_spring.clr;

import com.jb.coupon_system_spring.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
@RequiredArgsConstructor
public class CustomerTest implements CommandLineRunner {
    private final CustomerService customerService;
    @Override
    public void run(String... args) throws Exception {

    }
}

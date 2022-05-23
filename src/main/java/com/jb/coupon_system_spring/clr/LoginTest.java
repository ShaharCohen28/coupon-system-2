package com.jb.coupon_system_spring.clr;

import com.jb.coupon_system_spring.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
@RequiredArgsConstructor
public class LoginTest implements CommandLineRunner {
    private final LoginService loginService;
    @Override
    public void run(String... args) throws Exception {

    }
}

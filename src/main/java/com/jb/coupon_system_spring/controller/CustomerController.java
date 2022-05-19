package com.jb.coupon_system_spring.controller;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.ClientType;
import com.jb.coupon_system_spring.beans.ErrorTypes;
import com.jb.coupon_system_spring.exceptions.CouponException;
import com.jb.coupon_system_spring.exceptions.CustomerException;
import com.jb.coupon_system_spring.exceptions.LoginException;
import com.jb.coupon_system_spring.service.CustomerService;
import com.jb.coupon_system_spring.util.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class CustomerController {

    private final CustomerService customerService;
    private final JWT jwt;
    private final ClientType clientType=ClientType.CUSTOMER;

    @PostMapping("/purchaseCoupon/{couponId}")
    public ResponseEntity<?> addCouponPurchase
            (@RequestHeader(name = "Authorization") String token, @PathVariable int couponId)
            throws CouponException, LoginException {
        jwt.checkClient(customerService,token,clientType);
        customerService.purchaseCoupon(couponId);
        return ResponseEntity.ok()
                .header("Authorization", customerService.getToken())
                .build();
    }

    @GetMapping("/customerCoupons")
    public ResponseEntity<?> getCustomerCoupons
            (@RequestHeader(name = "Authorization") String token)
            throws CustomerException, LoginException {
        jwt.checkClient(customerService,token,clientType);
        return ResponseEntity.ok()
                .header("Authorization", customerService.getToken())
                .body(customerService.getCustomerCoupon());
    }

    @GetMapping("/customerCouponsByCategory/{category}")
    public ResponseEntity<?> getCustomerCouponsByCategory
            (@RequestHeader(name = "Authorization") String token,@PathVariable Category category)
            throws CustomerException, LoginException {
        jwt.checkClient(customerService,token,clientType);
        return ResponseEntity.ok()
                .header("Authorization", customerService.getToken())
                .body(customerService.getCustomerCouponByCategory(category));
    }

    @GetMapping("/customerCouponsByPrice/{price}")
    public ResponseEntity<?> getCustomerCouponsByPrice
            (@RequestHeader(name = "Authorization") String token,@PathVariable double price)
            throws CustomerException, LoginException {
        jwt.checkClient(customerService,token,clientType);
        return ResponseEntity.ok()
                .header("Authorization", customerService.getToken())
                .body(customerService.getCustomerCouponByPrice(price));
    }

    @GetMapping("/Details")
    public ResponseEntity<?> customerDetails
            (@RequestHeader(name = "Authorization") String token)
            throws CustomerException, LoginException {
        jwt.checkClient(customerService,token,clientType);
        return ResponseEntity.ok()
                .header("Authorization", customerService.getToken())
                .body(customerService.getCustomerDetails());
    }

}
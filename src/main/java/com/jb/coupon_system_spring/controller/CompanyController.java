package com.jb.coupon_system_spring.controller;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.ClientType;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.beans.ErrorTypes;
import com.jb.coupon_system_spring.exceptions.CompanyException;
import com.jb.coupon_system_spring.exceptions.LoginException;
import com.jb.coupon_system_spring.service.CompanyService;
import com.jb.coupon_system_spring.util.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class CompanyController {
    private final CompanyService companyService;
    private final JWT jwt;
    private final ClientType clientType=ClientType.COMPANY;

    @GetMapping("/Details")
    public ResponseEntity<?> companyDetails
            (@RequestHeader(name = "Authorization") String token)
            throws CompanyException, LoginException {
        jwt.checkClient(companyService,token,clientType);
        return ResponseEntity.ok()
                .header("Authorization", companyService.getToken())
                .body(companyService.companyDetails());
    }

    @PostMapping("/addCoupon")
    public ResponseEntity<?> addNewCoupon
            (@RequestHeader(name = "Authorization")String token, @RequestBody Coupon coupon)
            throws LoginException, CompanyException {
        jwt.checkClient(companyService,token,clientType);
        companyService.addCoupon(coupon);
        return ResponseEntity.ok()
                .header("Authorization", companyService.getToken())
                .build();

    }

    @PutMapping("/updateCoupon")
    public ResponseEntity<?> updateCoupon
            (@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon)
            throws CompanyException, LoginException {
        jwt.checkClient(companyService,token,clientType);
        companyService.updateCoupon(coupon);
        return ResponseEntity.ok()
                .header("Authorization", companyService.getToken())
                .build();
    }

    @DeleteMapping("/deleteCoupon/{id}")
    public ResponseEntity<?> deleteCoupon
            (@RequestHeader(name = "Authorization")String token,@PathVariable int id)
            throws CompanyException, LoginException {
        jwt.checkClient(companyService,token,clientType);
        companyService.deleteCoupon(id);
        return ResponseEntity.ok()
                .header("Authorization", companyService.getToken())
                .build();
    }

    @GetMapping("/allCoupons")
    public ResponseEntity<?> allCoupons
            (@RequestHeader(name = "Authorization")String token)
            throws CompanyException, LoginException {
        jwt.checkClient(companyService,token,clientType);
        return ResponseEntity.ok()
                .header("Authorization", companyService.getToken())
                .body(companyService.allCompanyCoupons());
    }

    @GetMapping("/couponsByCategory/{category}")
    public ResponseEntity<?> couponsByCategory
            (@RequestHeader(name = "Authorization")String token,@PathVariable Category category)
            throws CompanyException, LoginException {
        jwt.checkClient(companyService,token,clientType);
        return ResponseEntity.ok()
                .header("Authorization", companyService.getToken())
                .body(companyService.allCompanyCouponsByCategory(category));
    }

    @GetMapping("/couponsByPrice/{price}")
    public ResponseEntity<?> couponsByPrice(
            @RequestHeader(name = "Authorization")String token,@PathVariable double price)
            throws CompanyException, LoginException {
        jwt.checkClient(companyService,token,clientType);
        return ResponseEntity.ok()
                .header("Authorization", companyService.getToken())
                .body(companyService.allCompanyCouponsByPrice(price));
    }

}

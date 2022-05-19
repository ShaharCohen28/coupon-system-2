package com.jb.coupon_system_spring.controller;

import com.jb.coupon_system_spring.beans.ClientType;
import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Customer;
import com.jb.coupon_system_spring.beans.ErrorTypes;
import com.jb.coupon_system_spring.exceptions.AdminException;
import com.jb.coupon_system_spring.exceptions.LoginException;
import com.jb.coupon_system_spring.service.AdminService;
import com.jb.coupon_system_spring.util.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class AdminController {
    private final JWT jwt;
    private final AdminService adminService;
    private final ClientType clientType=ClientType.ADMIN;

    @GetMapping("/allCompanies")
    public ResponseEntity<?> getAllCompanies
            (@RequestHeader(name = "Authorization") String token)
            throws LoginException {
        jwt.checkClient(adminService,token,clientType);
        return ResponseEntity.ok()
                .header("Authorization", adminService.getToken())
                .body(adminService.getAllCompanies());
    }

    @GetMapping("/allCustomers")
    public ResponseEntity<?> getAllCustomers
            (@RequestHeader(name = "Authorization") String token)
            throws LoginException {
        jwt.checkClient(adminService,token,clientType);
        return ResponseEntity.ok()
                .header("Authorization", adminService.getToken())
                .body(adminService.getAllCustomers());
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<?> getCompanyById
            (@RequestHeader(name = "Authorization") String token, @PathVariable int id)
            throws AdminException, LoginException {
        jwt.checkClient(adminService,token,clientType);
        return ResponseEntity.ok()
                .header("Authorization", adminService.getToken())
                .body(adminService.getCompanyById(id));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomerById
            (@RequestHeader(name = "Authorization") String token, @PathVariable int id)
            throws AdminException, LoginException {
        jwt.checkClient(adminService,token,clientType);
        return ResponseEntity.ok()
                .header("Authorization", adminService.getToken())
                .body(adminService.getCustomerById(id));

    }

    @PostMapping("/company/add")
    public ResponseEntity<?> addCompany
            (@RequestHeader(name = "Authorization") String token, @RequestBody Company company)
            throws LoginException {
        jwt.checkClient(adminService,token,clientType);
        adminService.addCompany(company);
        return ResponseEntity.ok()
                .header("Authorization", adminService.getToken())
                .build();


    }

    @PostMapping("/customer/add")
    public ResponseEntity<?> addCustomer
            (@RequestHeader(name = "Authorization") String token, @RequestBody Customer customer)
            throws LoginException {
        jwt.checkClient(adminService,token,clientType);
        adminService.addCustomer(customer);
        return ResponseEntity.ok()
                .header("Authorization", adminService.getToken())
                .build();
    }

    @PutMapping("/company/update")
    public ResponseEntity<?> updateCompany
            (@RequestHeader(name = "Authorization") String token, @RequestBody Company company)
            throws AdminException, LoginException {
        jwt.checkClient(adminService,token,clientType);
        adminService.updateCompany(company);
        return ResponseEntity.ok()
                .header("Authorization", adminService.getToken())
                .build();

    }

    @PutMapping("/customer/update")
    public ResponseEntity<?> updateCustomer
            (@RequestHeader(name = "Authorization") String token, @RequestBody Customer customer)
            throws AdminException, LoginException {
        jwt.checkClient(adminService,token,clientType);
        adminService.updateCustomer(customer);
        return ResponseEntity.ok()
                .header("Authorization", adminService.getToken())
                .build();
    }

    @DeleteMapping("/company/delete/{id}")
    public ResponseEntity<?> deleteCompany
            (@RequestHeader(name = "Authorization") String token, @PathVariable int id)
            throws AdminException, LoginException {
        jwt.checkClient(adminService,token,clientType);
        adminService.deleteCompany(id);
        return ResponseEntity.ok()
                .header("Authorization", adminService.getToken())
                .build();
    }

    @DeleteMapping("/customer/delete/{id}")
    public ResponseEntity<?> deleteCustomer
            (@RequestHeader(name = "Authorization") String token, @PathVariable int id)
            throws AdminException, LoginException {
        jwt.checkClient(adminService,token,clientType);
        adminService.deleteCustomer(id);
        return ResponseEntity.ok()
                .header("Authorization", adminService.getToken())
                .build();
    }

}

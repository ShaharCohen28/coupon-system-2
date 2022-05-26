package com.jb.coupon_system_spring.clr;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.exceptions.CouponException;
import com.jb.coupon_system_spring.exceptions.CustomerException;
import com.jb.coupon_system_spring.service.CustomerService;
import com.jb.coupon_system_spring.util.TablePrinter;
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
        purchaseCoupons();
        getAllCoupons();
        getAllCouponsByCategory();
        getAllCouponsByPrice();
        getDetails();
    }
    private void purchaseCoupons() {
        customerService.setClientId(1);
        for (int counter = 1; counter <= 5; counter++) {
            try {
                customerService.purchaseCoupon(counter);
            } catch (CouponException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private void getAllCoupons() {
        customerService.setClientId(1);
        try {
            TablePrinter.print(customerService.getCustomerCoupon());
        } catch (CustomerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getAllCouponsByCategory() {
        customerService.setClientId(1);
        try {
            TablePrinter.print(customerService.getCustomerCouponByCategory(Category.ELECTRICITY));
        } catch (CustomerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getAllCouponsByPrice() {
        customerService.setClientId(1);
        try {
            TablePrinter.print(customerService.getCustomerCouponByPrice(68.00));
        } catch (CustomerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getDetails() {
        customerService.setClientId(2);
        try {
            TablePrinter.print(customerService.getCustomerDetails());
        } catch (CustomerException e) {
            System.out.println(e.getMessage());
        }
    }
}

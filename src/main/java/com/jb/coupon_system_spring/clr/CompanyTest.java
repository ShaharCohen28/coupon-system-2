package com.jb.coupon_system_spring.clr;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.exceptions.CompanyException;
import com.jb.coupon_system_spring.service.CompanyService;
import com.jb.coupon_system_spring.util.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Order(2)
@RequiredArgsConstructor
public class CompanyTest implements CommandLineRunner {
    private final CompanyService companyService;
    private final int ONE_DAY=1000*60*60*24;
    @Override
    public void run(String... args) throws Exception {
            addCoupons();
            updateCoupons();
            deleteCoupon();
            allCompanyCoupons();
            allCompanyCouponsByCategory();
            allCompanyCouponsByPrice();
            companyDetails();
    }

    public void addCoupons() throws CompanyException {
        for (int counter = 1;counter <= 2;counter++){
            companyService.setClientId(counter);
            for (int i=0;i<3;i++){
                Coupon coupon= Coupon
                        .builder()
                        .companyId(counter)
                        .category(Category.ELECTRICITY)
                        .amount(100)
                        .description("coupon number "+(i+1))
                        .title("coupon title "+(i+1))
                        .price(Math.random()*100+1)
                        .startDate(new Date(System.currentTimeMillis()))
                        .endDate(new Date(System.currentTimeMillis()+(int)(Math.random()*7+1)*ONE_DAY))
                        .image("image")
                        .build();
                companyService.addCoupon(coupon);
            }
        }

    }

    public void updateCoupons() throws CompanyException {
        companyService.setClientId(1);
        Coupon coupon = companyService.getCouponRepo().getById(1);
        coupon.setDescription("update coupon");
        companyService.updateCoupon(coupon);

    }

    public void deleteCoupon() throws CompanyException {
        companyService.setClientId(2);
        companyService.deleteCoupon(6);
    }

    public void allCompanyCoupons() throws CompanyException {
        companyService.setClientId(2);
        TablePrinter.print(companyService.allCompanyCoupons());
    }

    public void allCompanyCouponsByCategory() throws CompanyException {
        companyService.setClientId(1);
        TablePrinter.print(companyService.allCompanyCouponsByCategory(Category.ELECTRICITY));
    }

    public void allCompanyCouponsByPrice() throws CompanyException {
        companyService.setClientId(1);
        TablePrinter.print(companyService.allCompanyCouponsByPrice(70.0));
    }

    public void companyDetails() throws CompanyException {
        companyService.setClientId(1);
        TablePrinter.print(companyService.companyDetails());
    }


}

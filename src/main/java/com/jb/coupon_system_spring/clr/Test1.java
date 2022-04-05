package com.jb.coupon_system_spring.clr;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.repository.CompanyRepo;
import com.jb.coupon_system_spring.repository.CouponRepo;
import com.jb.coupon_system_spring.repository.CustomerRepo;
import com.jb.coupon_system_spring.util.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Order(1)
@RequiredArgsConstructor
public class Test1 implements CommandLineRunner {
    private final CompanyRepo companyRepo;
    private final CouponRepo couponRepo;
    private final int ONE_DAY=1000*60*60*24;
    @Override
    public void run(String... args) throws Exception {

        List<Coupon> coupons=new ArrayList<>();
        for (int counter = 0; counter < 3; counter++) {
            Coupon coupon= Coupon
                    .builder()
                    .category(Category.ELECTRICITY)
                    .amount(100)
//                    .companyId(1)
                    .description("coupon number "+(counter+1))
                    .title("coupon title "+(counter+1))
                    .price(Math.random()*100+1)
                    .startDate(new Date(System.currentTimeMillis()))
                    .endDate(new Date(System.currentTimeMillis()+(int)(Math.random()*7+1)*ONE_DAY))
                    .image("image")
                    .build();
            coupons.add(coupon);
        }

        for (int counter = 0; counter <3 ; counter++) {
            Company company= Company
                    .builder()
                    .email("company"+(counter+1)+"@company.com")
                    .password("company"+(counter+1))
                    .name("company"+(counter+1))
                    .build();
            companyRepo.save(company);
        }


            Optional<Company> singleCompany=companyRepo.findById(1);
            if(singleCompany.isPresent()){
                Company addTest = companyRepo.getById(1);
                addTest.setName("Update");
                addTest.setCoupons(coupons);
                companyRepo.save(addTest);
            }
            TablePrinter.print(couponRepo.findAll());

//        List<Company> companies=companyRepo.findAll();
//        TablePrinter.print(companies);
//
//        Optional<Company> singleCompany=companyRepo.findById(1);
//        if(singleCompany.isPresent()){
//            TablePrinter.print(singleCompany);
//            companyRepo.deleteById(singleCompany.get().getId());
//        }
//        companies=companyRepo.findAll();
//        TablePrinter.print(companies);

    }
}

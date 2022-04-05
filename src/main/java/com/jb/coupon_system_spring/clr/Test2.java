package com.jb.coupon_system_spring.clr;

import com.jb.coupon_system_spring.beans.Customer;
import com.jb.coupon_system_spring.repository.CustomerRepo;
import com.jb.coupon_system_spring.util.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//@Component
//@Order(2)
@RequiredArgsConstructor
public class Test2 implements CommandLineRunner {
    private final CustomerRepo customerRepo;

    @Override
    public void run(String... args) throws Exception {
        for (int counter = 0; counter <3 ; counter++) {
            Customer customer= Customer
                    .builder()
                    .email("customer"+(counter+1)+"@customer.com")
                    .password("customer"+(counter+1))
                    .firstName("customer first"+(counter+1))
                    .lastName("customer last"+(counter+1))
                    .build();
            customerRepo.save(customer);
        }
        List<Customer> customers =customerRepo.findAll();
        TablePrinter.print(customers);

        Optional<Customer> singleCustomer=customerRepo.findById(1);
        if(singleCustomer.isPresent()){
            TablePrinter.print(singleCustomer);
            customerRepo.deleteById(singleCustomer.get().getId());
        }
        customers =customerRepo.findAll();
        TablePrinter.print(customers);
    }
}

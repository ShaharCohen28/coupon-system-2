package com.jb.coupon_system_spring.beans;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    @Singular
    private List<Coupon> coupons = new ArrayList<>();

    public void setCoupons(List<Coupon> coupons) {
        for (int i =0;i<coupons.size();i++){
            coupons.get(i).setCompanyId(getId());
        }
        this.coupons = coupons;
    }
}



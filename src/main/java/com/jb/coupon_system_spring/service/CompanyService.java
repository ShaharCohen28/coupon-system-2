package com.jb.coupon_system_spring.service;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.beans.ErrorTypes;
import com.jb.coupon_system_spring.exceptions.CompanyException;
import com.jb.coupon_system_spring.service.interfaces.CompanyServiceInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class CompanyService extends ClientService implements CompanyServiceInterface {


    //private int companyId;


    @Override
    public void addCoupon(Coupon coupon) throws CompanyException {
        //todo: how to add coupon only to the current company
//        if(coupon.getCompanyId()==this.clientId) {
//            couponRepo.save(coupon);
//        }else {
//            throw new CompanyException(ErrorTypes.WRONG_COMPANY.getMessage());
//        }
        if(!companyRepo.existsById(coupon.getCompanyId())){
            throw new CompanyException(ErrorTypes.COMPANY_NOT_EXIST.getMessage());
        }
        if(coupon.getCompanyId()!=this.clientId){
            throw new CompanyException(ErrorTypes.WRONG_COMPANY.getMessage());
        }
        couponRepo.save(coupon);

    }

    @Override
    public void updateCoupon(Coupon coupon) throws CompanyException {
//        if (companyRepo.existsById(coupon.getCompanyId())) {
//            if (couponRepo.existsById(coupon.getId())) {
//                couponRepo.save(coupon);
//            } else {
//                throw new CompanyExceptions(ErrorTypes.COUPON_NOT_EXIST.getMessage());
//            }
//        } else {
//            throw new CompanyExceptions(ErrorTypes.COMPANY_NOT_EXIST.getMessage());
//        }
        if(couponRepo.existsById(coupon.getId())){
            if(!companyRepo.existsById(coupon.getId())){
                throw new CompanyException(ErrorTypes.COMPANY_NOT_EXIST.getMessage());
            } else if (coupon.getCompanyId()!=this.clientId) {
                throw new CompanyException(ErrorTypes.UNCHANGED_VALUE.getMessage());
            }else {
                couponRepo.save(coupon);
            }
        }else{
            throw new CompanyException(ErrorTypes.COUPON_NOT_EXIST.getMessage());
        }

    }

    @Override
    public void deleteCoupon(int couponId) throws CompanyException {
        if (couponRepo.existsById(couponId)) {
            if (couponRepo.getById(couponId).getCompanyId() == this.clientId) {
                couponRepo.deleteCouponPurchase(couponId);
                couponRepo.deleteById(couponId);
            } else {
                throw new CompanyException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
            }
        } else {
            throw new CompanyException(ErrorTypes.COUPON_NOT_EXIST.getMessage());
        }
    }

    @Override
    public List<Coupon> allCompanyCoupons() throws CompanyException {
        if (companyRepo.existsById(this.clientId)) {
            return couponRepo.findByCompanyId(this.clientId);
        } else {
            throw new CompanyException(ErrorTypes.COMPANY_NOT_EXIST.getMessage());
        }
    }

    @Override
    public List<Coupon> allCompanyCouponsByCategory(Category category) throws CompanyException {
        if (companyRepo.existsById(this.clientId)) {
            return couponRepo.findByCompanyIdAndCategory(this.clientId, category);
        } else {
            throw new CompanyException(ErrorTypes.COMPANY_NOT_EXIST.getMessage());
        }
    }

    @Override
    public List<Coupon> allCompanyCouponsByPrice(double price) throws CompanyException {
        if (companyRepo.existsById(this.clientId)) {
            return couponRepo.findByCompanyIdAndPriceLessThanEqual(this.clientId, price);
        } else {
            throw new CompanyException(ErrorTypes.COMPANY_NOT_EXIST.getMessage());
        }
    }

    @Override
    public Company companyDetails() throws CompanyException {
//        Optional<Company> company = companyRepo.findById(this.companyId);
        Optional<Company> company = companyRepo.findById(this.clientId);
        if (company.isPresent()) {
            return company.get();
        } else {
            throw new CompanyException(ErrorTypes.COMPANY_NOT_EXIST.getMessage());
        }
    }
}

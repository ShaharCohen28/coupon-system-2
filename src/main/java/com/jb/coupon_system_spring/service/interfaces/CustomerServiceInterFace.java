package com.jb.coupon_system_spring.service.interfaces;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.beans.Customer;
import com.jb.coupon_system_spring.exceptions.CouponException;
import com.jb.coupon_system_spring.exceptions.CustomerException;

import java.util.List;

public interface CustomerServiceInterFace {
     Coupon purchaseCoupon(int couponId) throws CouponException;
     List<Coupon> getCustomerCoupon() throws CustomerException;
     List<Coupon> getCustomerCouponByCategory(Category category) throws CustomerException;
     List<Coupon> getCustomerCouponByPrice(double price) throws CustomerException;
     Customer getCustomerDetails() throws CustomerException;



}

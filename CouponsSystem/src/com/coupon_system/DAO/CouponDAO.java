package com.coupon_system.DAO;

import java.sql.SQLException;
import java.util.List;

import com.coupon_system.beans.Coupon;
import com.coupon_system.beans.CustomerVsCoupon;

public interface CouponDAO {
	void AddCoupon(Coupon coupon) throws SQLException;

	void UpdeatCoupon(int couponID, Coupon coupon) throws SQLException;

	void DeleteCoupon(int couponID) throws SQLException;

	List<Coupon> getAllCoupons() throws SQLException;
	
	Coupon GetOneCoupon(int couponID) throws SQLException;
	
	void AddCouponPurchase(int customerID, int couponID) throws SQLException;
	
	void DeleteCouponPurchase(int customerID, int couponID) throws SQLException;

	List<CustomerVsCoupon> getAllCustomerVsCoupons() throws SQLException;

}

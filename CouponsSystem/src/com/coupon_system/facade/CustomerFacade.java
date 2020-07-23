package com.coupon_system.facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.coupon_system.beans.Category;
import com.coupon_system.beans.Coupon;
import com.coupon_system.beans.Customer;
import com.coupon_system.beans.CustomerVsCoupon;
import com.coupon_system.exceptions.purchaseCouponsException;

public class CustomerFacade extends ClientFacade {
	private int customerID;

	public CustomerFacade() {
		super();

	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	@Override
	public boolean login(String email, String password) {
		return this.customerDAO.IsCustomerExist(email, password);
	}

	public void purchaseCoupon(Coupon coupon) throws Exception {

		List<CustomerVsCoupon> customerVsCoupons = couponDAO.getAllCustomerVsCoupons();
		for (CustomerVsCoupon customerVsCoupon : customerVsCoupons) {
			if (customerVsCoupon.getCouponID() == coupon.getId()) {
				throw new purchaseCouponsException("you can not purchase more then one");
			}

			Coupon fromdb = couponDAO.GetOneCoupon(coupon.getId());
			if (fromdb.getAmmount() <= 0) {
				throw new purchaseCouponsException("you cannot purchase coupon when ammount=0");

			}
			if (fromdb.getEnd_date().before(new Date())) {
				throw new purchaseCouponsException("you cannot purchase coupon when the date is expaierd");
			}
			System.out.println("it's sims that you can purchase this coupon");
			System.out.println(fromdb);
			System.out.println(fromdb.getAmmount() - 1);
			System.out.println(fromdb);
			couponDAO.UpdeatCoupon(fromdb.getId(), fromdb);
			couponDAO.AddCouponPurchase(this.customerID, coupon.getId());
			System.out.println("done");

		}
	}

	public List<Coupon> getCustomerCoupons() throws SQLException {
		List<Coupon> result = new ArrayList<>();
		List<CustomerVsCoupon> customerVsCoupons = this.couponDAO.getAllCustomerVsCoupons();
		for (CustomerVsCoupon customerVsCoupon : customerVsCoupons) {
			if (customerVsCoupon.getCustomerID() == this.customerID) {
				result.add(this.couponDAO.GetOneCoupon(customerVsCoupon.getCouponID()));
			}

		}
		return result;
	}

	public List<Coupon> getCustomerCoupons(Category category) throws SQLException {
		List<Coupon> source = new ArrayList<>();
		List<Coupon> result = new ArrayList<>();
		List<CustomerVsCoupon> customerVsCoupons = this.couponDAO.getAllCustomerVsCoupons();
		for (CustomerVsCoupon customerVsCoupon : customerVsCoupons) {
			if (customerVsCoupon.getCustomerID() == this.customerID) {
				source.add(this.couponDAO.GetOneCoupon(customerVsCoupon.getCouponID()));
			}
			for (Coupon coupon : source) {
				if (coupon.getCategory().equals(category)) {
					result.add(coupon);
				}
			}

		}
		return result;
	}

	public List<Coupon> getCustomerCoupons(double maxPrice) throws SQLException {
		List<Coupon> source = new ArrayList<>();
		List<Coupon> result = new ArrayList<>();
		List<CustomerVsCoupon> customerVsCoupons = this.couponDAO.getAllCustomerVsCoupons();
		for (CustomerVsCoupon customerVsCoupon : customerVsCoupons) {
			if (customerVsCoupon.getCustomerID() == this.customerID) {
				source.add(this.couponDAO.GetOneCoupon(customerVsCoupon.getCouponID()));
			}
			for (Coupon coupon : source) {
				if (coupon.getPrice() <= maxPrice) {
					result.add(coupon);
				}
			}

		}
		return result;
	}

	public Customer getCustomerDetails() throws SQLException {
		Customer customer = this.customerDAO.GetOneCustomer(customerID);
		customer.setCoupons(getCustomerCoupons());
		return customer;
	}

	public int getCutomerID(String email, String password) throws SQLException {
		Customer customer = this.customerDAO.GetOneCustomer(customerID);
		return customer.getId();
	}

}

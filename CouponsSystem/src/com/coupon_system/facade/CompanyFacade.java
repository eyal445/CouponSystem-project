package com.coupon_system.facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coupon_system.beans.Category;
import com.coupon_system.beans.Company;
import com.coupon_system.beans.Coupon;
import com.coupon_system.beans.Customer;
import com.coupon_system.beans.CustomerVsCoupon;
import com.coupon_system.exceptions.NotAllowdException;

public class CompanyFacade extends ClientFacade {

	private int compamyID;

	public CompanyFacade() {
		super();
	}

	public int getCompamyID() {
		return compamyID;
	}

	public void setCompamyID(int compamyID) {
		this.compamyID = compamyID;
	}

	@Override
	public boolean login(String email, String password) {
		return this.companyDAO.IsCompanyExist(email, password);
	}

	public void addCoupon(Coupon coupon) throws Exception {
		List<Coupon> coupons = couponDAO.getAllCoupons();
		for (Coupon c1 : coupons) {
			if (c1.getTitle().equals(coupon.getTitle()) && c1.getCompany_id() == coupon.getCompany_id()) {
				throw new NotAllowdException(
						"you can't add a new coupon to a company that have a coupon with the same title");
			}
		}

		couponDAO.AddCoupon(coupon);
	}

	public void updateCoupon(Coupon coupon) throws Exception {
		List<Coupon> coupons = couponDAO.getAllCoupons();
		for (Coupon c1 : coupons) {
			if (c1.getId() != coupon.getId()) {
				this.couponDAO.UpdeatCoupon(coupon.getId(), coupon);
			}
			if (c1.getCompany_id() != coupon.getCompany_id()) {
				throw new NotAllowdException("you can't change the companyID");
			}

			throw new NotAllowdException("you can't change the id of the coupon");
		}

	}

	public void deleteCoupon(int couponID) throws Exception {
		List<CustomerVsCoupon> customerVsCoupons = couponDAO.getAllCustomerVsCoupons();
		List<Customer> customers = customerDAO.getAllCustomers();
		List<Customer> result = new ArrayList<>();
		for (CustomerVsCoupon customerVsCoupon : customerVsCoupons) {
			if (customerVsCoupon.getCouponID() == couponID) {
				for (Customer customer : customers) {
					if (customer.getId() == customerVsCoupon.getCustomerID()) {
						result.add(customer);
					}
				}
				for (Customer customer : result) {
					this.couponDAO.DeleteCouponPurchase(customer.getId(), couponID);
				}

			}
		}

		this.couponDAO.DeleteCoupon(couponID);
	}

	public List<Coupon> getCompanyCoupons() throws Exception {
		List<Coupon> result = new ArrayList<>();
		List<Coupon> coupons = this.couponDAO.getAllCoupons();
		for (Coupon coupon : coupons) {
			if (coupon.getCompany_id() == this.compamyID) {
				result.add(coupon);
			}
		}
		return result;
	}

	public List<Coupon> getCompanyCoupons(Category category) throws Exception {
		List<Coupon> source = new ArrayList<>();
		List<Coupon> result = new ArrayList<>();
		List<Coupon> coupons = this.couponDAO.getAllCoupons();
		for (Coupon coupon : coupons) {
			if (coupon.getCompany_id() == this.compamyID) {
				source.add(this.couponDAO.GetOneCoupon(coupon.getCompany_id()));
			}
			for (Coupon coupon1 : source) {
				if (coupon1.getCategory().equals(category)) {
					result.add(coupon1);
				}
			}
		}
		return result;
	}

	public List<Coupon> getCompanyCoupons(double maxPrice) throws Exception {
		List<Coupon> source = new ArrayList<>();
		List<Coupon> result = new ArrayList<>();
		List<Coupon> coupons = this.couponDAO.getAllCoupons();
		for (Coupon coupon : coupons) {
			if (coupon.getCompany_id() == this.compamyID) {
				source.add(this.couponDAO.GetOneCoupon(coupon.getCompany_id()));
			}
			for (Coupon coupon1 : source) {
				if (coupon1.getPrice() <= maxPrice) {
					result.add(coupon1);
				}
			}
		}
		return result;
	}

	public Company getCompanyDetails() throws Exception {
		Company company = this.companyDAO.GetOneCompany(compamyID);
		company.setCoupons(getCompanyCoupons());
		return company;
	}

	public int getCompanyID(String email, String password) throws SQLException {
		Company company = this.companyDAO.GetOneCompany(compamyID);
		return company.getId();

	}
}

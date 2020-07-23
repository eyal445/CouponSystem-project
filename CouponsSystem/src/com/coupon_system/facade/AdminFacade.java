package com.coupon_system.facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coupon_system.beans.Company;
import com.coupon_system.beans.Coupon;
import com.coupon_system.beans.Customer;
import com.coupon_system.beans.CustomerVsCoupon;
import com.coupon_system.exceptions.AlreadyExsistException;
import com.coupon_system.exceptions.NotAllowdException;

public class AdminFacade extends ClientFacade {

	public AdminFacade() {
		super();
	}

	@Override
	public boolean login(String email, String password) {
		if (email == "admin@gmail.com" && password == "admin") {
			return true;
		}
		return false;
	}

	public void addCompany(Company company) throws Exception {
		List<Company> companies = companyDAO.getAllCompanies();
		for (Company c1 : companies) {
			if (c1.getName().equals(company.getName())) {
				throw new AlreadyExsistException("there is another company with the same name");

			}
			if (c1.getEmail().equals(company.getEmail())) {
				throw new AlreadyExsistException("there is another company with the same email");
			}
		}
		companyDAO.AddCompany(company);

	}

	public void updateCompany(Company company) throws Exception {
		List<Company> companies = companyDAO.getAllCompanies();
		for (Company c1 : companies) {
			if (c1.equals(company)) {
				this.companyDAO.UpdeatCompany(company.getId(), company);
			}

		}
	}

	public void deleteCompany(int companyID) throws Exception {
		List<Coupon> coupons = this.couponDAO.getAllCoupons();
		for (Coupon coupon : coupons) {
			if (coupon.getCompany_id() == companyID) {
				this.couponDAO.DeleteCoupon(coupon.getId());
			}
		}
		this.companyDAO.DeleteCompany(companyID);
	}

	public List<Company> getAllCompanies() throws SQLException {
		return this.companyDAO.getAllCompanies();
	}

	public Company getOneCompany(int companyID) throws Exception {
		if (companyID <= 0) {
			throw new NotAllowdException("illigal company id");
		}
		List<Company> companies = companyDAO.getAllCompanies();
		for (Company company : companies) {
			if (company.getId() == companyID) {

				return company;
			}
		}
		return null;
	}

	public void addCustomer(Customer customer) throws Exception {
		List<Customer> customers = customerDAO.getAllCustomers();
		for (Customer c1 : customers) {
			if (c1.getEmail().equals(customer.getEmail())) {
				throw new NotAllowdException("you can't add a customer with an email that is already excist");
			}
		}
		this.customerDAO.AddCustomer(customer);
	}

	public void updateCustomer(Customer customer) throws Exception {
		List<Customer> customers = customerDAO.getAllCustomers();
		for (Customer c1 : customers) {
			if (c1.getId() != customer.getId()) {
				throw new NotAllowdException("you can't change the customer id");
			}
		}
		this.customerDAO.UpdeatCustomer(customer.getId(), customer);
	}

	public void deleteCustomer(int customerID) throws Exception {
		List<Coupon> result = new ArrayList<>();
		List<CustomerVsCoupon> customerVsCoupons = this.couponDAO.getAllCustomerVsCoupons();
		for (CustomerVsCoupon customerVsCoupon : customerVsCoupons) {
			if (customerVsCoupon.getCustomerID() == customerID) {
				result.add(this.couponDAO.GetOneCoupon(customerVsCoupon.getCouponID()));

				for (Coupon coupon : result) {
					this.couponDAO.DeleteCouponPurchase(customerID, coupon.getId());
				}
			}
		}

		this.customerDAO.DeleteCustomer(customerID);
	}

	public List<Customer> getAllCustomers() throws Exception {
		List<Customer> customers = this.customerDAO.getAllCustomers();
		return customers;
	}

	public Customer getOneCustomer(int customerID) throws Exception {
		Customer customer = this.customerDAO.GetOneCustomer(customerID);
		return customer;
	}
}

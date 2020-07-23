package com.coupon_system.facade;

import com.coupon_system.DAO.CompanyDAO;
import com.coupon_system.DAO.CouponDAO;
import com.coupon_system.DAO.CustomerDAO;
import com.coupon_system.DBDAO.CompanyDBDAO;
import com.coupon_system.DBDAO.CouponsDBDAO;
import com.coupon_system.DBDAO.CustumerDBDAO;

public abstract class ClientFacade {
	protected CompanyDAO companyDAO;
	protected CustomerDAO customerDAO;
	protected CouponDAO couponDAO;


	public ClientFacade() {
		this.companyDAO = new CompanyDBDAO();
		this.customerDAO = new CustumerDBDAO();
		this.couponDAO = new CouponsDBDAO();
	}


	public abstract boolean login(String email, String password);
}

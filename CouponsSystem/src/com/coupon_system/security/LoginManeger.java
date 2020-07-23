package com.coupon_system.security;

import java.sql.SQLException;

import com.coupon_system.facade.AdminFacade;
import com.coupon_system.facade.ClientFacade;
import com.coupon_system.facade.CompanyFacade;
import com.coupon_system.facade.CustomerFacade;

public class LoginManeger {
	private static LoginManeger instance = null;
	private ClientFacade clientFacade;

	private LoginManeger() {
System.out.println("login manager in action");
	}

	public static LoginManeger getInstance() {
		if (instance == null) {
			synchronized (LoginManeger.class) {
				if (instance == null) {
					instance = new LoginManeger();
				}
			}
		}
		return instance;
	}

	public ClientFacade login(String email, String password, ClientType clientType) throws SQLException {
		if (clientType == ClientType.ADMINISTRATOR) {
			clientFacade = (ClientFacade) new AdminFacade();
			if (clientFacade.login(email, password)) {
				return clientFacade;
			} else {
				return null;
			}

		} else if (clientType == ClientType.COMPANY) {
			if (clientFacade.login(email, password)) {
				int companyID = ((CompanyFacade) clientFacade).getCompanyID(email, password);
				System.out.println(""+companyID);
				((CompanyFacade) clientFacade).setCompamyID(companyID);
				return clientFacade;
			} else {
				return null;
			}
		} else if (clientType == ClientType.CUSTOMER) {
			if (clientFacade.login(email, password)) {
				int customerID = ((CustomerFacade) clientFacade).getCutomerID(email, password);
				((CustomerFacade) clientFacade).setCustomerID(customerID);
				return clientFacade;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}

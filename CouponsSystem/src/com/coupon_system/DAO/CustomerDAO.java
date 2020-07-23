package com.coupon_system.DAO;

import java.sql.SQLException;
import java.util.List;

import com.coupon_system.beans.Customer;

public interface CustomerDAO {
	boolean IsCustomerExist(String email, String password) throws SQLException;

	void AddCustomer(Customer customer) throws SQLException;

	void UpdeatCustomer(int customerID, Customer customer) throws SQLException;

	void DeleteCustomer(int customerID) throws SQLException;

	List<Customer> getAllCustomers() throws SQLException;

	Customer GetOneCustomer(int customerID) throws SQLException;

	int GetCustomerID(int customerID);
}

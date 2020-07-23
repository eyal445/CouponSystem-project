package com.coupon_system.DBDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.coupon_system.DAO.CustomerDAO;
import com.coupon_system.beans.Coupon;
import com.coupon_system.beans.Customer;
import com.coupon_system.utils.ConnectionPool;

public class CustumerDBDAO implements CustomerDAO {
	@Override
	public List<Customer> getAllCustomers() throws SQLException {
		List<Customer> customers = new ArrayList<Customer>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM `coupons_system`.`customers`";
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				Customer customer = new Customer();
				customer.setId(resultset.getInt(1));
				customer.setFirst_name(resultset.getString(2));
				customer.setLast_name(resultset.getString(3));
				customer.setEmail(resultset.getString(4));
				customer.setPassword(resultset.getString(5));
				customers.add(customer);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return customers;
	}

	@Override
	public boolean IsCustomerExist(String email, String password) throws SQLException {
		if (email != null && password != null) {
			List<Customer> customers = getAllCustomers();
			for (Customer customer : customers) {
				if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
					return true;
				}
			}

		}
		return false;
	}

	@Override
	public void AddCustomer(Customer customer) throws SQLException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "INSERT INTO `coupons_system`.`customers` (id, first_name, last_name, email, password) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customer.getId());
			statement.setString(2, customer.getFirst_name());
			statement.setString(3, customer.getLast_name());
			statement.setString(4, customer.getEmail());
			statement.setString(5, customer.getPassword());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	@Override
	public void UpdeatCustomer(int customerID, Customer customer) throws SQLException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "UPDATE `coupons_system`.`customers` SET first_name=?, last_name=?, email=?, password=? WHERE (id=?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, customer.getFirst_name());
			statement.setString(2, customer.getLast_name());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getPassword());
			statement.setInt(5, customerID);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	@Override
	public void DeleteCustomer(int customerID) throws SQLException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "DELETE FROM `coupons_system`.`customers` WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customerID);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	@Override
	public Customer GetOneCustomer(int customerID) {
		Customer customer = new Customer();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM `coupons_system`.`customers` WHERE `id` = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customerID);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				customer.setId(resultset.getInt(1));
				customer.setFirst_name(resultset.getString(2));
				customer.setLast_name(resultset.getString(3));
				customer.setEmail(resultset.getString(4));
				customer.setPassword(resultset.getString(5));
				customer.setCoupons(new ArrayList<Coupon>());

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return customer;

	}

	@Override
	public int GetCustomerID(int customerID) {
		Customer customer = new Customer();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM `coupons_system`.`customers` WHERE `id` = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customerID);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				customer.setId(resultset.getInt(1));
				customer.setFirst_name(resultset.getString(2));
				customer.setLast_name(resultset.getString(3));
				customer.setEmail(resultset.getString(4));
				customer.setPassword(resultset.getString(5));
				customer.setCoupons(new ArrayList<Coupon>());

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return customer.getId();
	}

}

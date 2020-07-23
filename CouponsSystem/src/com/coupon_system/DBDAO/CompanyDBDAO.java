package com.coupon_system.DBDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.coupon_system.DAO.CompanyDAO;
import com.coupon_system.beans.Company;
import com.coupon_system.beans.Coupon;
import com.coupon_system.utils.ConnectionPool;

public class CompanyDBDAO implements CompanyDAO {

	@Override
	public List<Company> getAllCompanies() throws SQLException {
		List<Company> companies = new ArrayList<Company>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM `coupons_system`.`companies`";
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				Company company = new Company();
				company.setId(resultset.getInt(1));
				company.setName(resultset.getString(2));
				company.setEmail(resultset.getString(3));
				company.setPassword(resultset.getString(4));
				companies.add(company);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return companies;
	}

	@Override
	public boolean IsCompanyExist(String email, String password) throws SQLException {
		if (email != null && password != null) {
			List<Company> companies = getAllCompanies();
			for (Company company : companies) {
				if (company.getEmail().equals(email) && company.getPassword().equals(password)) {
					return true;
				}

			}

		}
		return false;

	}

	@Override
	public void AddCompany(Company company) throws SQLException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "INSERT INTO `coupons_system`.`companies` (name, email, password) VALUES (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, company.getName());
			statement.setString(2, company.getEmail());
			statement.setString(3, company.getPassword());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	@Override
	public void UpdeatCompany(int id, Company company) throws SQLException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "UPDATE `coupons_system`.`companies` SET email = ?, password = ? WHERE (id = ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, company.getEmail());
			statement.setString(2, company.getPassword());
			statement.setInt(3, company.getId());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	@Override
	public void DeleteCompany(int id) throws SQLException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "DELETE FROM `coupons_system`.`companies` WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	@Override
	public Company GetOneCompany(int company_id) throws SQLException {
		Company company = new Company();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM `coupons_system`.`companies` WHERE `id` = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, company_id);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				company.setId(resultset.getInt(1));
				company.setName(resultset.getString(2));
				company.setEmail(resultset.getString(3));
				company.setPassword(resultset.getString(4));
				company.setCoupons(new ArrayList<Coupon>());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return company;
	}
}

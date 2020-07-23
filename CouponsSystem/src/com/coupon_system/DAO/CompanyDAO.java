package com.coupon_system.DAO;
import java.sql.SQLException;
import java.util.List;

import com.coupon_system.beans.Company;

public interface CompanyDAO {
	boolean IsCompanyExist(String email, String password) throws SQLException;
	
	void AddCompany(Company company) throws SQLException;

	void UpdeatCompany(int id, Company company) throws SQLException;

	void DeleteCompany(int id) throws SQLException;

	List<Company> getAllCompanies() throws SQLException;
	
	Company GetOneCompany (int company_id) throws SQLException;
}

package com.coupon_system.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.coupon_system.DBDAO.CategoryDBDAO;
import com.coupon_system.beans.Category;

public class General_DataBase {
	private static final String url = "jdbc:mysql://localhost:3306/coupons_system?creatDatabaseIfNotExsit=TRUE&useTimezone=TRUE&serverTimezone=UTC ";
	private static final String username = "root";
	private static final String password = "AL9281EY";
	private static Connection connection = null;

	private static final String CreatSchema = "CREATE SCHEMA `coupons_system`;";
	private static final String DropSchema = "DROP DATABASE `coupons_system`;";
	private static final String companies = "CREATE TABLE `coupons_system`.`companies` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `name` VARCHAR(45) NOT NULL,\r\n"
			+ "  `email` VARCHAR(45) NOT NULL,\r\n" + "  `password` VARCHAR(45) NOT NULL,\r\n"
			+ "  PRIMARY KEY (`id`));";
	private static final String customers = "CREATE TABLE `coupons_system`.`customers` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `first_name` VARCHAR(45) NOT NULL,\r\n"
			+ "  `last_name` VARCHAR(45) NOT NULL,\r\n" + "  `email` VARCHAR(45) NOT NULL,\r\n"
			+ "  `password` VARCHAR(45) NOT NULL,\r\n" + "  PRIMARY KEY (`id`)); ";
	private static final String categories = "CREATE TABLE `coupons_system`.`categories` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `name` VARCHAR(45) NOT NULL,\r\n"
			+ "  PRIMARY KEY (`id`)); ";
	private static final String coupons = "CREATE TABLE `coupons_system`.`coupons` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `company_id` INT NOT NULL,\r\n"
			+ "  `category_id` INT NOT NULL,\r\n" + "  `title` VARCHAR(45) NOT NULL,\r\n"
			+ "  `description` VARCHAR(45) NOT NULL,\r\n" + "  `start_date` DATE NOT NULL,\r\n"
			+ "  `end_date` DATE NOT NULL,\r\n" + "  `ammount` INT NOT NULL,\r\n" + "  `price` DOUBLE NOT NULL,\r\n"
			+ "  `image` VARCHAR(45) NOT NULL,\r\n" + "  PRIMARY KEY (`id`),\r\n"
			+ "  INDEX `company_id_idx` (`company_id` ASC) VISIBLE,\r\n"
			+ "  INDEX `category_id_idx` (`category_id` ASC) VISIBLE,\r\n" + "  CONSTRAINT `company_id`\r\n"
			+ "    FOREIGN KEY (`company_id`)\r\n" + "    REFERENCES `coupons_system`.`companies` (`id`)\r\n"
			+ "    ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION,\r\n" + "  CONSTRAINT `category_id`\r\n"
			+ "    FOREIGN KEY (`category_id`)\r\n" + "    REFERENCES `coupons_system`.`categories` (`id`)\r\n"
			+ "    ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION);";
	private static final String customers_VS_coupons = "CREATE TABLE `coupons_system`.`customers_vs_coupons` (\r\n"
			+ "  `customer_id` INT NOT NULL,\r\n" + "  `coupon_id` INT NOT NULL,\r\n"
			+ "  PRIMARY KEY (`customer_id`, `coupon_id`),\r\n"
			+ "  INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE,\r\n" + "  CONSTRAINT `customer_id`\r\n"
			+ "    FOREIGN KEY (`customer_id`)\r\n" + "    REFERENCES `coupons_system`.`customers` (`id`)\r\n"
			+ "    ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION,\r\n" + "  CONSTRAINT `coupon_id`\r\n"
			+ "    FOREIGN KEY (`coupon_id`)\r\n" + "    REFERENCES `coupons_system`.`coupons` (`id`)\r\n"
			+ "    ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION);";


	public static String getUrl() {
		return url;
	}

	public static String getUsername() {
		return username;
	}

	public static String getPassword() {
		return password;
	}

	public static void CreatAllTabls() throws SQLException {
		DropSchema();
		CreateSchema();
		CompaniesTable();
		CustomersTable();
		CategoriesTable();
		addCategories();
		CouponTable();
		CustomersVsCouponsTable();

	}

	public static void DropSchema() throws SQLException {
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = DropSchema;
			Statement statment = connection.createStatement();
			statment.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
	}

	public static void CreateSchema() throws SQLException {
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = CreatSchema;
			Statement statment = connection.createStatement();
			statment.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
	}

	public static void CompaniesTable() throws SQLException {
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = companies;
			Statement statment = connection.createStatement();
			statment.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	public static void CustomersTable() throws SQLException {
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = customers;
			Statement statment = connection.createStatement();
			statment.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	public static void CategoriesTable() throws SQLException {
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = categories;
			Statement statment = connection.createStatement();
			statment.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	public static void CouponTable() throws SQLException {
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = coupons;
			Statement statment = connection.createStatement();
			statment.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	public static void CustomersVsCouponsTable() throws SQLException {
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = customers_VS_coupons;
			Statement statment = connection.createStatement();
			statment.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}
	public static void addCategories() throws SQLException {
		CategoryDBDAO categoryDBDAO = new CategoryDBDAO();
		categoryDBDAO.AddCategory(Category.FOOD);
		categoryDBDAO.AddCategory(Category.ELECTRICITY);
		categoryDBDAO.AddCategory(Category.RESTAURANT);
		categoryDBDAO.AddCategory(Category.VACATION);
		
	}
}

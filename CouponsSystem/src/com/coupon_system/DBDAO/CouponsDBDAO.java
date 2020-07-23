package com.coupon_system.DBDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.coupon_system.DAO.CouponDAO;
import com.coupon_system.beans.Category;
import com.coupon_system.beans.Coupon;
import com.coupon_system.beans.CustomerVsCoupon;
import com.coupon_system.utils.ConnectionPool;
import com.coupon_system.utils.DateUtils;

public class CouponsDBDAO implements CouponDAO {

	@Override
	public List<Coupon> getAllCoupons() throws SQLException {
		List<Coupon> coupons = new ArrayList<Coupon>();

		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM `coupons_system`.`coupons`";
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(resultset.getInt(1));
				coupon.setCompany_id(resultset.getInt(2));
				coupon.setCategory(Category.values()[resultset.getInt(3) - 1]);
				coupon.setTitle(resultset.getString(4));
				coupon.setDescription(resultset.getString(5));
				coupon.setStart_date(resultset.getDate(6));
				coupon.setEnd_date(resultset.getDate(7));
				coupon.setAmmount(resultset.getInt(8));
				coupon.setPrice(resultset.getDouble(9));
				coupon.setImage(resultset.getString(10));
				coupons.add(coupon);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return coupons;
	}

	@Override
	public void AddCoupon(Coupon coupon) throws SQLException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "INSERT INTO `coupons_system`.`coupons` (company_id, category_id, title, description, start_date, end_date, ammount, price, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, coupon.getCompany_id());
			statement.setInt(2, coupon.getCategory().ordinal() + 1);
			statement.setString(3, coupon.getTitle());
			statement.setString(4, coupon.getDescription());
			statement.setDate(5, DateUtils.convertUtilDateToSQL(coupon.getStart_date()));
			statement.setDate(6, DateUtils.convertUtilDateToSQL(coupon.getEnd_date()));
			statement.setInt(7, coupon.getAmmount());
			statement.setDouble(8, coupon.getPrice());
			statement.setString(9, coupon.getImage());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	@Override
	public void UpdeatCoupon(int couponID, Coupon coupon) throws SQLException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "UPDATE `coupons_system`.`coupons` SET title=?, description=?, start_date=?, end_date=?, ammount=?, price=?, image=? WHERE (id=?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, coupon.getTitle());
			statement.setString(2, coupon.getDescription());
			statement.setDate(3, DateUtils.convertUtilDateToSQL(coupon.getStart_date()));
			statement.setDate(4, DateUtils.convertUtilDateToSQL(coupon.getEnd_date()));
			statement.setInt(5, coupon.getAmmount());
			statement.setDouble(6, coupon.getPrice());
			statement.setString(7, coupon.getImage());
			statement.setInt(8, couponID);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	@Override
	public void DeleteCoupon(int couponID) throws SQLException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "DELETE FROM `coupons_system`.`coupons` WHERE (`id`=?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, couponID);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	@Override
	public Coupon GetOneCoupon(int couponID) throws SQLException {
		Coupon coupon = new Coupon();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM `coupons_system`.`coupons` WHERE `id` = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, couponID);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				coupon.setId(resultset.getInt(1));
				coupon.setCompany_id(resultset.getInt(2));
				coupon.setCategory(Category.values()[resultset.getInt(3) - 1]);
				coupon.setTitle(resultset.getString(4));
				coupon.setDescription(resultset.getString(5));
				coupon.setStart_date(resultset.getDate(6));
				coupon.setEnd_date(resultset.getDate(7));
				coupon.setAmmount(resultset.getInt(8));
				coupon.setPrice(resultset.getDouble(9));
				coupon.setImage(resultset.getString(10));
				

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return coupon;

	}

	@Override
	public void AddCouponPurchase(int customerID, int couponID) throws SQLException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "INSERT INTO `coupons_system`.`customers_vs_coupons` (`customer_id`, `coupon_id`) VALUES (?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customerID);
			statement.setInt(2, couponID);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	@Override
	public void DeleteCouponPurchase(int customerID, int couponID) throws SQLException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "DELETE FROM `coupons_system`.`customers_vs_coupons` WHERE `customer_id`=? AND `coupon_id`=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customerID);
			statement.setInt(2, couponID);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	@Override
	public List<CustomerVsCoupon> getAllCustomerVsCoupons() throws SQLException {
		List<CustomerVsCoupon> cVc = new ArrayList<CustomerVsCoupon>();

		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM coupons_system.customers_vs_coupons";
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				 CustomerVsCoupon customerVsCoupon = new CustomerVsCoupon();
				 customerVsCoupon.setCouponID(resultset.getInt(1));
				 customerVsCoupon.setCustomerID(resultset.getInt(2));
				cVc.add(customerVsCoupon);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return cVc;
	}
}

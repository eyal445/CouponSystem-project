package com.coupon_system.DBDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.coupon_system.beans.Category;
import com.coupon_system.utils.ConnectionPool;

public class CategoryDBDAO {
	public void AddCategory(Category category) throws SQLException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "INSERT INTO `coupons_system`.`categories` (name) VALUES (?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, category.name());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}
}

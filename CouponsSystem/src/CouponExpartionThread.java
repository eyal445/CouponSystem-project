import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.coupon_system.DAO.CouponDAO;
import com.coupon_system.DBDAO.CouponsDBDAO;
import com.coupon_system.beans.Coupon;
import com.coupon_system.beans.CustomerVsCoupon;

public class CouponExpartionThread implements Runnable {
	private boolean quit = false;
	private CouponDAO couponDAO;

	public CouponExpartionThread(){
		this.couponDAO = new CouponsDBDAO();
	}

	@Override
	public void run() {
		while (!quit) {
		List<Coupon> coupons = null;
		try {
			coupons = couponDAO.getAllCoupons();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (Coupon coupon : coupons) {
			if (coupon.getEnd_date().before(new Date())) {
				List<CustomerVsCoupon> customerVsCoupons = null;
				try {
					customerVsCoupons = couponDAO.getAllCustomerVsCoupons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				for (CustomerVsCoupon customerVsCoupon : customerVsCoupons) {
					if (customerVsCoupon.getCouponID()==coupon.getId()) {
						try {
							couponDAO.DeleteCouponPurchase(customerVsCoupon.getCustomerID(), customerVsCoupon.getCouponID());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				try {
					couponDAO.DeleteCoupon(coupon.getId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
	}
	public void stopThread() {
		this.quit = true;
	}

}

import java.sql.SQLException;

import com.coupon_system.utils.ConnectionPool;
import com.coupon_system.utils.General_DataBase;

public class CouponSystem {
	private static CouponSystem instace = null;
	private Thread dailyThread;

	public CouponSystem() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		General_DataBase.CreatAllTabls();
		ConnectionPool.getInstance();
		startDailyThread();
	}

private void startDailyThread() {
	dailyThread = new Thread(CouponExpartionThread);
	dailyThread.
	
}

	public static CouponSystem getInstace() throws ClassNotFoundException, SQLException {
		if (instace == null) {
			synchronized (CouponSystem.class) {
				if (instace == null) {
					instace = new CouponSystem();
				}
			}
		}
		return instace;
	}
}

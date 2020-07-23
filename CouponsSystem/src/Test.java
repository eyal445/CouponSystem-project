import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import com.coupon_system.DAO.CompanyDAO;
import com.coupon_system.DBDAO.CompanyDBDAO;
import com.coupon_system.DBDAO.CouponsDBDAO;
import com.coupon_system.DBDAO.CustumerDBDAO;
import com.coupon_system.beans.Category;
import com.coupon_system.beans.Company;
import com.coupon_system.beans.Coupon;
import com.coupon_system.beans.Customer;
import com.coupon_system.exceptions.AlreadyExsistException;
import com.coupon_system.facade.AdminFacade;
import com.coupon_system.facade.ClientFacade;
import com.coupon_system.facade.CompanyFacade;
import com.coupon_system.facade.CustomerFacade;
import com.coupon_system.security.ClientType;
import com.coupon_system.security.LoginManeger;
import com.coupon_system.utils.ConnectionPool;
import com.coupon_system.utils.General_DataBase;

public class Test {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
		System.out.println("start");
		Class.forName("com.mysql.cj.jdbc.Driver");
		General_DataBase.CreatAllTabls();
		CompanyDBDAO companyDBDAO = new CompanyDBDAO();
		CustumerDBDAO cutomerDBDAO = new CustumerDBDAO();
		CouponsDBDAO couponDBDAO = new CouponsDBDAO();

		Company c1 = new Company();
		Company c2 = new Company();
		Company c3 = new Company();
		Company c4 = new Company();
		Company c5 = new Company();

		c2.setName("pepsi");
		c2.setEmail("pepsi@gmail.com");
		c2.setPassword("12345");

		c1.setName("Coca-Cola");
		c1.setEmail("Coca-Cola@gmail.com");
		c1.setPassword("12345");

		c3.setName("Macdonald's");
		c3.setEmail("Macdonald's@gmail.com");
		c3.setPassword("12345");

		c4.setName("Coca-Cola");
		c4.setEmail("CocaCola@gmail.com");
		c4.setPassword("12345");

		c5.setName("pep");
		c5.setEmail("pepsi@gmail.com");
		c5.setPassword("12345");

		Customer cus1 = new Customer();
		cus1.setFirst_name("Moshe");
		cus1.setLast_name("Levi");
		cus1.setEmail("moshe@customer.com");
		cus1.setPassword("12345");

		Customer cus2 = new Customer();
		cus2.setFirst_name("Roy");
		cus2.setLast_name("Bar");
		cus2.setEmail("roy@customer.com");
		cus2.setPassword("12345");

		Customer cus3 = new Customer();
		cus3.setFirst_name("Ben");
		cus3.setLast_name("Choen");
		cus3.setEmail("ben@customer.com");
		cus3.setPassword("12345");

		Customer cus4 = new Customer();
		cus4.setFirst_name("Dan");
		cus4.setLast_name("Levi");
		cus4.setEmail("roy@customer.com");
		cus4.setPassword("12345");

		Coupon co1 = new Coupon();
		Coupon co2 = new Coupon();
		Coupon co3 = new Coupon();
		Coupon co4 = new Coupon();
		Coupon co5 = new Coupon();
		Coupon co6 = new Coupon();//ammount=0
		Coupon co7 = new Coupon();//expaired date

		co1.setCompany_id(1);
		co1.setCategory(Category.FOOD);
		co1.setTitle("2+1");
		co1.setDescription("buy 2 cans and get 1 free");
		co1.setStart_date(new Date(2020, 7, 1));
		co1.setEnd_date(new Date(2020, 8, 1));
		co1.setAmmount(10);
		co1.setPrice(19.99);
		co1.setImage("https://myfopinion.files.wordpress.com");

		co2.setCompany_id(2);
		co2.setCategory(Category.FOOD);
		co2.setTitle("2+1");
		co2.setDescription("buy 2 cans and get 1 free");
		co2.setStart_date(new Date(2020, 7, 1));
		co2.setEnd_date(new Date(2020, 8, 15));
		co2.setAmmount(10);
		co2.setPrice(17.99);
		co2.setImage("https://images-eu.ssl-images-amazon.com");

		co3.setCompany_id(3);
		co3.setCategory(Category.RESTAURANT);
		co3.setTitle("1+1");
		co3.setDescription("buy 1 meal and get 1 free");
		co3.setStart_date(new Date(2020, 7, 1));
		co3.setEnd_date(new Date(2020, 8, 15));
		co3.setAmmount(10);
		co3.setPrice(34.99);
		co3.setImage("https://static.seekingalpha.com");

		co4.setCompany_id(3);
		co4.setCategory(Category.RESTAURANT);
		co4.setTitle("1+shake");
		co4.setDescription("buy burger and get a free shake");
		co4.setStart_date(new Date(2020, 7, 1));
		co4.setEnd_date(new Date(2020, 8, 1));
		co4.setAmmount(30);
		co4.setPrice(12.99);
		co4.setImage("https://static.seekingalpha.com");

		co5.setCompany_id(3);
		co5.setCategory(Category.RESTAURANT);
		co5.setTitle("1+shake");
		co5.setDescription("buy chizburger and get a free shake");
		co5.setStart_date(new Date(2020, 7, 13));
		co5.setEnd_date(new Date(2020, 8, 1));
		co5.setAmmount(30);
		co5.setPrice(12.99);
		co5.setImage("https://static.seekingalpha.com");
		
		co6.setCompany_id(3);
		co6.setCategory(Category.RESTAURANT);
		co6.setTitle("1+shake");
		co6.setDescription("buy chizburger and get a free shake");
		co6.setStart_date(new Date(2020, 7, 13));
		co6.setEnd_date(new Date(2020, 8, 1));
		co6.setAmmount(0);
		co6.setPrice(12.99);
		co6.setImage("https://static.seekingalpha.com");
		
		co7.setCompany_id(3);
		co7.setCategory(Category.RESTAURANT);
		co7.setTitle("1+shake");
		co7.setDescription("buy chizburger and get a free shake");
		co7.setStart_date(new Date(2020, 7, 13));
		co7.setEnd_date(new Date(2020, 7, 20));
		co7.setAmmount(10);
		co7.setPrice(12.99);
		co7.setImage("https://static.seekingalpha.com");

		/**
		 * company: add- working, update- working, delete- working, getAll- working,
		 * getOne- working customer: add- working, update- working, delete- working,
		 * getAll- working, getOne- working coupon: add- working, update- working,
		 * delete- working, getAll- working, getOne- working customers vs coupons: add
		 * coupon purchase- working, delete coupon purchase- working
		 */
		/*
		 * companyDBDAO testing:
		 */
		companyDBDAO.AddCompany(c1);
		companyDBDAO.AddCompany(c2);
		companyDBDAO.AddCompany(c3);
//		c1.setName("CocaCola");
//		companyDBDAO.UpdeatCompany(1, c1);
//      couponDBDAO.DeleteCoupon(1);
//      companyDBDAO.DeleteCompany(1);
//      System.out.println(companyDBDAO.IsCompanyExist("pepsi@gmail.com", "12345"));
//      System.out.println(companyDBDAO.getAllCompanies());
//		System.out.println(companyDBDAO.GetOneCompany(1));
		/*
		 * customerDBDAO testing
		 */
		cutomerDBDAO.AddCustomer(cus1);
		cutomerDBDAO.AddCustomer(cus2);
//		cutomerDBDAO.AddCustomer(cus3);
//      cus1.setFirst_name("Shalom");
//      cutomerDBDAO.UpdeatCustomer(1, cus1);
//      cutomerDBDAO.DeleteCustomer(1);
//	    System.out.println(cutomerDBDAO.getAllCustomers());
//	    System.out.println(cutomerDBDAO.GetOneCustomer(1));

		/*
		 * couponDBDAO testing
		 */
		couponDBDAO.AddCoupon(co1);
		couponDBDAO.AddCoupon(co2);
		couponDBDAO.AddCoupon(co3);
		couponDBDAO.AddCoupon(co4);
		couponDBDAO.AddCoupon(co6);
//		co1.setAmmount(8);
//		couponDBDAO.UpdeatCoupon(1, co1);
//		couponDBDAO.DeleteCoupon(1);
//		System.out.println(couponDBDAO.getAllCoupons());
//		System.out.println(couponDBDAO.GetOneCoupon(1));
		couponDBDAO.AddCouponPurchase(1, 1);
		couponDBDAO.AddCouponPurchase(2, 1);
//		couponDBDAO.DeleteCouponPurchase(1, 1);

		/*
		 * adminfacade testing
		 */

		AdminFacade adminFacade = new AdminFacade();
		LoginManeger.getInstance().login("eyal_d@gmail.com", "12345", ClientType.ADMINISTRATOR);
		/*
		 * add company- working
		 */
//		try {
//			adminFacade.addCompany(c3);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
//
//		try {
//			adminFacade.addCompany(c4);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
//
//
//		try {
//			adminFacade.addCompany(c5);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
		/*delete company
		 */
//		try {
//			adminFacade.deleteCompany(2);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		/*
		 * delete customer
		 */
//		try {
//			adminFacade.deleteCustomer(2);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		/*
		 * update company- need to check again
		 */
		c1.setId(4);
		try {
			adminFacade.updateCompany(c1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		c1.setName("CocaCola");
		try {
			adminFacade.updateCompany(c1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		c1.setEmail("coca@gmail.com");
		try {
			adminFacade.updateCompany(c1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		/*
		 * get all companies- working
		 */
		System.out.println("*******************************");
		System.out.println("get all companies");
		try {
			System.out.println(adminFacade.getAllCompanies());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * get one company- working
		 */
		System.out.println("***********************************************");
		System.out.println("get one company");
		try {
			System.out.println(adminFacade.getOneCompany(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * add customer- working
		 */
//try {
//	adminFacade.addCustomer(cus3);
//} catch (Exception e) {
//	System.out.println(e.getMessage());
//	e.printStackTrace();
//}
//try {
//	adminFacade.addCustomer(cus4);
//} catch (Exception e) {
//	System.out.println(e.getMessage());
//	e.printStackTrace();
//}
		/*
		 * get all customers
		 */
		System.out.println("*****************************************");
		System.out.println("get all customers");
		try {
			System.out.println(adminFacade.getAllCustomers());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * get one customer
		 */
		System.out.println("**********************************************");
		System.out.println("get one customer");
		try {
			System.out.println(adminFacade.getOneCustomer(2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * update customer+company delete customer+company
		 */
		/*
		 * customer facade testing
		 */
/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
		CustomerFacade customerFacade = new CustomerFacade();
		LoginManeger.getInstance().login("moshe@gmail.com", "12345", ClientType.CUSTOMER);
		customerFacade.setCustomerID(1);
//		try {
//			customerFacade.purchaseCoupon(co2);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//amount 0 exception:
//		try {
//			customerFacade.purchaseCoupon(co6);
//		} catch (Exception e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
		//Expired date
		try {
			customerFacade.purchaseCoupon(co7);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		/*
		 * get customers coupons- working
		 */

		System.out.println("**********************************");
		System.out.println("get customer coupons");
		System.out.println(customerFacade.getCustomerCoupons());

		/*
		 * get customer coupons by category- working
		 */
		System.out.println("******************************************");
		System.out.println("get customer coupons by category");
		System.out.println(customerFacade.getCustomerCoupons(Category.FOOD));

		/*
		 * get customer coupons by max price
		 */
		System.out.println("*****************************************************");
		System.out.println("get customer coupons by max price");
		System.out.println(customerFacade.getCustomerCoupons(30.0));
		/*
		 * get customer details
		 */
		System.out.println("***************************");
		System.out.println("get customer details");
		System.out.println(customerFacade.getCustomerDetails());
		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

		CompanyFacade companyFacade = new CompanyFacade();
		LoginManeger.getInstance().login("coca-cola@gmail.com", "12345", ClientType.COMPANY);
		companyFacade.setCompamyID(1);
		/*
		 * add coupon- working
		 */
		
//		try {
//			companyFacade.addCoupon(co4);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		try {
//			companyFacade.addCoupon(co5);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
		/*
		 * update coupon
		 */
//		co4.setDescription("buy burger and get desert");
//		try {
//			companyFacade.updateCoupon(co4);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		
		/*
		 * delete coupon
		 */
//		try {
//			companyFacade.deleteCoupon(1);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		}
		/*
		 * get company coupons
		 */
		System.out.println("******************************************************************");
		System.out.println("get company coupons ");
		try {
			System.out.println(companyFacade.getCompanyCoupons());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * get company coupons by category
		 */
		System.out.println("**************************************");
		System.out.println("get company coupons by category");
		try {
			System.out.println(companyFacade.getCompanyCoupons(Category.FOOD));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * get company coupons by max price
		 */
		System.out.println("*******************************************");
		System.out.println("get company coupons by max price");
		try {
			System.out.println(companyFacade.getCompanyCoupons(30.0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * get company details
		 */
		System.out.println("*********************************************");
		System.out.println("get company details");
		try {
			System.out.println(companyFacade.getCompanyDetails());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
		
	

		ConnectionPool.getInstance().closeAllConnection();
		System.out.println("end");

	}

}

package com.coupon_system.beans;

import java.util.Date;

public class Coupon {
	private int id;
	private int company_id;
	private Category category;
	private String title;
	private String description;
	private Date start_date;
	private Date end_date;
	private int ammount;
	private double price;
	private String image;

	public Coupon(int id, int company_id, Category category, String title, String description, Date start_date,
			Date end_date, int ammount, double price, String image) {
		this.id = id;
		this.company_id = company_id;
		this.category = category;
		this.title = title;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.ammount = ammount;
		this.price = price;
		this.image = image;
	}

	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getAmmount() {
		return ammount;
	}

	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", company_id=" + company_id + ", category=" + category + ", title=" + title
				+ ", description=" + description + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", ammount=" + ammount + ", price=" + price + ", image=" + image + "]";
	}

}

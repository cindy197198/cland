package vn.edu.vinaenter.models;

import java.sql.Timestamp;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class Land {
	private int lid;
	private int count_views;
	@NotEmpty(message="Tên tin tức không được rỗng")
	private String lname;
	@NotEmpty(message="Mô tả không được rỗng")
	private String description;
	@NotEmpty(message="Địa chỉ không được rỗng")
	private String address;
	private String picture;
	private Timestamp date_create;
	@Valid
	private Category cat;
	@NotNull(message="Vui lòng nhập diện tích")
	@Min(value=0,message="Diện tích phải lớn hơn 0")
	private double area;
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public int getCount_views() {
		return count_views;
	}
	public void setCount_views(int count_views) {
		this.count_views = count_views;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Timestamp getDate_create() {
		return date_create;
	}
	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}
	public Category getCat() {
		return cat;
	}
	public void setCat(Category cat) {
		this.cat = cat;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public Land(int lid, int count_views, String lname, String description, String address, String picture,
			Timestamp date_create, Category cat, double area) {
		super();
		this.lid = lid;
		this.count_views = count_views;
		this.lname = lname;
		this.description = description;
		this.address = address;
		this.picture = picture;
		this.date_create = date_create;
		this.cat = cat;
		this.area = area;
	}
	public Land() {
		super();
	}
	@Override
	public String toString() {
		return "Land [lid=" + lid + ", count_views=" + count_views + ", lname=" + lname + ", description=" + description
				+ ", address=" + address + ", picture=" + picture + ", date_create=" + date_create + ", cat=" + cat
				+ ", area=" + area + "]";
	}
	
	
}

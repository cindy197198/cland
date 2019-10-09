package vn.edu.vinaenter.models;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
public class Category {
	@NotNull(message="Vui lòng chọn danh mục tin")
	private Integer cid;
	//@NotEmpty(message="Tên danh mục không được rỗng")
	private String cname;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Category(Integer cid, String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}
	public Category() {
		super();
	}
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + "]";
	}
	
}

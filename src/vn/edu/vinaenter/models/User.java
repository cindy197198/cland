package vn.edu.vinaenter.models;

import org.hibernate.validator.constraints.NotEmpty;

public class User {
	private int id;
	@NotEmpty(message="Tên đăng nhập không được rỗng")
	private String username;
	@NotEmpty(message="Họ tên không được rỗng")
	private String fullname;
	@NotEmpty(message="Mật khẩu không được rỗng")
	private String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(int id, String username, String fullname, String password) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", fullname=" + fullname + ", password=" + password + "]";
	}
	
}

package vn.edu.vinaenter.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Contact {
	private int cid;
	@NotEmpty(message="Vui lòng nhập họ tên")
	private String fullname;
	@NotEmpty(message="Vui lòng nhập email")
	@Email(message="Email không đúng định dạng")
	private String email;
	@NotEmpty(message="Vui lòng nhập chủ đề")
	private String subject;
	@NotEmpty(message="Vui lòng nhập nội dung")
	private String content;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Contact(int cid, String fullname, String email, String subject, String content) {
		super();
		this.cid = cid;
		this.fullname = fullname;
		this.email = email;
		this.subject = subject;
		this.content = content;
	}
	public Contact() {
		super();
	}
	
}

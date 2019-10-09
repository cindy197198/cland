package vn.edu.vinaenter.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vinaenter.defines.PageDefine;
import vn.edu.vinaenter.models.Category;
import vn.edu.vinaenter.models.Contact;
import vn.edu.vinaenter.models.User;

@Repository
public class UserDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public List<User> getItems() {
		String sql="SELECT id,username,fullname,password FROM users ORDER BY id DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
	}
	public int addItem(User objUser) {
		String sql="INSERT INTO users(username,fullname,password) VALUES(?,?,?)";
		return jdbcTemplate.update(sql, new Object[] {objUser.getUsername(),objUser.getFullname(),objUser.getPassword()});
	}
	public int delItem(int id_user) {
		String sql="DELETE FROM users WHERE id=?";
		return jdbcTemplate.update(sql, new Object[] {id_user});
	}
	public User getItem(int id_user) {
		String sql="SELECT id,username,fullname,password FROM users WHERE id=?";
		List<User> list=jdbcTemplate.query(sql,new Object[] {id_user} , new BeanPropertyRowMapper<User>(User.class));		
		if (list.size()>0 && list!=null) return list.get(0);
		else return null;
	}
	public int editItem(User itemUser) {
		String sql="UPDATE users SET fullname=?,password=? WHERE id=?";
		return jdbcTemplate.update(sql, new Object[] {itemUser.getFullname(),itemUser.getPassword(),itemUser.getId()});
	}
	public int countItems() {
		String sql="SELECT COUNT(*) total "
				+"FROM users ";
		return  jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public List<User> getItemsPagination(int offset) {
		String sql="SELECT id,username,fullname,password FROM users ORDER BY id DESC LIMIT ?,?";
		return jdbcTemplate.query(	sql, 
									new Object[] {offset,PageDefine.ADMIN_ROW_COUNT	}, 
									new BeanPropertyRowMapper<User>(User.class));
	}
	public boolean checkItem(User objUser) {
		String sql="SELECT id,username,fullname,password FROM users WHERE username=?";
		List<User> list=jdbcTemplate.query(sql,new Object[] {objUser.getUsername()} , new BeanPropertyRowMapper<User>(User.class));
		if (list.size()>0) return false;
		return true;
	}
	public User checkLogin(String username, String password) {
		String sql="SELECT id,username,fullname,password FROM users WHERE username=? && password=?";
		List<User> list=jdbcTemplate.query(sql,new Object[] {username,password} , new BeanPropertyRowMapper<User>(User.class));
		if (list.size()>0) return list.get(0);
		return null;
	}

}

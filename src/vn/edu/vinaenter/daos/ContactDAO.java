package vn.edu.vinaenter.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import vn.edu.vinaenter.defines.PageDefine;
import vn.edu.vinaenter.models.Category;
import vn.edu.vinaenter.models.Contact;

@Repository
public class ContactDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public List<Contact> getItems(){
		String sql="SELECT cid,fullname,email,subject,content FROM vnecontact ORDER BY cid DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Contact>(Contact.class));
	}
	public int addItem(Contact objContact) {
		String sql="INSERT INTO vnecontact(fullname,email,subject,content) VALUES(?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[] {	objContact.getFullname(),
														objContact.getEmail(),
														objContact.getSubject(),
														objContact.getContent()});
	}

	public int countItems() {
		String sql="SELECT COUNT(*) total "
				+"FROM vnecontact ";
		return  jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public List<Contact> getItemsPagination(int offset) {
		String sql="SELECT cid,fullname,email,subject,content FROM vnecontact ORDER BY cid DESC LIMIT ?,?";
		return jdbcTemplate.query(	sql, 
									new Object[] {offset,PageDefine.ADMIN_ROW_COUNT	}, 
									new BeanPropertyRowMapper<Contact>(Contact.class));
	}
	public int delItem(int id_contact) {
		String sql="DELETE FROM vnecontact WHERE cid=?";
		return jdbcTemplate.update(sql, new Object[] {id_contact});
	}

}

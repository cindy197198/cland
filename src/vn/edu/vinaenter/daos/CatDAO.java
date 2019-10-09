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
public class CatDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public List<Category> getItems(){
		String sql="SELECT cid,cname FROM categories ORDER BY cid DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
	}
	public int addItem(Category objCat) {
		String sql="INSERT INTO categories(cname) VALUES(?)";
		return jdbcTemplate.update(sql, new Object[] {objCat.getCname()});
	}
	public int delItem(int id_cat) {
		String sql="DELETE FROM categories WHERE cid=?";
		return jdbcTemplate.update(sql, new Object[] {id_cat});
	}
	public Category getItem(int id_cat) {
		String sql="SELECT cid,cname FROM categories WHERE cid=?";
		List<Category> list=jdbcTemplate.query(sql,new Object[] {id_cat} , new BeanPropertyRowMapper<Category>(Category.class));
		//return jdbcTemplate.query(sql,new Object[] {id_cat},new ResultSetExtractor(Category.class) );
		if (list.size()>0 && list!=null) return list.get(0);
		else return null;
	}
	public int editItem(Category objCat) {
		String sql="UPDATE categories SET cname=? WHERE cid=?";
		return jdbcTemplate.update(sql, new Object[] {objCat.getCname(),objCat.getCid()});
	}
	public int countItems() {
		String sql="SELECT COUNT(*) total "
				+"FROM categories ";
		return  jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public List<Category> getItemsPagination(int offset) {
		String sql="SELECT cid,cname FROM categories ORDER BY cid DESC LIMIT ?,?";
		return jdbcTemplate.query(	sql, 
									new Object[] {offset,PageDefine.ADMIN_ROW_COUNT	}, 
									new BeanPropertyRowMapper<Category>(Category.class));
	}
	public boolean checkItem(Category objCat) {
		String sql="SELECT cid,cname FROM categories WHERE cname=?";
		List<Category> list=jdbcTemplate.query(sql,new Object[] {objCat.getCname()} , new BeanPropertyRowMapper<Category>(Category.class));
		if (list.size()>0) return false;
		return true;
	}
	public int countItems(Category cat) {
		String sql=	"SELECT COUNT(*) total "
					+"FROM categories AS c "
					+"INNER JOIN lands AS l "
					+"ON l.cid=c.cid "
					+" WHERE c.cid=?";
		return  jdbcTemplate.queryForObject(sql,new Object[] {cat.getCid()}, Integer.class);
	}
	public List<Category> getItemsHotCat(int catRowCount) {
		String sql=	"SELECT c.cid,cname "
					+"FROM categories AS c " 
					+"INNER JOIN lands AS l "
					+"ON c.cid=l.cid GROUP BY cname " 
					+"ORDER BY SUM(count_views)  DESC LIMIT ?";
		return jdbcTemplate.query(	sql, 
									new Object[] {catRowCount	}, 
									new BeanPropertyRowMapper<Category>(Category.class));
	}

}

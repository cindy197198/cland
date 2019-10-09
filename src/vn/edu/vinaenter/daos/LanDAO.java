package vn.edu.vinaenter.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.PreparedStatement;

import vn.edu.vinaenter.defines.PageDefine;
import vn.edu.vinaenter.models.Category;
import vn.edu.vinaenter.models.Land;

@Repository
public class LanDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public List<Land> getItemsPagination(int offset){
		List<Land> listLand=new ArrayList<Land>();
		String sql="SELECT lid,lname,count_views,description,date_create,address,picture,area,c.cid,cname "
					+"FROM categories AS c "
					+"INNER JOIN lands AS l "
					+"ON c.cid=l.cid "
					+"ORDER BY lid DESC LIMIT ?,?";
		return (List<Land>) jdbcTemplate.query(	sql,
									new Object[] {offset,PageDefine.ADMIN_ROW_COUNT},
									new ResultSetExtractor<List<Land>>() {
										@Override
										public List<Land> extractData(ResultSet rs) throws SQLException, DataAccessException {												
											while (rs.next()) {
												listLand.add(new Land(	rs.getInt("lid"), 
																		rs.getInt("count_views"), 
																		rs.getString("lname"), 
																		rs.getString("description"), 
																		rs.getString("address"), 
																		rs.getString("picture"), 
																		rs.getTimestamp("date_create"), 
																		new Category(rs.getInt("cid"),rs.getString("cname")), 
																		rs.getDouble("area")));
											}
											return listLand;
										}
									});
	}
	
	public int countItems(){
		String sql="SELECT COUNT(*) total "
					+"FROM categories AS c "
					+"INNER JOIN lands AS l "
					+"ON c.cid=l.cid ";
		return  jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public int addItem(Land objLand) {
		String sql="INSERT INTO lands(lname,description,address,picture,area,cid) VALUES(?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[] {	objLand.getLname(),
														objLand.getDescription(),
														objLand.getAddress(),
														objLand.getPicture(),
														objLand.getArea(),
														objLand.getCat().getCid()});
	}

	public int delItem(int id_land) {
		String sql="DELETE FROM lands WHERE lid=?";
		return jdbcTemplate.update(sql, new Object[] {id_land});
	}

	public Land getItem(int id_land) {
		List<Land> listLand=new ArrayList<Land>();
		String sql="SELECT lid,lname,count_views,description,date_create,address,picture,area,c.cid,cname "
				+"FROM categories AS c "
				+"INNER JOIN lands AS l "
				+"ON c.cid=l.cid "
				+ " WHERE lid=?";	
		return  jdbcTemplate.query(	sql,
				new Object[] {id_land},
				new ResultSetExtractor<List<Land>>() {
					@Override
					public List<Land> extractData(ResultSet rs) throws SQLException, DataAccessException {												
						while (rs.next()) {
							listLand.add(new Land(	rs.getInt("lid"), 
													rs.getInt("count_views"), 
													rs.getString("lname"), 
													rs.getString("description"), 
													rs.getString("address"), 
													rs.getString("picture"), 
													rs.getTimestamp("date_create"), 
													new Category(rs.getInt("cid"),rs.getString("cname")), 
													rs.getDouble("area")));
						}
						return listLand;
					}
				}).get(0);
	}

	public int editItem(Land objLand) {
		String sql="UPDATE lands SET lname=?,description=?,address=?,picture=?,area=?,cid=? WHERE lid=?";
		return jdbcTemplate.update(sql, new Object[] {objLand.getLname(),
														objLand.getDescription(),
														objLand.getAddress(),
														objLand.getPicture(),
														objLand.getArea(),
														objLand.getCat().getCid(),
														objLand.getLid()});
	}

	public int countItemsByCat(int cid) {
		String sql="SELECT COUNT(*) total "
				+"FROM categories AS c "
				+"INNER JOIN lands AS l "
				+"ON c.cid=l.cid WHERE l.cid=?";
	return  jdbcTemplate.queryForObject(sql,new Object[] {cid}, Integer.class);
	}

	public List<Land> getItemsPaginationByCat(int cid,int offset) {
		List<Land> listLand=new ArrayList<Land>();
		String sql="SELECT lid,lname,count_views,description,date_create,address,picture,area,c.cid,cname "
					+"FROM categories AS c "
					+"INNER JOIN lands AS l "
					+"ON c.cid=l.cid WHERE l.cid=?  "
					+"ORDER BY lid DESC LIMIT ?,?";
		return (List<Land>) jdbcTemplate.query(	sql,
									new Object[] {cid,offset,PageDefine.CAT_ROW_COUNT},
									new ResultSetExtractor<List<Land>>() {
										@Override
										public List<Land> extractData(ResultSet rs) throws SQLException, DataAccessException {												
											while (rs.next()) {
												listLand.add(new Land(	rs.getInt("lid"), 
																		rs.getInt("count_views"), 
																		rs.getString("lname"), 
																		rs.getString("description"), 
																		rs.getString("address"), 
																		rs.getString("picture"), 
																		rs.getTimestamp("date_create"), 
																		new Category(rs.getInt("cid"),rs.getString("cname")), 
																		rs.getDouble("area")));
											}
											return listLand;
										}
									});
	}

	public List<Land> getRelatedItems(Land objLand, int i) {
		List<Land> listLand=new ArrayList<Land>();
		String sql="SELECT lid,lname,count_views,description,date_create,address,picture,area,c.cid,cname "
					+"FROM categories AS c "
					+"INNER JOIN lands AS l "
					+"ON c.cid=l.cid WHERE l.cid=? && l.lid!=?  "
					+"ORDER BY lid DESC LIMIT ?";
		return (List<Land>) jdbcTemplate.query(	sql,
									new Object[] {objLand.getCat().getCid(),objLand.getLid(),i},
									new ResultSetExtractor<List<Land>>() {
										@Override
										public List<Land> extractData(ResultSet rs) throws SQLException, DataAccessException {												
											while (rs.next()) {
												listLand.add(new Land(	rs.getInt("lid"), 
																		rs.getInt("count_views"), 
																		rs.getString("lname"), 
																		rs.getString("description"), 
																		rs.getString("address"), 
																		rs.getString("picture"), 
																		rs.getTimestamp("date_create"), 
																		new Category(rs.getInt("cid"),rs.getString("cname")), 
																		rs.getDouble("area")));
											}
											return listLand;
										}
									});
	}

	public List<Land> getItemsMostView(int i) {
		List<Land> listLand=new ArrayList<Land>();
		String sql="SELECT lid,lname,count_views,description,date_create,address,picture,area,c.cid,cname "
					+"FROM categories AS c "
					+"INNER JOIN lands AS l "
					+"ON c.cid=l.cid  "
					+"ORDER BY count_views DESC LIMIT ?";
		return (List<Land>) jdbcTemplate.query(	sql,
									new Object[] {i},
									new ResultSetExtractor<List<Land>>() {
										@Override
										public List<Land> extractData(ResultSet rs) throws SQLException, DataAccessException {												
											while (rs.next()) {
												listLand.add(new Land(	rs.getInt("lid"), 
																		rs.getInt("count_views"), 
																		rs.getString("lname"), 
																		rs.getString("description"), 
																		rs.getString("address"), 
																		rs.getString("picture"), 
																		rs.getTimestamp("date_create"), 
																		new Category(rs.getInt("cid"),rs.getString("cname")), 
																		rs.getDouble("area")));
											}
											return listLand;
										}
									});
	}
}

package jsoft.ads.article.category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jsoft.ConnectionPool;
import jsoft.ads.article.section.SectionImpl;
import jsoft.objects.CategoryObject;
import jsoft.*;

public class CategoryImpl extends SectionImpl implements Category {
	
	public CategoryImpl( ConnectionPool cp) {
		super("Category", cp);
	}

	public CategoryImpl(String objectName, ConnectionPool cp) {
		super(objectName,cp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addCategory(CategoryObject item) {
		// TODO Auto-generated method stub

		String sql = "INSERT INTO tblcategory( ";
		sql += "category_name, category_section_id, category_notes, ";
		sql += "category_created_date, category_created_author_id, ";
		sql += "category_last_modified, category_manager_id, category_enable, ";
		sql += "category_delete, category_image, category_name_en, category_language ";
		sql += ") ";
		sql += "VALUES(?,?,?,?,?,?,?,?,?,?,?,?) ";

		// Biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			// Truyền giá trị
			pre.setString(1, item.getCategory_name());
			pre.setInt(2, item.getCategory_section_id());
			pre.setString(3, item.getCategory_notes());
			pre.setString(4, item.getCategory_created_date());
			pre.setInt(5, item.getCategory_created_author_id());
			pre.setString(6, item.getCategory_last_modified());
			pre.setInt(7, item.getCategory_manager_id());
			pre.setBoolean(8, item.isCategory_enable());
			pre.setBoolean(9, item.isCategory_delete());
			pre.setString(10, item.getCategory_image());
			pre.setString(11, item.getCategory_name_en());
			pre.setInt(12, item.getCategory_language());

			return this.add(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean editCategory(CategoryObject item) {
		// TODO Auto-generated method stub
		String sql = "UPDATE tblcategory SET ";
		sql += "category_name=?,category_section_id=?, category_notes=?, ";
		sql += "category_last_modified=?,";
		sql += "category_manager_id =?, category_enable=?, ";
		sql += "category_delete=?, category_image=?, category_name_en=?, category_language =? ";
		sql += "WHERE category_id =? ";

		// Biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			// Truyền giá trị
			pre.setString(1, item.getCategory_name());
			pre.setInt(2, item.getCategory_section_id());

			pre.setString(3, item.getCategory_notes());

			pre.setString(4, item.getCategory_last_modified());
			pre.setInt(5, item.getCategory_manager_id());
			pre.setBoolean(6, item.isCategory_enable());
			pre.setBoolean(7, item.isCategory_delete());
			pre.setString(8, item.getCategory_image());
			pre.setString(9, item.getCategory_name_en());
			pre.setInt(10, item.getCategory_language());

			pre.setInt(11, item.getCategory_id());

			return this.edit(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean delCategory(CategoryObject item) {
		// TODO Auto-generated method stub
		

		if (!IsEmpty(item)) {
			return false;
		}
		String sql = "DELETE FROM tblcategory WHERE category_id =? ";

		// Biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setInt(1, item.getCategory_id());

			return this.del(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Phương thức kiểm tra User xoá có liên kết với bảng khác không <br>
	 * <u><i>Update: 10/10/2022</i></u></br>
	 * 
	 * @param item
	 * @return
	 */
	private boolean IsEmpty(CategoryObject item) {
		boolean flag = true;

		String sql1 = "SELECT article_id FROM tblarticle WHERE article_category_id =? ";

		ResultSet rs1 = this.gets(sql1);


		if (rs1 != null) {
			try {
				if (rs1.next()) {
					flag = false;
				}

				rs1.close();
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public ResultSet getCategory(short id) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM tblcategory ";
		sql +="LEFT JOIN tblsection ON category_section_id = section_id ";
		sql+="WHERE category_id=? ";
		return this.get(sql, id);
	}

	@Override
	public ResultSet getCategories(CategoryObject similar, int at, byte total) {
		// TODO Auto-generated method stub
		
		return this.getCategories(similar, CategoryShort.NAME, at, total);
	}

	@Override
	public ResultSet getCategories(CategoryObject similar, CategoryShort cs, int at, byte total) {
		// TODO Auto-generated method stub
		String sql="SELECT*FROM tblcategory c ";
		sql +="LEFT JOIN tblsection s ON c.category_section_id = s.section_id ";
		sql += "LEFT JOIN tbluser u ON c.category_created_author_id = u.user_id ";
		sql += "";
		switch(cs) {
		case ID:
			sql+="ORDER BY category_id DESC "; break;
		case NAME:
			sql+="ORDER BY category_name ASC "; break;
		case SECTION_NAME:
			sql+="ORDER BY category_section_name ASC "; break;
		case MANAGER:
			sql+="ORDER BY category_manager ASC "; break;
		case AUTHOR:			
			sql+="ORDER BY category_created_author_id ASC "; break;

		}
		sql +="LIMIT "+at+", "+ total;
		return this.gets(sql);
	}
	
	public static void main(String[] args) {
		// Tạo bộ quản lý kết nối
				ConnectionPool cp = new ConnectionPoolImpl();

				// Tạo đối tượng thực thi chức năng Interface
				Category c = new CategoryImpl(cp);

				// Tạo đối tượng lưu trữ thông tin
				CategoryObject nCate = new CategoryObject();
				nCate.setCategory_name("duongdiep");
				nCate.setCategory_notes("Dương Thị Diệp");
				nCate.setCategory_manager_id(15);
				nCate.setCategory_created_date("16/10/2022");
				nCate.setCategory_created_author_id((short)10);

				// Thực hiện thêm mới
				boolean result = c.addCategory(nCate);

				// Thông báo
				if (!result) {
					System.out.println("\n---------------KHÔNG THÀNH CÔNG----------------\n");
				}
				// Lấy tập kết quả
				ResultSet rs = c.getCategories(null, 0, (byte) 30);

				// Trả về kết nối
				c.releaseConnection();

				// Duyệt và in ra màn hình
				String row;

				if (rs != null) {
					try {
						while (rs.next()) {
							row = "ID: " + rs.getInt("category_id");
							row += "\tNAME: " + rs.getString("category_name");
							row += "\tNOTES: " + rs.getString("category_notes");
							row += "\tMANAGER: " + rs.getInt("category_manager_id");
							System.out.println(row);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	}
}

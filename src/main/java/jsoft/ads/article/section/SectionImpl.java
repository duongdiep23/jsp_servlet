package jsoft.ads.article.section;

import java.sql.*;
import jsoft.*;
import jsoft.ads.basic.*;
import jsoft.objects.SectionObject;

public class SectionImpl extends BasicImpl implements Section {
	
	public SectionImpl(ConnectionPool cp) {
		super("Section", cp);
	}
	
	public SectionImpl(String objectName, ConnectionPool cp) {
		super(objectName, cp);
	}

	@Override
	public boolean addSection(SectionObject item) {
		// TODO Auto-generated method stub
		
		if(this.isExisting(item)) {
			return false;
		}
		
		String sql = "INSERT INTO tblsection(";
		sql += "section_name, section_notes, section_created_date,";
		sql += "section_manager_id, section_enable, section_delete, ";
		sql += "section_last_modified, section_created_author_id, section_name_en, section_language)   ";
		sql += "VALUES(?,?,?,?,?,?,?,?,?,?) ";
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			
			pre.setString(1, item.getSection_name());
			pre.setString(2, item.getSection_notes());
			pre.setString(3, item.getSection_created_date());
			pre.setInt(4, item.getSection_manager_id());
			pre.setBoolean(5, item.isSection_enable());
			pre.setBoolean(6, item.isSection_delete());
			pre.setString(7, item.getSection_last_modified());
			pre.setInt(8, item.getSection_created_author_id());
			pre.setString(9, item.getSection_name_en());
			pre.setByte(10, item.getSection_language());
			
			return this.add(pre);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean isExisting(SectionObject item) {
		boolean flag = false;
		
	
		String sql = "SELECT section_id FROM tblsection WHERE section_name='"+item.getSection_name()+"' ";
		
		ResultSet rs = this.gets(sql);
		
		if(rs != null) {
			try {
				if(rs.next()) {
					flag = true;
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return flag;
	}
	

	@Override
	public boolean editSection(SectionObject item) {
		// TODO Auto-generated method stub
		

		String sql = "UPDATE tblsection SET ";
		sql += "section_name=?, section_notes=?,";
		sql += "section_manager_id=?, section_enable=?, section_delete=?, ";
		sql += "section_last_modified=?, section_name_en=?, section_language=?  ";
		sql += "WHERE section_id=? ";
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			
			pre.setString (1, item.getSection_name());
			pre.setString (2, item.getSection_notes());
			
			pre.setInt (3, item.getSection_manager_id());
			pre.setBoolean (4, item.isSection_enable());
			pre.setBoolean (5, item.isSection_delete());
			pre.setString (6, item.getSection_last_modified());
			
			pre.setString (7, item.getSection_name_en());
			pre.setByte (8, item.getSection_language());
			
			pre.setShort(9, item.getSection_id());
			
			return this.edit(pre);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delSection(SectionObject item) {
		// TODO Auto-generated method stub
		
		if(!this.isEmpty(item)) {
			return false;
		}
		
		String sql = "DELETE FROM tblsection WHERE section_id=? ";
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setShort(1, item.getSection_id());
			return this.del(pre);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean isEmpty(SectionObject item) {
		boolean flag = true;
		
		String sql1 = "SELECT article_id FROM tblarticle WHERE article_section_id=?";
		String sql2 = "SELECT category_id FROM tblcategory WHERE category_section_id=?";
		
		ResultSet rs1 = this.gets(sql1);
		ResultSet rs2 = this.gets(sql2);
		
		if(rs1 != null || rs2 != null) {
			try {
				if(rs1.next() || rs2.next()) {
					flag = false;
				}
				
				rs1.close();
				rs2.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return flag;
	}

	@Override
	public ResultSet getSection(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblsection WHERE section_id=? ";
		
		return this.get(sql, id);
		
	}

	@Override
	public ResultSet getSections(SectionObject similar, int at, byte total) {
		// TODO Auto-generated method stub;
		
		return this.getSections(similar, SectionSort.NAME, at, total);
	}

	@Override
	public ResultSet getSections(SectionObject similar, SectionSort ss, int at, byte total) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblsection s ";
		sql += "LEFT JOIN tbluser u ON s.section_created_author_id = u.user_id ";
		sql += "";
		switch(ss) {
		case ID:
			sql += "ORDER BY section_id ASC "; break;
		case NAME:
			sql += "ORDER BY section_name ASC "; break;
		case CREATED_DATE:
			sql += "ORDER BY section_created_date ASC "; break;
		}
		if(at == 0 && total == 0) {
			sql += " ";
		}else {
			sql += "LIMIT "+at+", "+ total;
		}
		
		return this.gets(sql);
	}
	
	public static void main(String[] args) {
		//tạo bộ quản lý kết nối
		ConnectionPool cp = new ConnectionPoolImpl();
		
		//tạo đối tg thực thi chức năng của interface
		Section s = new SectionImpl(cp);
		
		//tạo đối tg lưu trữ thông tin
		SectionObject nSection = new SectionObject();
		nSection.setSection_id((short)12);
		nSection.setSection_name("Dương Thị Diệp");
		nSection.setSection_notes("Đã sửa");
		nSection.setSection_manager_id(15);
				
		//Thực hiện thêm mới
		boolean result = s.editSection(nSection);
				
		//thông báo 
		if(!result) {
			System.out.println("\n------------KHÔNG THÀNH CÔNG-----------\n");
		}
		
		//lấy tập kết quả
		ResultSet rs = s.getSections(null, 0, (byte)20);
		
		//trả về kết nối
		s.releaseConnection();
		
		//in kết quả
		String row;
		
		if(rs != null) {
			try {
				while (rs.next()) {
					row = "ID: " + rs.getShort("section_id");
					row += "\tNAME: " + rs.getString("section_name");
					row += "\tCREATED DATE: " + rs.getString("section_created_date");
					row += "\tNOTES: " + rs.getString("section_notes");
					
					System.out.println(row);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}

package jsoft.ads.article;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.ads.article.category.CategoryImpl;
import jsoft.objects.ArticleObject;

public class ArticleImpl extends CategoryImpl implements Article {

	public ArticleImpl(ConnectionPool cp) {
		super("Article", cp);
	}

	@Override
	public boolean addArticle(ArticleObject item) {
		// TODO Auto-generated method stub
		
		if (this.IsExisting(item)) {
			return false;
		}

		String sql = "INSERT INTO tblarticle ( ";
		sql += "article_title, article_summary, article_content, ";
		sql += "article_created_date, article_last_modified, article_image, ";
		sql += "article_category_id, article_section_id, article_visited, article_author_name, ";
		sql += "article_enable, article_url_link, article_tag, article_title_en, article_summary_en, ";
		sql += "article_content_en, article_tag_en, article_fee, article_isfee, ";
		sql += "article_delete, article_deleted_date, article_restored_date, ";
		sql += "article_modified_author_name, article_author_permission, article_source, ";
		sql += "article_language, article_focus, article_type, article_forhome) ";
		sql += "VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

		// biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			// truyền giá trị
			pre.setString(1, item.getArticle_title());
			pre.setString(2, item.getArticle_summary());
			pre.setString(3, item.getArticle_content());
			pre.setString(4, item.getArticle_created_date());
			pre.setString(5, item.getArticle_last_modified());
			pre.setString(6, item.getArticle_image());
			pre.setShort(7, item.getArticle_category_id());
			pre.setShort(8, item.getArticle_section_id());
			pre.setInt(9, item.getArticle_visited());
			pre.setString(10, item.getArticle_author_name());
			pre.setBoolean(11, item.isArticle_enable());
			pre.setString(12, item.getArticle_url_link());
			pre.setString(13, item.getArticle_tag());
			pre.setString(14, item.getArticle_title_en());
			pre.setString(15, item.getArticle_summary_en());
			pre.setString(16, item.getArticle_content_en());
			pre.setString(17, item.getArticle_tag_en());
			pre.setInt(18, item.getArticle_fee());
			pre.setBoolean(19, item.isArticle_isfee());
			pre.setBoolean(20, item.isArticle_delete());
			pre.setString(21, item.getArticle_deleted_date());
			pre.setString(22, item.getArticle_restored_date());
			pre.setString(23, item.getArticle_modified_author_name());
			pre.setByte(24, item.getArticle_author_permission());
			pre.setString(25, item.getArticle_source());
			pre.setByte(26, item.getArticle_language());
			pre.setBoolean(27, item.isArticle_focus());
			pre.setByte(28, item.getArticle_type());
			pre.setBoolean(29, item.isArticle_forhome());

			return this.add(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean IsExisting(ArticleObject item) {
		boolean flag = false;

		String sql = "SELECT article_id FROM tblarticle WHERE article_title ='" + item.getArticle_title() + "'";

		ResultSet rs = this.gets(sql);

		if (rs != null) {
			try {
				if (rs.next()) {
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
	public boolean editArticle(ArticleObject item) {
		// TODO Auto-generated method stub

		String sql = "UPDATE tblarticle SET ";
		sql += "article_title = ?, article_summary = ?, article_content = ?, ";
		sql += "              article_last_modified = ?, article_image = ?, ";
		sql += " ";
		sql += "article_enable = ?, article_url_link = ?, article_tag = ?, ";
		sql += "article_title_en = ?, article_summary_en = ?, article_content_en = ?, article_tag_en = ?, ";
		sql += "article_fee = ?, article_isfee = ?, article_delete = ?, ";
		sql += "article_modified_author_name = ?, article_author_permission = ?, ";
		sql += "article_source = ?, article_language = ?, article_focus = ?, article_type = ?, article_forhome = ? ";
		sql += "WHERE article_id = ? ";

		// Biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			// Truyền giá trị
			pre.setString(1, item.getArticle_title());
			pre.setString(2, item.getArticle_summary());
			pre.setString(3, item.getArticle_content());

			pre.setString(4, item.getArticle_last_modified());
			pre.setString(5, item.getArticle_image());

			pre.setBoolean(6, item.isArticle_enable());
			pre.setString(7, item.getArticle_url_link());
			pre.setString(8, item.getArticle_tag());
			pre.setString(9, item.getArticle_title_en());
			pre.setString(10, item.getArticle_summary_en());
			pre.setString(11, item.getArticle_content_en());
			pre.setString(12, item.getArticle_tag_en());
			pre.setInt(13, item.getArticle_fee());
			pre.setBoolean(14, item.isArticle_isfee());
			pre.setBoolean(15, item.isArticle_delete());

			pre.setString(16, item.getArticle_modified_author_name());
			pre.setByte(17, item.getArticle_author_permission());
			pre.setString(18, item.getArticle_source());
			pre.setByte(19, item.getArticle_language());
			pre.setBoolean(20, item.isArticle_focus());
			pre.setByte(21, item.getArticle_type());
			pre.setBoolean(22, item.isArticle_forhome());

			pre.setInt(23, item.getArticle_id());
			// Sửa
			return this.edit(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delArticle(ArticleObject item) {
		// TODO Auto-generated method stub

		String sql = "DELETE FROM tblarticle WHERE article_id = ? ";

		// Biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setInt(1, item.getArticle_id());

			return this.del(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ResultSet getArticle(int id) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM tblarticle ";
		sql += "LEFT JOIN tblcategory ON article_category_id = category_id ";
		sql += "LEFT JOIN tblsection ON category_section_id = section_id ";
		sql += "WHERE article_id = ? ";

		return this.get(sql, id);

	}

	@Override
	public ResultSet getArticles(ArticleObject similar, int at, byte total) {
		// TODO Auto-generated method stub

		return this.getArticles(similar, ArticleShort.TITLE, at, total);
	}

	@Override
	public ResultSet getArticles(ArticleObject similar, ArticleShort as, int at, byte total) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM tblarticle ";
		sql += "LEFT JOIN tblcategory ON article_category_id = category_id ";
		sql += "LEFT JOIN tblsection ON category_section_id = section_id ";
		sql += "";
		switch (as) {
		case ID:
			sql += "ORDER BY article_id ASC ";
			break;
		case TITLE:
			sql += "ORDER BY article_title ASC ";
			break;
		case CREATED_DATE:
			sql += "ORDER BY article_created_date ASC ";
			break;
		}
		sql += "LIMIT " + at + ", " + total;

		return this.gets(sql);

	}

	public static void main(String[] args) {
		// tạo bộ quản lý kết nối
		ConnectionPool cp = new ConnectionPoolImpl();

		// tạo đối tg thực thi chức năng mức Interface
		Article a = new ArticleImpl(cp);

		// tạo đối tg lưu trữ thông tin
		ArticleObject nArtic = new ArticleObject();
		nArtic.setArticle_title("Dương Thị Diệp Lục");
		nArtic.setArticle_content("Đây là bài viết của Diệp");
		nArtic.setArticle_author_name("Lee Min Qanh");
		nArtic.setArticle_created_date("15-10-2022");
		nArtic.setArticle_visited(10);

		// Thực hiện thêm mới
		 boolean result = a.addArticle(nArtic);

		//Thông báo kết quả
		 if(!result) {
			 System.out.println("\n-----KHÔNG THÀNH CÔNG-------\n");
		 }
		// Lấy tập kết quả
		ResultSet rs = a.getArticles(null, 0, (byte) 30);

		// trả về kết nối
		a.releaseConnection();

		// Duyệt và in ra màn hình
		String row;
		if (rs != null) {
			try {
				while (rs.next()) {
					row = "ID: " + rs.getInt("article_id");
					//row += "\tTITLE: " + rs.getString("article_title");
					//row += "\tCONTENT" + rs.getString("article_content");
					row += "\tCREATED_DATE: " + rs.getString("article_created_date");

					System.out.println(row);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

package jsoft.ads.article;

import jsoft.*;
import jsoft.objects.*;

import java.sql.*;
import java.util.*;

public class ArticleModel {
	private Article a;

	public ArticleModel(ConnectionPool cp) {
		this.a = new ArticleImpl(cp);
	}

	protected void finalize() throws Throwable {
		this.a = null;
	}
	
	public ConnectionPool getCP(){
		return this.a.getCP();
	}
	
	public void releaseConnection() {
		this.a.releaseConnection();
	}
	

	// ------------------------------

	public boolean addArticle(ArticleObject item) {
		return this.a.addArticle(item);

	}

	public boolean editArticle(ArticleObject item) {
		return this.a.editArticle(item);
	}

	public boolean delArticle(ArticleObject item) {
		return this.a.delArticle(item);
	}
	// ------------------------------

	public ArticleObject getArticleObject(int id) {
		ArticleObject item = null;

		ResultSet rs = this.a.getArticle(id);

		if (rs != null) {
			try {
				if (rs.next()) {
					item = new ArticleObject();
					item.setArticle_id(rs.getInt("article_id"));
					item.setArticle_title(rs.getString("article_title"));
					item.setArticle_summary(rs.getString("article_summary"));
					item.setArticle_content(rs.getString("article_content"));
					item.setArticle_created_date(rs.getString("article_created_date"));
					item.setArticle_last_modified(rs.getString("article_last_modified"));
					item.setArticle_image(rs.getString("article_image"));
					item.setArticle_category_id(rs.getShort("article_category_id"));
					item.setArticle_section_id(rs.getShort("article_section_id"));
					item.setArticle_visited(rs.getInt("article_visited"));
					item.setArticle_author_name(rs.getString("article_author_name"));
					
					item.setArticle_category_id(rs.getShort("category_id"));
					item.setCategory_name(rs.getString("category_name"));
					
					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));

				}

				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return item;
	}

	public ArrayList<ArticleObject> getArticleObjects(ArticleObject similer, ArticleShort as, short page, byte total) {
		ArrayList<ArticleObject> items = new ArrayList<>();

		ArticleObject item = null;

		int at = (page - 1) * total;
		
		ResultSet rs = this.a.getArticles(similer, as, at, total);

		if (rs != null) {
			try {
				while (rs.next()) {
					item = new ArticleObject();
					item.setArticle_id(rs.getInt("article_id"));
					item.setArticle_title(rs.getString("article_title")+"@@@"+ rs.getString("section_name"));
					item.setArticle_summary(rs.getString("article_summary"));
					item.setArticle_content(rs.getString("article_content"));
					item.setArticle_created_date(rs.getString("article_created_date"));
					item.setArticle_last_modified(rs.getString("article_last_modified"));
					item.setArticle_image(rs.getString("article_image"));
					item.setArticle_category_id(rs.getShort("article_category_id"));
					item.setArticle_section_id(rs.getShort("article_section_id"));
					item.setArticle_visited(rs.getInt("article_visited"));
					item.setArticle_author_name(rs.getString("article_author_name")+"@@@"+ rs.getString("category_name"));
					
					item.setArticle_category_id(rs.getShort("category_id"));
					item.setCategory_name(rs.getString("category_name"));
					
					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));
					// .......
					
					items.add(item);
				}

				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return items;
	}

	// ------------------------------

}

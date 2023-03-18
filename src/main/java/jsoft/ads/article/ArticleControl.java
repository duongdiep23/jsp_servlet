package jsoft.ads.article;

import jsoft.*;
import jsoft.objects.*;

import java.util.*;

public class ArticleControl {
	private ArticleModel am;
	
	public ArticleControl(ConnectionPool cp) {
		this.am = new ArticleModel(cp);
	}
	
	protected void finalize() throws Throwable{
		this.am = null;
	}
	
	public ConnectionPool getCP(){
		return this.am.getCP();
	}
	
	public void releaseConnection() {
		this.am.releaseConnection();
	}
	
	
	//----------------------------
	

	public boolean addArticle(ArticleObject item) {
		return this.am.addArticle(item);

	}

	public boolean editArticle(ArticleObject item) {
		return this.am.editArticle(item);
	}

	public boolean delArticle(ArticleObject item) {
		return this.am.delArticle(item);
	}
	
	//----------------------------------
	
	public ArticleObject getArticleObject(int id) {
		return this.am.getArticleObject(id);
	}
	
	//-----------------------------------
	
	public String viewArticles(ArticleObject similar, ArticleShort as, short page, byte total) {
		//Lấy dữ liệu
		ArrayList<ArticleObject> items = this.am.getArticleObjects(similar, as, page, total);
		
		return ArticleLibrary.viewArticles(items);
	}

	public static void main(String[] args) {
		//tạo đối tượng thực thi CN mức control
		ArticleControl ac = new ArticleControl(null);
		
		//Lấy cấu trúc trình bày
		String view = ac.viewArticles(null, ArticleShort.ID, (short)1, (byte)20);
		
		//trả về kêts nối
		ac.releaseConnection();
		
		//Hiển thị
		System.out.println(view);
		
	}
}

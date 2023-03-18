package jsoft.ads.article;

import java.sql.*;

import jsoft.ShareControl;
import jsoft.objects.*;

public interface Article extends ShareControl {
	public boolean addArticle(ArticleObject item);

	public boolean editArticle(ArticleObject item);

	public boolean delArticle(ArticleObject item);

	public ResultSet getArticle(int id);

	public ResultSet getArticles(ArticleObject similar, int at, byte total);
	
	public ResultSet getArticles(ArticleObject similar, ArticleShort as, int at, byte total);
}

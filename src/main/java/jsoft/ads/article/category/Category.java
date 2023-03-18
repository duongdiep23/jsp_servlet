package jsoft.ads.article.category;

import java.sql.ResultSet;

import jsoft.ShareControl;
import jsoft.objects.*;

public interface Category extends ShareControl {
	public boolean addCategory(CategoryObject item);

	public boolean editCategory(CategoryObject item);

	public boolean delCategory(CategoryObject item);

	public ResultSet getCategory(short id);

	public ResultSet getCategories(CategoryObject similar, int at, byte total);
	
	public ResultSet getCategories(CategoryObject similar,CategoryShort cs, int at, byte total);

}

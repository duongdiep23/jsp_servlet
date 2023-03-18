package jsoft.ads.article;

import jsoft.objects.*;
import java.util.*;

import jsoft.library.*;

public class ArticleLibrary {
	public static String viewArticles(ArrayList<ArticleObject> items) {
		String tmp = "";
		
		tmp += "<div class=\"card\">";
		tmp += "<div class=\"card-body\">";
		tmp += "<h5 class=\"card-title\">Danh sách bài viết</h5>";
	
		tmp += "<!-- Table with stripped rows -->";
		tmp += "<table class=\"table table-striped\">";
		tmp += "<thead>";
		tmp += "<tr>";
		tmp += "<th scope=\"col\">#</th>";
		tmp += "<th scope=\"col\">Ngày tạo</th>";
		tmp += "<th scope=\"col\">Tiêu đề bài viết</th>";
		tmp += "<th scope=\"col\">Người tạo bài viết</th>";
		tmp += "<th scope=\"col\">Tên danh mục</th>";
		tmp += "<th scope=\"col\">Tên chuyên mục</th>";
		tmp += "<th scope=\"col\">Số lượng truy cập</th>";
		tmp += "<th scope=\"col\" colspan=\"2\">Thực hiện</th>";
		tmp += "</tr>";
		tmp += "</thead>";
	
		tmp += "<tbody>";
		
		for(ArticleObject item: items) {
			tmp += "<tr>\n";
			tmp += "<th scope=\"row\">"+item.getArticle_id()+"</th>";
			tmp += "<td>"+item.getArticle_created_date()+"</td>";
			tmp += "<td>"+Utilities.getInfo(item.getArticle_title(), (byte) 0)+"</td>";
			tmp += "<td>"+Utilities.getInfo(item.getArticle_author_name(), (byte) 0)+"</td>";
			tmp += "<td>"+Utilities.getInfo(item.getArticle_title(), (byte) 1)+"</td>";
			tmp += "<td>"+Utilities.getInfo(item.getArticle_author_name(), (byte) 1)+"</td>";
			tmp += "<td>"+item.getArticle_visited()+"</td>";
			tmp += "<td><a href=\"#\" class=\"btn btn-primary btn-sm\"><i class=\"fa-solid fa-user-pen\"></i></a></td>";
			tmp += "<td><a href=\"#\" class=\"btn btn-danger btn-sm\"><i class=\"fa-solid fa-trash-can\"></i></a></td>";
			tmp += "</tr>";
		}
		
		tmp += "</tbody>\n";
		tmp += "</table>";
		tmp += "</div>";
		tmp += "</div>";
		
		return tmp;
	}
}

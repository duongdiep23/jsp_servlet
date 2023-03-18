package jsoft.ads.article.category;

import jsoft.objects.*;
import java.util.*;

import jsoft.library.*;

public class CategoryLibrary {
	public static String viewCategories(ArrayList<CategoryObject> items) {
		String tmp = "";
		
		tmp += "<div class=\"card\">";
		tmp += "<div class=\"card-body\">";
		tmp += "<h5 class=\"card-title\">Danh sách danh mục</h5>";
		tmp += "";
		tmp += "<!-- Table with stripped rows -->";
		tmp += "<table class=\"table table-striped\">";
		tmp += "<thead>";
		tmp += "<tr>";
		tmp += "<th scope=\"col\">#</th>";
		tmp += "<th scope=\"col\">Ngày tạo</th>";
		tmp += "<th scope=\"col\">Tên danh mục</th>";
		tmp += "<th scope=\"col\">Tên thể loại</th>";
		tmp += "<th scope=\"col\">Nhóm danh mục</th>";
		tmp += "<th scope=\"col\">Người tạo danh mục</th>";
		tmp += "<th scope=\"col\">Ghi chú</th>";
		tmp += "<th scope=\"col\">Người quản lý</th>";
		tmp += "<th scope=\"col\" colspan=\"2\">Thực hiện</th>";
		tmp += "</tr>";
		tmp += "</thead>";
	
		tmp += "<tbody>";
		
		for(CategoryObject item: items) {
			tmp += "<tr>";
			tmp += "<th scope=\"row\">"+item.getCategory_id()+"</th>";
			tmp += "<td>"+item.getCategory_created_date()+"</td>";
			tmp += "<td>"+item.getCategory_name()+"</td>";
			tmp += "<td>"+item.getSection_name()+"</td>";
			tmp += "<td>"+item.getCategory_section_id()+"</td>";
			tmp += "<td>"+Utilities.getInfo(item.getCategory_notes(), (byte)1)+"</td>";
			tmp += "<td>"+Utilities.getInfo(item.getCategory_notes(), (byte)0)+"</td>";
			tmp += "<td>"+item.getCategory_manager_id()+"</td>";
			tmp += "<td><a href=\"#\" class=\"btn btn-primary btn-sm\"><i class=\"fa-solid fa-pen-to-square\"></i></a></td>";
			tmp += "<td><a href=\"#\" class=\"btn btn-danger btn-sm\"><i class=\"fa-solid fa-trash-can\"></i></a></td>";
			tmp += "</tr>";
		}
		
		tmp += "</tbody>";
		tmp += "</table>";
		tmp += "</div>";
		tmp += "</div>";
		
		return tmp;
	}
	
}

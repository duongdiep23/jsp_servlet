package jsoft.ads.article.section;

import jsoft.objects.*;
import java.util.*;

import jsoft.library.*;

public class SectionLibrary {
	public static String viewSections(ArrayList<SectionObject> items) {
		String tmp = "";

		tmp += "<div class=\"card\">";
		tmp += "<div class=\"card-body\">";
		tmp += "<h5 class=\"card-title\">Danh sách chuyên mục</h5>";
		tmp += "";
		tmp += "<!-- Table with stripped rows -->";
		tmp += "<table class=\"table table-striped\">";
		tmp += "<thead>";
		tmp += "<tr>";
		tmp += "<th scope=\"col\">ID</th>";
		tmp += "<th scope=\"col\">Ngày tạo</th>";
		tmp += "<th scope=\"col\">Tên chuyên mục</th>";
		tmp += "<th scope=\"col\">Ghi chú tổng hợp</th>";
		tmp += "<th scope=\"col\">Người quản lý</th>";
		tmp += "<th scope=\"col\">Người tạo chuyên mục</th>";
		tmp += "<th scope=\"col\" colspan=\"2\">Thực hiện</th>";
		tmp += "</tr>";
		tmp += "</thead>";

		tmp += "<tbody>";

		for (SectionObject item : items) {
			tmp += "<tr>";
			tmp += "<th scope=\"row\">" + item.getSection_id() + "</th>";
			tmp += "<td>" + item.getSection_created_date() + "</td>";
			tmp += "<td>" + item.getSection_name() + "</td>";
			tmp += "<td>" + Utilities.getInfo(item.getSection_notes(), (byte) 0) + "</td>";
			tmp += "<td>" + item.getSection_manager_id() + "</td>";
			tmp += "<td>" + Utilities.getInfo(item.getSection_notes(), (byte) 1) + "</td>";
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
	
	public static String getSectionNames(ArrayList<SectionObject> items) {
		
		String tmp ="";
		tmp += "<select class=\"form-control\" id=\"slcsection\" name=\"slcSection \" >\n";
		for(SectionObject item : items) {
			tmp += "<option value=\""+ item.getSection_id()+"\">"+ item.getSection_name()+"</option>\n";
		}
		tmp += "</select>";
		
		return tmp;
	}

}

package jsoft.ads.user;

import jsoft.objects.*;
import java.util.*;
import jsoft.library.*;

public class UserLibrary {
	public static String viewUsers(ArrayList<UserObject> items, UserObject user) {
		String tmp = "";
		tmp += "<div class=\"card\">";
		tmp += "<div class=\"card-body\">";
		tmp += "<h5 class=\"card-title\">Danh sách người sử dụng</h5>";
		tmp += "";
		tmp += "<!-- Table with stripped rows -->";
		tmp += "<table class=\"table table-striped table-sm\">";
		tmp += "<thead>";
		tmp += "<tr>";
		tmp += "<th scope=\"col\">#</th>";
		tmp += "<th scope=\"col\">Ngày tạo</th>";
		tmp += "<th scope=\"col\">Tên đăng nhập</th>";
		tmp += "<th scope=\"col\">Tên đầy đủ</th>";
		tmp += "<th scope=\"col\">Hộp thư</th>";
		tmp += "<th scope=\"col\">Lần đăng nhập</th>";
		tmp += "<th scope=\"col\" colspan=\"2\">Thực hiện</th>";
		tmp += "</tr>";
		tmp += "</thead>";

		tmp += "<tbody>";
		if(items.size() >0) {

		for (UserObject item : items) {
			tmp += "<tr>\n";
			tmp += "<th scope=\"row\">" + item.getUser_id() + "</th>";
			tmp += "<td>" + item.getUser_created_date() + "</td>";
			tmp += "<td>" + item.getUser_name() + "</td>";
			tmp += "<td>" + item.getUser_fullname() + "</td>";
			tmp += "<td>" + item.getUser_email() + "</td>";
			tmp += "<td>" + item.getUser_logined() + "</td>";
			tmp += "<td><a href=\"/adv/user/profiles?t=o&id=" + item.getUser_id()
					+ "\" class=\"btn btn-primary btn-sm\"><i class=\"fa-solid fa-user-pen\"></i></a></td>";
			if(item.getUser_id()!= user.getUser_id()) {
				tmp += "<td class=\"text-center\"><a href=\"#\" class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#model"
						+ item.getUser_id() + "\"><i class=\"fa-solid fa-trash-can\"></i></a></td>";
			}else {
				tmp += "<td class=\"text-center\"><a href=\"#\" class=\"btn btn-secondary btn-sm\" disabled"+ item.getUser_id() + "\"><i class=\"fa-solid fa-trash-can\"></i></a></td>";
			}
			
			
			tmp += "</tr>";

			tmp += UserLibrary.getModal(item);
		}
		}

		tmp += "</tbody>";
		tmp += "</table>";
		tmp += "</div>";
		tmp += "</div>";
		
		
		//Chèn biểu đồ
		tmp += UserLibrary.viewChart(items);

		return tmp;
	}

	private static String getModal(UserObject item) {
		String tmp = "";

		tmp += "<div class=\"modal fade\" id=\"modal" + item.getUser_id()
				+ "\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">";
		tmp += "<div class=\"modal-dialog\">";
		tmp += "<div class=\"modal-content\">";
		tmp += "<div class=\"modal-header\">";
		tmp += "<h1 class=\"modal-title fs-5\" id=\"exampleModalLabel\"><i class=\"fa-solid fa-triangle-exclamation\"></i> Xác nhận xoá</h1>";
		tmp += "<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>";
		tmp += "</div>";
		tmp += "<div class=\"modal-body\">";
		tmp += "Bạn có chắc chắn xoá tài khoản <b>" + item.getUser_fullname() + "(" + item.getUser_name() + ")</b><br>";
		tmp += "Nếu không xoá được do người sử dụng này còn có liên quan tới các phần khác.";
		tmp += "</div>";
		tmp += "<div class=\"modal-footer\">";
		tmp += "<a href=\"/adv/user/del?id="+item.getUser_id()+"\" class=\"btn btn-danger\"><i class=\"fa-solid fa-trash-can\"></i> Xoá</a>";
		tmp += "<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"fa-solid fa-xmark\"></i> Thoát</button>";

		tmp += "</div>";
		tmp += "</div>";
		tmp += "</div>";
		tmp += "</div>";

		return tmp;
	}
	
	private static String viewChart(ArrayList<UserObject> items) {
		
		String data = "";
		String categories = "";
		if(!items.isEmpty()) {
			for(int i = 0; i < items.size(); i ++) {
				data  += items.get(i).getUser_logined() + ",";
				categories += "'" + Utilities.decodeFromHtml(items.get(i).getUser_fullname()) + "("+ Utilities.decodeFromHtml(items.get(i).getUser_name()+")',");
			}
			
			//loại bỏ dấu phẩy cuối cùng
			data = data.substring(0, data.length()-1);
			categories = categories.substring(0, categories.length()-1);
		}
			String tmp = "";
			
			tmp += "<div class=\"card\">";
			tmp += "<div class=\"card-body\">";
			tmp += "<h5 class=\"card-title\">Bar Chart</h5>";
			tmp += "";
			tmp += "<!-- Bar Chart -->";
			tmp += "<div id=\"barChart\"></div>";
			tmp += "";
			tmp += "<script>";
			tmp += "document.addEventListener(\"DOMContentLoaded\", () => {";
			tmp += "new ApexCharts(document.querySelector(\"#barChart\"), {";
			tmp += "series: [{";
			tmp += "data: ["+data+"]";
			tmp += "}],";
			tmp += "chart: {";
			tmp += "type: 'bar',";
			tmp += "height: 350";
			tmp += "},";
			tmp += "plotOptions: {";
			tmp += "bar: {";
			tmp += "borderRadius: 4,";
			tmp += "horizontal: true,";
			tmp += "}";
			tmp += "},";
			tmp += "dataLabels: {";
			tmp += "enabled: false";
			tmp += "},";
			tmp += "xaxis: {";
			tmp += "categories: ["+categories+"],";
			tmp += "}";
			tmp += "}).render();";
			tmp += "});";
			tmp += "</script>";
			tmp += "<!-- End Bar Chart -->";
			tmp += "";
			tmp += "</div>";
			tmp += "</div>";
			return tmp;
		}

	
	public static void main(String[] args) {
		
	}

}

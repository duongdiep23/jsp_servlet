package jsoft.ads.user;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import jsoft.ConnectionPool;
import jsoft.objects.*;

import jsoft.library.*;

/**
 * Servlet implementation class View
 */
@WebServlet("/user/profiles")
public class UserProfiles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Khai báo kiểu nội dung xuất về trình khách(client)
	private static final String CONTENT_TYPE = "text/html; charset = utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserProfiles() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Tìm thông tin đăng nhập trong phiên làm việc
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");

		if (user != null) {
			view(request, response);
		} else {
			response.sendRedirect("/adv/user/login");
		}
	}

	protected void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Xác định kiểu nội dung xuất về trình khách
		response.setContentType(CONTENT_TYPE);

		// Tạo đối tượng xuất nội dung về trình khách
		PrintWriter out = response.getWriter();

		// Tham chiếu servlet header thông qua đối tượng RequestDispatcher
		RequestDispatcher header = request.getRequestDispatcher("/header");
		if (header != null) {
			header.include(request, response);
		}

		// Tìm bộ quản lý kết nối
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");

		// Tạo đối tượng thực thi chức năng
		UserControl uc = new UserControl(cp);

		if (cp == null) {
			getServletContext().setAttribute("CPool", uc.getCP());
		}

		// Lấy cấu trúc trình bày
		// String view = uc.viewUsers(null, UserSort.NAME, (short) 1, (byte) 20);

		// Tìm tham số báo lỗi nếu có
//		String error = request.getParameter("err");
//		String message = "";
//
//		if (error != null) {
//			switch (error) {
//
//			case "param":
//				message = "Không chính xác tham số lấy giá trị!";
//				break;
//
//			case "value":
//				message = "Không có giá trị để tạo người sử dụng!";
//				break;
//
//			case "notok":
//				message = "Đăng nhập không thành công!";
//				break;
//
//			default:
//				message = "Không thành công!";
//			}
//		}
		// Trả về kết nối
		uc.releaseConnection();

		out.print("<main id=\"main\" class=\"main\">");
		out.print("");
		out.print("<div class=\"pagetitle\">");
		out.print("<h1>User management</h1>");
		out.print("<nav>");
		out.print("<ol class=\"breadcrumb\">");
		out.print("<li class=\"breadcrumb-item\"><a href=\"/adv/view\">Home</a></li>");
		out.print("<li class=\"breadcrumb-item\">Pages</li>");
		out.print("<li class=\"breadcrumb-item active\">Blank</li>");
		out.print("</ol>");
		out.print("</nav>");
		out.print("</div><!-- End Page Title -->");

		// ===============
		
		out.print("<section class=\"section profile\">");
		out.print("<div class=\"row\">");
		
		// Tham chiếu servlet header thông qua đối tượng RequestDispatcher
		RequestDispatcher avatar = request.getRequestDispatcher("/user/profiles/avatar");
		if (avatar != null) {
			avatar.include(request, response);
		}
		
		out.print("<div class=\"col-xl-8\">");
		
		out.print("<div class=\"card\">");
		out.print("<div class=\"card-body pt-3\">");
		out.print("<!-- Bordered Tabs -->");
		out.print("<ul class=\"nav nav-tabs nav-tabs-bordered\">");
		
		//Xác định vị trí của tab
		String tab = request.getParameter("t");
		
		//Khai báo đối tượng xác định active cho từng tab
		HashMap<String, String> active = new HashMap<>();
		
		if(tab != null) {
			if(tab.equalsIgnoreCase("o")) {
				active.put("overview", "active");
			}else if(tab.equalsIgnoreCase("e")) {
				active.put("edit", "active");
			}else if(tab.equalsIgnoreCase("p")) {
				active.put("changpass", "active");
			}
		}
		
		out.print("<li class=\"nav-item\">");
		out.print("<button class=\"nav-link "+active.getOrDefault("overview", "")+"\" data-bs-toggle=\"tab\" data-bs-target=\"#profile-overview\">Overview</button>");
		out.print("</li>");
		
		out.print("<li class=\"nav-item\">");
		out.print("<button class=\"nav-link "+active.getOrDefault("edit", "")+"\" data-bs-toggle=\"tab\" data-bs-target=\"#profile-edit\">Edit Profile</button>");
		out.print("</li>");
		
		out.print("<li class=\"nav-item\">");
		out.print("<button class=\"nav-link \" data-bs-toggle=\"tab\" data-bs-target=\"#profile-settings\">Settings</button>");
		out.print("</li>");
		
		out.print("<li class=\"nav-item\">");
		out.print("<button class=\"nav-link "+active.getOrDefault("changpass", "")+"\" data-bs-toggle=\"tab\" data-bs-target=\"#profile-change-password\">Change Password</button>");
		out.print("</li>");
		
		out.print("              </ul>");
		
		out.print("<div class=\"tab-content pt-2\">");
		out.print("");
		// Tham chiếu servlet header thông qua đối tượng RequestDispatcher
		RequestDispatcher overview = request.getRequestDispatcher("/user/profiles/overview");
		if (overview != null) {
			overview.include(request, response);
		}
		
		
		// Tham chiếu servlet header thông qua đối tượng RequestDispatcher
		RequestDispatcher edit = request.getRequestDispatcher("/user/profiles/edit");
		if (edit != null) {
			edit.include(request, response);
		}
		
		// Tham chiếu servlet header thông qua đối tượng RequestDispatcher
		RequestDispatcher setting = request.getRequestDispatcher("/user/profiles/setting");
		if (setting != null) {
			setting.include(request, response);
		}
		
		// Tham chiếu servlet header thông qua đối tượng RequestDispatcher
		RequestDispatcher changepass = request.getRequestDispatcher("/user/profiles/changepass");
		if (changepass != null) {
			changepass.include(request, response);
		}
		out.print("");

		out.print("");
		out.print("</div><!-- End Bordered Tabs -->");
		out.print("");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("</div>");
		out.print("</div>");
		out.print("</section>");
		
		// ===============

		out.print("</main><!-- End #main -->");

		// Tham chiếu servlet footer thông qua đối tượng RequestDispatcher
		RequestDispatcher footer = request.getRequestDispatcher("/footer");
		if (footer != null) {
			footer.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}


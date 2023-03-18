package jsoft.ads.user.profiles;

import java.io.IOException;
import jsoft.library.*;
import jsoft.objects.UserObject;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsoft.ConnectionPool;
import jsoft.ads.user.UserControl;
import jsoft.ads.user.UserSort;

/**
 * Servlet implementation class Overview
 */
@WebServlet("/user/profiles/overview")
public class overview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Khai báo kiểu nội dung xuất về trình khách(client)
	private static final String CONTENT_TYPE = "text/html; charset = utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public overview() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Xác định kiểu nội dung xuất về trình khách
		response.setContentType(CONTENT_TYPE);

		// Tạo đối tượng xuất nội dung về trình khách
		PrintWriter out = response.getWriter();
		
		//tìm id để xem chi tiết thông tin
		int id = Utilities.getIntParam(request, "id");
		String name = "", fullname = "", email = "", address = "";
		String create_date = "", mobile = "", office = "", notes = "";

		
		if(id>0) {
		
			//tìm bộ quản lý kết nối
			ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
			
			//tạo đối tượng thực thi chức năg
			UserControl uc = new UserControl(cp);
			if(cp==null) {
				getServletContext().setAttribute("CPool", uc.getCP());
			}
			
			//lấy đối tượng để xem thông tin 
			UserObject user = uc.getUserObject(id);
			
			
			//trả về kết nối
			uc.releaseConnection();
			
			//tách thông tin
			if(user != null) {
				name = user.getUser_name();
				fullname = user.getUser_fullname();
				email = user.getUser_email();
				address = user.getUser_address();
				create_date = user.getUser_created_date();
				mobile  = user.getUser_mobilephone();
				office = user.getUser_officephone();
				notes = user.getUser_notes();
			}
		}
		
		//Xác định vị trí
		String tab = request.getParameter("t");
		String show = "";
		if(tab != null && tab.equalsIgnoreCase("o")) {
			show = "show active";
		}
		
		out.print("<div class=\"tab-pane fade "+show+" profile-overview\" id=\"profile-overview\">");
		out.print("<h5 class=\"card-title\">About</h5>");
		out.print("<p class=\"small fst-italic\">"+notes+"</p>");
		
		out.print("<h5 class=\"card-title\">Profile Details</h5>");

		out.print("<div class=\"row\">");
		out.print("<div class=\"col-lg-3 col-md-4 label \">Full Name</div>");
		out.print("<div class=\"col-lg-9 col-md-8\">"+fullname+" ("+name+")</div>");
		out.print("</div>");
		
		out.print("<div class=\"row\">");
		out.print("<div class=\"col-lg-3 col-md-4 label\">Mobile</div>");
		out.print("<div class=\"col-lg-9 col-md-8\">"+mobile+"</div>");
		out.print("</div>");
		
		out.print("<div class=\"row\">");
		out.print("<div class=\"col-lg-3 col-md-4 label\">Notes</div>");
		out.print("<div class=\"col-lg-9 col-md-8\">"+notes+"</div>");
		out.print("</div>");
		
		out.print("<div class=\"row\">");
		out.print("<div class=\"col-lg-3 col-md-4 label\">Office</div>");
		out.print("<div class=\"col-lg-9 col-md-8\">"+office+"</div>");
		out.print("</div>");
		
		out.print("<div class=\"row\">");
		out.print("<div class=\"col-lg-3 col-md-4 label\">Address</div>");
		out.print("<div class=\"col-lg-9 col-md-8\">"+address+"</div>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"row\">");
		out.print("<div class=\"col-lg-3 col-md-4 label\">Creeate</div>");
		out.print("<div class=\"col-lg-9 col-md-8\">"+create_date+"</div>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"row\">");
		out.print("<div class=\"col-lg-3 col-md-4 label\">Email</div>");
		out.print("<div class=\"col-lg-9 col-md-8\">"+email+"</div>");
		out.print("</div>");
		out.print("");
		out.print("</div>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

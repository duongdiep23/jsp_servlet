package jsoft.ads.user.profiles;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsoft.ConnectionPool;
import jsoft.ads.user.UserControl;
import jsoft.library.Utilities;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class EditProfiles
 */
@WebServlet("/user/profiles/edit")
public class edit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Khai báo kiểu nội dung xuất về trình khách(client)
	private static final String CONTENT_TYPE = "text/html; charset = utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public edit() {
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
		String name = "",email = "", fullname = "", address = "";
		
		String create_date = "", mobile = "", office = "", notes = "";
		
	
		
		boolean isEdit = false;
		
		if(id>0 ) {
		
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
				
				isEdit = true;
			}
		}
		
		//Xác định vị trí
		String tab = request.getParameter("t");
		String show = "";
		if(tab != null && tab.equalsIgnoreCase("e")) {
			show = "show active";
		}
				
		out.print("<div class=\"tab-pane fade "+show+" profile-edit pt-3\" id=\"profile-edit\">");
		
		out.print("<!-- Profile Edit Form -->");
		out.print("<form>");
		out.print("<div class=\"row mb-3\">");
		out.print("<label for=\"profileImage\" class=\"col-md-4 col-lg-3 col-form-label\">Profile Image</label>");
		out.print("<div class=\"col-md-8 col-lg-9\">");
		out.print("<img src=\"/adv/img/profile-img.jpg\" alt=\"Profile\">");
		out.print("<div class=\"pt-2\">");
		out.print("<a href=\"#\" class=\"btn btn-primary btn-sm\" title=\"Upload new profile image\"><i class=\"bi bi-upload\"></i></a>");
		out.print("<a href=\"#\" class=\"btn btn-danger btn-sm\" title=\"Remove my profile image\"><i class=\"bi bi-trash\"></i></a>");
		out.print("</div>");
		out.print("</div>");
		out.print("</div>");
		
		out.print("<div class=\"row mb-3\">");
		out.print("<label for=\"fullName\" class=\"col-md-4 col-lg-3 col-form-label\">Full Name</label>");
		out.print("<div class=\"col-md-5 col-lg-6\">");
		out.print("<input name=\"fullname\" type=\"text\" class=\"form-control\" id=\"fullname\" value=\""+fullname+"\">");

		out.print("</div>");
		
		out.print("<div class=\"col-md-3 col-lg-3\">");
		out.print("<input name=\"username\" type=\"text\" class=\"form-control\" id=\"userName\" disabled value=\""+name+"\">");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"row mb-3\">");
		out.print("<label for=\"about\" class=\"col-md-4 col-lg-3 col-form-label\">Notes</label>");
		out.print("<div class=\"col-md-8 col-lg-9\">");
		out.print("<textarea name=\"notes\" class=\"form-control\" id=\"about\" style=\"height: 100px\">"+notes+"</textarea>");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"row mb-3\">");
		out.print("<label for=\"company\" class=\"col-md-4 col-lg-3 col-form-label\">Mobile</label>");
		out.print("<div class=\"col-md-8 col-lg-9\">");
		out.print("<input name=\"mobile\" type=\"text\" class=\"form-control\" id=\"company\" value=\""+mobile+"\">");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"row mb-3\">");
		out.print("<label for=\"Job\" class=\"col-md-4 col-lg-3 col-form-label\">Office</label>");
		out.print("<div class=\"col-md-8 col-lg-9\">");
		out.print("<input name=\"office\" type=\"text\" class=\"form-control\" id=\"Job\" value=\""+office+"\">");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"row mb-3\">");
		out.print("<label for=\"Country\" class=\"col-md-4 col-lg-3 col-form-label\">Create</label>");
		out.print("<div class=\"col-md-8 col-lg-9\">");
		out.print("<input name=\"create\" type=\"text\" class=\"form-control\" id=\"Country\" value=\""+create_date+"\">");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"row mb-3\">");
		out.print("<label for=\"Address\" class=\"col-md-4 col-lg-3 col-form-label\">Address</label>");
		out.print("<div class=\"col-md-8 col-lg-9\">");
		out.print("<input name=\"address\" type=\"text\" class=\"form-control\" id=\"Address\" value=\""+address+"\">");
		out.print("</div>");
		out.print("</div>");
		
		out.print("<div class=\"row mb-3\">");
		out.print("<label for=\"Email\" class=\"col-md-4 col-lg-3 col-form-label\">Email</label>");
		out.print("<div class=\"col-md-8 col-lg-9\">");
		out.print("<input name=\"email\" type=\"email\" class=\"form-control\" id=\"Email\" value=\""+email+"\">");
		out.print("</div>");
		out.print("</div>");
		

		out.print("<div class=\"text-center\">");
		out.print("<button type=\"button\" class=\"btn btn-primary\" onclick=\"editProfile(this.form)\">Save Changes</button>");
		out.print("</div>");
		
		//truyền id để ghi nhận sự cập nhật theo cơ chế biến form ẩn
		if(isEdit) {
			out.print("<input type=\"hidden\" name=\"id\" value=\""+id+"\"/>");
		}
		
		out.print("</form><!-- End Profile Edit Form -->");
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
		
		//thiết lập tập ký tự
		request.setCharacterEncoding("utf-8");
		
		//tìm id để xem cập nhật thông tin
		int id = Utilities.getIntParam(request, "id");
		String fullname = "", address = "";
		String email = request.getParameter("email");
		String create_date = "", mobile = "", office = "", notes = "";
	
		
		if(id>0 && email !=null && email.equalsIgnoreCase("")) {
		
			//tìm bộ quản lý kết nối
			ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
			
			//tạo đối tượng thực thi chức năg
			UserControl uc = new UserControl(cp);
			if(cp==null) {
				getServletContext().setAttribute("CPool", uc.getCP());
			}
			
			//lấy đối tượng để cập nhật thông tin 
			UserObject user = uc.getUserObject(id);
			
			//lấy tiếp thông tin ms nhất
			fullname = request.getParameter("fullname");
			
			notes = request.getParameter("notes");
			office = request.getParameter("office");
			mobile = request.getParameter("mobile");
			address = request.getParameter("address");
			create_date = request.getParameter("create");
			
			//bổ sung thông tin
			if(user != null) {
				user.setUser_fullname(Utilities.encodeToHtml(fullname));
				user.setUser_email(email);
				user.setUser_notes(notes);
				user.setUser_officephone(office);
				user.setUser_mobilephone(mobile);
				user.setUser_address(Utilities.encodeToHtml(address));
				user.setUser_created_date(create_date);
				
			}
			
			//cập nhật trở lại
			boolean result = uc.editUser(user);
			
			
			//trả về kết nối
			uc.releaseConnection();
			//ktra 
			if(result) {
				response.sendRedirect("/adv/user/view");
			}else {
				response.sendRedirect("/adv/user/profiles?id="+id+"&err=notok");
			}
		}else {
			response.sendRedirect("/adv/user/view?err=value");
		}
	}

}

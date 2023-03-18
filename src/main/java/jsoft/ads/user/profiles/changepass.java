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
 * Servlet implementation class ChangePass
 */
@WebServlet("/user/profiles/changepass")
public class changepass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Khai báo kiểu nội dung xuất về trình khách(client)
	private static final String CONTENT_TYPE = "text/html; charset = utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public changepass() {
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
		
		//Tìm id để xem chi tiết thông tin
		int id = Utilities.getIntParam(request, "id");
		boolean isEdit = false;
		
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
				isEdit = true;
			}
			
		}
		
		//Xác định vị trí
		String tab = request.getParameter("t");
		String show = "";
		if(tab != null && tab.equalsIgnoreCase("p")) {
			show = "show active";
		}
		
		out.print("<div class=\"tab-pane fade "+show+" pt-3\" id=\"profile-change-password\">");
		out.print("<!-- Change Password Form -->");
		out.print("<form>");
		
		out.print("<div class=\"row mb-3\">");
		out.print("<label for=\"currentPassword\" class=\"col-md-4 col-lg-3 col-form-label\">Current Password</label>");
		out.print("<div class=\"col-md-7 col-lg-8\">");
		out.print("<input name=\"password\" type=\"password\" class=\"form-control\" id=\"currentPassword\">");
		out.print("</div>");
		out.print("</div>");
		
		out.print("<div class=\"row mb-3\">");
		out.print("<label for=\"newPassword\" class=\"col-md-4 col-lg-3 col-form-label\">New Password</label>");
		out.print("<div class=\"col-md-7 col-lg-8\">");
		out.print("<input name=\"newpassword\" type=\"password\" class=\"form-control\" id=\"newPassword\" onkeyup=\"checkValidChangePass(this.form)\">");
		out.print("</div>");
		out.print("<div class=\"col-md-1 col-lg-1 text-start px-1\" id=\"iconNewPass\" >");
		out.print("</div>");
		out.print("</div>");
		
		out.print("<div class=\"row mb-3\">");
		out.print("<label for=\"renewPassword\" class=\"col-md-4 col-lg-3 col-form-label\">Re-enter New Password</label>");
		out.print("<div class=\"col-md-7 col-lg-8\">");
		out.print("<input name=\"renewpassword\" type=\"password\" class=\"form-control\" id=\"renewPassword\" onkeyup=\"checkValidChangePass(this.form)\">");
		out.print("</div>");
		out.print("<div class=\"col-md-1 col-lg-1 text-start px-1\" id=\"iconReNewPass\">");
		out.print("</div>");
		out.print("</div>");
		
		out.print("<div class=\"text-center\">");
		out.print("<button type=\"button\" class=\"btn btn-primary\" onclick=\"changePass(this.form)\">Change Password</button>");
		out.print("</div>");
		
		//truyền id để ghi nhận sự cập nhật
		if(isEdit) {
			//theo cơ chế biến form ẩn
			out.print("<input type=\"hidden\" name=\"txtID\" value=\""+id+"\"/>");
		}
		out.print("</form><!-- End Change Password Form -->");
		
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
		
		//tìm id để thay đổi mật khẩu
		int id = Utilities.getIntParam(request, "txtID");
		
		String oldpass = "";
		
		//lấy thông tin form changpass
		String pass = request.getParameter("password");
		String newpass = request.getParameter("newpassword");
		String renewpass = request.getParameter("renewpassword");
		
		if(id > 0 && pass != null && !pass.equalsIgnoreCase("")) {
			//tìm bộ quản lý kết nối
			ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
			
			//tạo đối tượng thực thi chức năg
			UserControl uc = new UserControl(cp);
			
			if(cp==null) {
				getServletContext().setAttribute("CPool", uc.getCP());
			}
			
			//lấy đối tượng để xem thông tin 
			UserObject user = uc.getUserObject(id);
			
			if(user!=null) {
				oldpass = user.getUser_pass();
			}
			
			if(oldpass.charAt(0)=='0') {
				oldpass = oldpass.substring(1, oldpass.length());
			}
			
			boolean result = false;
			
			pass = Utilities.convertToMD5(pass);
			
			if(pass.equalsIgnoreCase(oldpass)) {
				if(newpass !=null && !newpass.equalsIgnoreCase("") && renewpass != null && !renewpass.equalsIgnoreCase("")) {
					if(newpass.equalsIgnoreCase(renewpass)) {
						// Bổ xung thông tin
						user.setUser_id(id);
						user.setUser_pass(newpass);
						
						result = uc.changPass(user);
						
						if(result) {
							response.sendRedirect("/adv/user/logout");
						}else {
							response.sendRedirect("/adv/user/profiles?id="+id+"&t=p&err=notok");
						}
					}else {
						response.sendRedirect("/adv/user/profiles?id="+id+"&t=p&err=nopass");
					}
				}else {
					response.sendRedirect("/adv/user/profiles?id="+id+"&t=p&err=newpass");
				}
			}else {
				response.sendRedirect("/adv/user/profiles?id="+id+"&t=p&err=pass");
			}
			
			
			//trả về kết nối
			uc.releaseConnection();
		}else {
			response.sendRedirect("/adv/user/profiles?id="+id+"&t=p&err=value");
		}
	}
		

}

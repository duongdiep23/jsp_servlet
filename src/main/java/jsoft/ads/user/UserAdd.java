package jsoft.ads.user;

import java.io.*;
import jsoft.library.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import jsoft.*;
import jsoft.objects.*;

/**
 * Servlet implementation class View
 */
@WebServlet("/user/add")
public class UserAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	// Khai báo kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset = utf-8";

    public UserAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//tìm thông tin đăng nhập trong phiên làm việc
		UserObject user = (UserObject)request.getSession().getAttribute("userLogined");
		
		if(user!=null) {
			view(request, response);
		}else {
			response.sendRedirect("/adv/user/login");
		}
		
		
		
	}	
	protected void view(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Xác định kiểu nội dung xuất về trình khách

		response.setContentType(CONTENT_TYPE);

		// tạo đối tượng xuất nội dung về trình khách
		PrintWriter out = response.getWriter();
		
		//tham chiếu servlet header (tìm kiếm) thông qua đối tượng RequestDispatcher
		RequestDispatcher header = request.getRequestDispatcher("/header");
		if(header!=null) {
			header.include(request, response);
		}
		
		//tìm bộ quản lý kết nối
		ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
		
		//tạo đối tượng thực thi chức năg
		UserControl uc = new UserControl(cp);
		if(cp==null) {
			getServletContext().setAttribute("CPool", uc.getCP());
		}
		
		//lấy ds hiển thị
		//String view = uc.viewUsers(null, UserSort.NAME, (short)1, (byte)20);
		
		//trả về kết nối
		uc.releaseConnection();
		
		out.print("<main id=\"main\" class=\"main\">");
		
		out.print("<div class=\"pagetitle\">");
		out.print("<h1>User management</h1>");
		out.print("<nav>");
		out.print("<ol class=\"breadcrumb\">");
		out.print("<li class=\"breadcrumb-item\"><a href=\"/adv/view\">Home</a></li>");
		out.print("<li class=\"breadcrumb-item\">User management</li>");
		out.print("<li class=\"breadcrumb-item active\">Add user</li>");
		out.print("</ol>");
		out.print("</nav>");
		out.print("</div><!-- End Page Title -->");
		
		out.print("<section class=\"section\">");
		out.print("<div class=\"row\">");
		
		out.print("<div class=\"col-lg-12\">");
		
		out.print("<div class=\"card\">");
		out.print("<div class=\"card-body\">");
		out.print("<h5 class=\"card-title\">Account information</h5>");
		
		out.print("<form>");
		
		out.print("<div class=\"row my-2 align-items-center\">");
		out.print("<div class=\"col-md-2 text-end\">");
		out.print("<label for=\"name\" class=\"form-label\">User name</label>");
		out.print("</div>");

		out.print("<div class=\"col-md-4\">");
		out.print("<input type=\"text\" class=\"form-control\" id=\"name\" name=\"txtUserName\" onKeyup =\"checkUsername()\">");
		out.print("<div id=\"userHelp\" class=\"form-text\">Bạn nhập tên tài khoản chính xác</div>");
		out.print("</div>");

		out.print("<div class=\"col-md-4\">");
		out.print("<div id=\"errUsername\"></div>");
		out.print("</div>");

		out.print("</div>");

		out.print("<div class=\"row my-2 align-items-center\">");
		out.print("<div class=\"col-md-2 text-end\">");
		out.print("<label for=\"pass\" class=\"form-label\">Password</label>");
		out.print("</div>");
		out.print("<div class=\"col-md-3\">");
		out.print("<input type=\"password\" class=\"form-control\" id=\"pass\" name=\"txtUserPass\" onKeyup =\"checkUserpass()\">");
		out.print("</div>");

		out.print("<div class=\"col-md-2 text-end\">");
		out.print("<label for=\"pass2\" class=\"form-label\">Confirm Password</label>");
		out.print("</div>");
		out.print("<div class=\"col-md-3\">");
		out.print("<input type=\"password\" class=\"form-control\" id=\"pass2\" name=\"txtUserPass2\" onKeyup=\"checkUserpass()\">");
		out.print("<div id=\"errPass\"></div>");
		out.print("</div>");
		out.print("</div>");


		out.print("<div class=\"row my-2 mt-3 align-items-center\">");
		out.print("<div class=\"col-md-2 text-end\">");
		out.print("<label for=\"email\" class=\"form-label\">Email</label>");
		out.print("</div>");
		out.print("<div class=\"col-md-3\">");
		out.print("<input type=\"email\" class=\"form-control\" id=\"email\" name=\"txtUserEmail\" onKeyup=\"checkUseremail()\">");
		out.print("</div>");

		out.print("<div class=\"col-md-3\">");
		out.print("<div id=\"errEmail\"></div>");
		out.print("</div>");
		out.print("</div>");

		out.print("<div class=\"row my-2 mt-3 align-items-center\">");
		out.print("<div class=\"col-md-2 text-end\">");
		out.print("<label for=\"permis\" class=\"form-label\">Permission</label>");
		out.print("</div>");
		out.print("<div class=\"col-md-3\">");
		out.print("<script language=\"javascript\">");
		out.print("generatePermis();");
		out.print("</script>");
		out.print("</div>");
		out.print("</div>");
		
		out.print("<div class=\"row my-2 mt-3 align-items-center\">");
		out.print("<div class=\"col-md-2 text-end\">");
		out.print("<label for=\"name\" class=\"form-label\">Fullname</label>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"col-md-3\">");
		out.print("<input type=\"text\" class=\"form-control\" id=\"fullname\" name=\"txtFullname\" >");
		out.print("</div>");
		out.print("</div>");
		
		out.print("<div class=\"row align-items-center\">");
		out.print("<div class=\"col-md-12 text-center\">");
		out.print("<hr class=\"border border-1 border-primary\">");
		out.print("</div>");
		out.print("</div>");
		
		out.print("<div class=\"row my-3 \">");
		out.print("<div class=\"col-md-12 text-center\">");
		out.print("<button type=\"button\" class=\"btn btn-primary mx-2\" onclick= \"createAccount(this.form)\"><i class=\"fa-solid fa-right-to-bracket\"></i> Create Account </button>");
		out.print("<button type=\"submit\" class=\"btn btn-primary\"> Cancel <i class=\"fa-solid fa-arrow-right-from-bracket\"></i></button>");
		out.print("</div>");
		out.print("</div>");
		
		out.print("</form>");
		
		out.print("</div>");//card body
		out.print("</div>");//card
		
		out.print("</div>");
		out.print("</div>");
		out.print("</section>");
		
		out.print("</main><!-- End #main -->");
		
		RequestDispatcher footer = request.getRequestDispatcher("/footer");
		if(footer!=null) {
			footer.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//thiết lập tập ký tự cần lấy
		request.setCharacterEncoding("utf-8");
		
		
		//lấy thông tin bắt buộc trên giao diện
		String name = request.getParameter("txtUserName");
		String pass = request.getParameter("txtUserPass");
		String email = request.getParameter("txtUserEmail");
		
		if(name!=null && pass!=null) {
			//trường hợp name đã là email
			
			if(email==null) {
				email=name;
			}
			if(!name.equalsIgnoreCase("") && !pass.equalsIgnoreCase("") && !email.equalsIgnoreCase("")) {
				
				//lấy tiếp các thông tin không bắt buộc
				String fullname = request.getParameter("txtFullname");
				
				//tìm bộ quản lý kết nối
				ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
				
				//tạo đối tượng thực thi chức năg
				UserControl uc = new UserControl(cp);
				
				if(cp==null) {
					getServletContext().setAttribute("CPool", uc.getCP());
				}
				
				//tạo đối tg lưu trữ thông tin
				UserObject nUser = new UserObject();
				nUser.setUser_name(name);
				nUser.setUser_pass(pass);
				nUser.setUser_email(email);
				nUser.setUser_fullname(Utilities.encodeToHtml(fullname));
				nUser.setUser_created_date("06/11/2022");
				nUser.setUser_parent_id(5);
				
				//thực hiện
				boolean result = uc.addUser(nUser);
				
				//trả về kết nối
				uc.releaseConnection();
				
				//kiểm tra
				if(result) {
					response.sendRedirect("/adv/user/view");
				}else {
					response.sendRedirect("/adv/user/add?err=notok");
				}
				
			}else {
				response.sendRedirect("/adv/user/add?err=value");
			}
			
		}else {
			response.sendRedirect("/adv/user/add?err=param");
		}
	}

}

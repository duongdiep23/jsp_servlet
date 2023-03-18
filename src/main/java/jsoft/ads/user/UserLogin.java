package jsoft.ads.user;

import java.io.*;
import jsoft.*;
import jsoft.objects.*;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/user/login")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Khai báo kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset = utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * phương thức thường cung cấp 1 html trên giao diện. <br>
	 * Phương thức này thường đc gọi với 2 khả năng sau: <br>
	 * 1) Thông qua đường dẫn URL, vd: http://localhost:8080/adv/user/login<br>
	 * 2) Thông qua sự kiện của form, vd: method="get"<br>
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// request - đối tượng lưu trữ các yêu cầu cần xử lý từ trình khách gửi lên
		// response - đối tượng lưu trữ các kết quả đã được xử lý từ trình chủ gửi về

		// Xác định kiểu nội dung xuất về trình khách

		response.setContentType(CONTENT_TYPE);

		// tạo đối tượng xuất nội dung về trình khách
		PrintWriter out = response.getWriter();
		
		//tìm tham số báo lỗi nếu có
		String error = request.getParameter("err");
		String message = "";
		if(error!=null) {
			switch(error) {
			case "param":
				message = "Không chính xác tham số lấy giá trị";
				break;
			case "value":
				message = "Không có giá trị để đăng nhập";
				break;
			case "notok":
				message = "Đăng nhập không thành công";
				break;
			default:
				message = "Không thành công";
			}
		}
		

		// thực hiện xuất
		out.print("<!doctype html>");
		out.print("<html lang=\"en\">");
		out.print("<head>");
		out.print("<meta charset=\"utf-8\">");
		out.print("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		out.print("<meta name=\"description\" content=\"\">");
		out.print("<meta name=\"author\" content=\"Diep\">");
		out.print("<meta name=\"generator\" content=\"Version2\">");
		out.print("<title>Login</title>");
		out.print("<link href=\"/adv/css/bootstrap.min.css\" rel=\"stylesheet\">");
		out.print("<link href=\"/adv/css/all.min.css\" rel=\"stylesheet\">");
		
		out.print("<!-- Custom styles for this template -->");
		out.print("<link href=\"/adv/css/loginv3_thay.css\" rel=\"stylesheet\">");
		
		out.print("<script language=\"javascript\" src=\"/adv/js/loginv3.js\"></script>");
		out.print("</head>");
		out.print("<body>");
		out.print("<div class=\"container-lg\">");
		out.print("<div class=\"row\">");
		out.print("<div class=\"col-lg-6 offset-lg-3\">");
		out.print("<div class=\"loginview text-bg-light \">");
		out.print("<form>");
		
		if(!message.equalsIgnoreCase("")) {
			out.print("<div class=\"row my-3\">");
			out.print("<div class=\"col-12 text-center\">");
			out.print("<div class=\"text-bg-danger py-3 \"><i class=\"fa-solid fa-triangle-exclamation\"></i> "+message+" </div>");
			out.print("</div>");
			out.print("</div>");
		}
		
		
		out.print("<div class=\"row my-3\">");
		out.print("<div class=\"col-12 text-center\">");
		out.print("<div class=\"text-bg-primary py-3 loginview-title\"><i class=\"fa-solid fa-user\"></i> Login </div>");
		out.print("</div>");
		out.print("</div>");
		out.print("<div class=\"row my-3 align-items-center\">");
		out.print("<div class=\"col-md-4 text-end\">");
		out.print("<label for=\"user\" class=\"form-label\">Username / Email</label>");
		out.print("</div>");
		out.print("<div class=\"col-md-6\">");
		out.print("<input type=\"text\" class=\"form-control\" name=\"txtUserName\" id=\"user\" aria-describedby=\"userHelp\" onKeyup=\"checkValidUser(this.form)\">");
		out.print("<div id=\"userHelp\" class=\"form-text\">Bạn có thể nhập tên tài khoản hoặc hộp thư</div>");
		out.print("<div id=\"errUser\"></div>");
		out.print("</div>");
		out.print("</div>");
		out.print("<div class=\"row my-3 align-items-center\">");
		out.print("<div class=\"col-md-4 text-end\">");
		out.print("<label for=\"pass\" class=\"form-label\">Password</label>");
		out.print("</div>");
		out.print("<div class=\"col-md-6\">");
		out.print("<input type=\"password\" class=\"form-control\" name=\"txtUserPass\" id=\"pass\" onKeyup=\"checkValidUser(this.form)\">");
		out.print("<div id=\"errPass\"></div>");
		out.print("</div>");
		out.print("</div>");
		out.print("						");
		out.print("<div class=\" row my-3 align-items-center\">");
		out.print("<div class=\"col-4 text-end\">");
		out.print("<input type=\"checkbox\" class=\"form-check-input\" id=\"exampleCheck1\">");
		out.print("</div>");
		out.print("<div class=\"col-8\">");
		out.print("<label class=\"form-check-label\" for=\"exampleCheck1\">Save information</label>");
		out.print("</div>");
		out.print("</div>");
		out.print("<div class=\"row my-3 \">");
		out.print("<div class=\"col-md-12 text-center\">");
		out.print("<a href=\"#\" class=\"link-primary\"><i class=\"fa-solid fa-key\"></i> Forget password?</a>  &nbsp; | &nbsp;");
		out.print("<a href=\"#\" class=\"link-primary\"><i class=\"fa-solid fa-circle-question\"></i> Help!</a>");
		out.print("</div>");
		out.print("</div>");
		out.print("<div class=\"row my-3 \">");
		out.print("<div class=\"col-md-12 text-center\">");
		out.print("<button type=\"button\" class=\"btn btn-primary\" onclick=\"login(this.form)\"><i class=\"fa-solid fa-right-to-bracket\"></i> Login</button> &nbsp;"); 
		out.print("<button type=\"button\" class=\"btn btn-primary\" onclick=\"window.close()\">Exit <i class=\"fa-solid fa-arrow-right-from-bracket\"></i></button>");
		out.print("</div>");
		out.print("</div>");
		out.print("<div class=\"row my-3 \">");
		out.print("<div class=\"col-md-11 pb-3 text-end\">");
		out.print("<a href=\"#\" class=\"link-primary\"> <i class=\"fa-solid fa-language\"></i> Vietnamese</a></a> ");
		out.print("</div>");
		out.print("</div>");
		out.print("</form>");
		
		out.print("</div>");
		out.print("</div>");
		out.print("</div>");
		out.print("</div>");
		
		out.print("</body>");
		out.print("</html>");
		// Đóng luồng xuất
		out.close();

	}

	/**
	 * Phương thức thường thực hiện xử lý dữ liệu do doGet gửi tới <br>
	 * Phương thức này được gọi với sự kiện của form, method = "post"<br>
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Lấy thông tin trên giao diện
		String username = request.getParameter("txtUserName");
		String userpass = request.getParameter("txtUserPass");
		
		if(username != null && userpass != null) {
			if(!username.equalsIgnoreCase("") && !userpass.equalsIgnoreCase("")) {
				
				//tham chiếu ngữ cảnh ứng dụng
				ServletContext application = getServletConfig().getServletContext();
				
				//Tìm bộ quản lý kết nối trong ngữ cảnh ứng dụng
				ConnectionPool cp = (ConnectionPool)application.getAttribute("CPool");
				
				//tạo đối tượng thực thi chức năg
				UserControl uc = new UserControl(cp);
				if(cp==null) {
					application.setAttribute("CPool", uc.getCP());
				}
				
				//xử lý dữ liệu
				username = username.trim();
				userpass = userpass.trim();
				
				//thực hiện đăng nhập
				UserObject user = uc.getUserObject(username, userpass);
				
				//trả về kết nối
				uc.releaseConnection();
				
				//kiểm tra kết quả đăng nhập
				if(user!=null) {
					//tham chiếu phiên làm việc(sesstion)
					HttpSession session = request.getSession(true);
					
					//Đưa thông tin đăng nhập vào phiên
					session.setAttribute("userLogined", user);
					
					//trở về giao diện chính
					response.sendRedirect("/adv/view");
					
				}else {
					response.sendRedirect("/adv/user/login?err=notok");
				}
				
				
			}else {
				response.sendRedirect("/adv/user/login?err=value");
			}
			
		}else {
			//doGet(request, response);
			
			response.sendRedirect("/adv/user/login?err=param");
		}
	}

}

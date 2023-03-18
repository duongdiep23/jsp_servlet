package jsoft.ads.main;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import jsoft.*;
import jsoft.objects.*;

/**
 * Servlet implementation class View
 */
@WebServlet("/view")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	// Khai báo kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset = utf-8";

    public View() {
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
		
		
		out.print("");
		out.print("<main id=\"main\" class=\"main\">");
		out.print("");
		out.print("<div class=\"pagetitle\">");
		out.print("<h1>Blank Page</h1>");
		out.print("<nav>");
		out.print("<ol class=\"breadcrumb\">");
		out.print("<li class=\"breadcrumb-item\"><a href=\"index.html\">Home</a></li>");
		out.print("<li class=\"breadcrumb-item\">Pages</li>");
		out.print("<li class=\"breadcrumb-item active\">Blank</li>");
		out.print("</ol>");
		out.print("</nav>");
		out.print("</div><!-- End Page Title -->");
		
		out.print("<section class=\"section\">");
		out.print("<div class=\"row\">");
		out.print("<div class=\"col-lg-6\">");
		
		out.print("<div class=\"card\">");
		out.print("<div class=\"card-body\">");
		out.print("<h5 class=\"card-title\">Example Card</h5>");
		out.print("<p>This is an examle page with no contrnt. You can use it as a starter for your custom pages.</p>");
		out.print("</div>");
		out.print("</div>");
		
		out.print("</div>");
		
		out.print("<div class=\"col-lg-6\">");
		
		out.print("<div class=\"card\">");
		out.print("<div class=\"card-body\">");
		out.print("<h5 class=\"card-title\">Example Card</h5>");
		out.print("<p>This is an examle page with no contrnt. You can use it as a starter for your custom pages.</p>");
		out.print("</div>");
		out.print("</div>");
		
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
		doGet(request, response);
	}

}

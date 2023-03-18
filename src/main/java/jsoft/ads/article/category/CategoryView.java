package jsoft.ads.article.category;

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
@WebServlet("/category/view")
public class CategoryView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	// Khai báo kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset = utf-8";

    public CategoryView() {
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
		CategoryControl cc = new CategoryControl(cp);
		if(cp==null) {
			getServletContext().setAttribute("CPool", cc.getCP());
		}
		
		//lấy ds hiển thị
		String view = cc.viewCategories(null, CategoryShort.NAME, (short)1, (byte)10);
		
		//trả về kết nối
		cc.releaseConnection();
		
		
		out.print("<main id=\"main\" class=\"main\">");
		
		out.print("<div class=\"pagetitle\">");
		out.print("<h1>Category management</h1>");
		out.print("<nav>");
		out.print("<ol class=\"breadcrumb\">");
		out.print("<li class=\"breadcrumb-item\"><a href=\"/adv/view\">Home</a></li>");
		out.print("<li class=\"breadcrumb-item\">Pages</li>");
		out.print("<li class=\"breadcrumb-item active\">Blank</li>");
		out.print("</ol>");
		out.print("</nav>");
		out.print("</div><!-- End Page Title -->");
		
		out.print("<section class=\"section\">");
		out.print("<div class=\"row\">");
		
		out.print("<div class=\"col-lg-12\">");
		
		out.print(view);
		
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

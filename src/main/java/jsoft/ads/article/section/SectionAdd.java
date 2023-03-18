package jsoft.ads.article.section;

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
@WebServlet("/section/add")
public class SectionAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	// Khai báo kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset = utf-8";

    public SectionAdd() {
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
		SectionControl sc = new SectionControl(cp);
		if(cp==null) {
			getServletContext().setAttribute("CPool", sc.getCP());
		}
		
		//lấy ds hiển thị
		//String view = sc.viewSections(null, SectionSort.NAME, (short)1, (byte)20);
		
		//trả về kết nối
		sc.releaseConnection();
		
		out.print("<main id=\"main\" class=\"main\">");
		
		out.print("<div class=\"pagetitle\">");
		out.print("<h1>Section management</h1>");
		out.print("<nav>");
		out.print("<ol class=\"breadcrumb\">");
		out.print("<li class=\"breadcrumb-item\"><a href=\"/adv/view\">Home</a></li>");
		out.print("<li class=\"breadcrumb-item\">Section management</li>");
		out.print("<li class=\"breadcrumb-item active\">Add Section</li>");
		out.print("</ol>");
		out.print("</nav>");
		out.print("</div><!-- End Page Title -->");
		
		out.print("<section class=\"section\">");
		out.print("<div class=\"row\">");
		
		out.print("<div class=\"col-lg-12\">");
		
		out.print("<div class=\"card\">");
		out.print("<div class=\"card-body\">");
		out.print("<h5 class=\"card-title\">Section information</h5>");
		
		out.print("<form>");
		
		out.print("<!-- -->");
		out.print("<div class=\"row mt-3 align-items-center\">");
		out.print("<label class=\"col-lg-2 offset-1 text-end py-2\" for=\"sname\">");
		out.print("Tên chuyên mục:");
		out.print("</label>");
		out.print("<div class=\"col-lg-3\">");
		out.print("<input type=\"text\" class=\"form-control\" id=\"sname\" name=\"txtSName\" onkeyup=\"\">");
		out.print("</div>");
		out.print("");
		out.print("<label class=\"col-lg-2  text-end py-2\" for=\"sname1\">");
		out.print("Tên tiếng Anh:");
		out.print("</label>");
		out.print("<div class=\"col-lg-3\">");
		out.print("<input type=\"text\" class=\"form-control\" id=\"sname1\" name=\"txtSName1\" onkeyup=\"\">");
		out.print("</div>");
		out.print("");
		out.print("</div>");
		out.print("");
		out.print("<!-- -->");
		out.print("<div class=\"row mt-3 align-items-center\">");
		out.print("<label class=\"col-lg-2 offset-1 text-end py-2\" for=\"smid\">");
		out.print("Quản lý:");
		out.print("</label>");
		out.print("<div class=\"col-lg-3\">");
		out.print("<input type=\"text\" class=\"form-control\" id=\"smid\" onkeyup=\"\">");
		out.print("</div>");
		out.print("");
		out.print("<label class=\"col-lg-2 text-end py-2\" for=\"scdate\">");
		out.print("Ngày tạo:");
		out.print("</label>");
		out.print("<div class=\"col-lg-3\">");
		out.print("<input type=\"date\" class=\"form-control\" id=\"scdate\" name=\"txtSCDate\" onkeyup=\"\">");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("<!-- -->");
		out.print("<div class=\"row mt-3 align-items-center\">");
		out.print("<label class=\"col-lg-2 offset-1 text-end py-2\" for=\"slanguage\">");
		out.print("Ngôn ngữ:");
		out.print("</label>");
		out.print("<div class=\"col-lg-3\">");
		out.print("<select class=\"form-control\" id=\"slanguage\" name=\"slanguage \" onkeyup=\"\">");
		out.print("<option value=\"vietnam\" default>-------</option>");
		out.print("<option value=\"vietnam\">Việt Nam</option>");
		out.print("<option value=\"english\">English</option>");
		out.print("</select>");
		out.print("</div>");
		out.print("");
		out.print("</div>");
		out.print("");
		out.print("");
		out.print("<!-- -->");
		out.print("<div class=\"row mt-3 align-items-center\">");
		out.print("<label class=\"col-lg-3  text-end py-2\" for=\"snotes\">");
		out.print("Ghi chú tổng hợp:");
		out.print("</label>");
		out.print("<div class=\"col-lg-6\">");
		out.print("<input type=\"text\" style=\"height:4rem;\" class=\"form-control \" id=\"snotes\" name=\"txtSNotes\" >");
		out.print("</div>");
		out.print("");
		out.print("<div class = \"col-lg-1\" id=\"iconPassword\"></div>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"row mt-3 align-items-center\">");
		out.print("<div class=\"col-lg-10 mx-auto\">");
		out.print("<div class=\"border-top border-1 border-primary\"></div>");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("<div class = \"row mt-3 align-items-center\">");
		out.print("<div class = \"col-lg-10 mx-auto\">");
		out.print("<div class=\"text-danger\" id=\"errValue\"></div>");
		out.print("</div>");
		out.print("</div>");
		out.print("<div class=\"row mt-3 \">");
		out.print("<div class=\"col-md-12 text-center\">");
		out.print("<button type=\"button\" class=\"btn btn-primary mx-2\" id=\"send\" onclick=\"createSection(this.form)\"><i class=\"fa-solid fa-right-to-bracket\"></i> Send</button>");
		out.print("<button type=\"submit\" class=\"btn btn-primary\">Exit <i class=\"fa-solid fa-arrow-right-from-bracket\"></i></button>");
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
		
		//Lấy thông tin bắt buộc 
		String sname = request.getParameter("txtSName");
		
		
		if(sname != null) {
			
			if(!sname.equalsIgnoreCase("")) {
				
				//Lấy các thông tin còn lại
				String sname1 = request.getParameter("txtSName1");
				String scdate = request.getParameter("txtSCDate");
				String snotes = request.getParameter("txtSNotes");
				
				//Tìm bộ quản lý kết nối
				ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
				
				//Tạo đối tượng thực thi chức năng
				SectionControl sc = new SectionControl(cp);
				
				if(cp == null) {
					getServletContext().setAttribute("CPool", sc.getCP());
				}
				
				
				//Tạo đối tượng lưu trữ thông tin
				SectionObject nSection = new SectionObject();
				nSection.setSection_name(sname);
				nSection.setSection_name_en(sname1);
				nSection.setSection_created_date(Utilities.formatDate(scdate));
				nSection.setSection_notes(snotes);
				nSection.setSection_created_author_id(22);
				
				//Thực hiện
				boolean result = sc.addSection(nSection);
				
				//Trả về kết nối
				sc.releaseConnection();
				
				//Kiểm tra
				if(result) {
					response.sendRedirect("/adv/section/view");
					
				} else {
					response.sendRedirect("/adv/section/add?err=notok");
				}
			}else {
				response.sendRedirect("/adv/section/add?err=value");
			}
			
		}else {
			response.sendRedirect("/adv/section/add?err=param");
		}
		
		
	}

}

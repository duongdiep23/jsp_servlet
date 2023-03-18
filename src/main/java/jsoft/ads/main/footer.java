package jsoft.ads.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class footer
 */
@WebServlet("/footer")
public class footer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	// Khai báo kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset = utf-8";
    public footer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Xác định kiểu nội dung xuất về trình khách

		response.setContentType(CONTENT_TYPE);

		// tạo đối tượng xuất nội dung về trình khách
		PrintWriter out = response.getWriter();
		
		out.print("<!-- ======= Footer ======= -->");
		out.print("<footer id=\"footer\" class=\"footer\">");
		out.print("<div class=\"copyright\">");
		  out.print("&copy; Copyright <strong><span>NiceAdmin</span></strong>. All Rights Reserved");
		out.print("</div>");
		out.print("<div class=\"credits\">");
		  out.print("<!-- All the links in the footer should remain intact. -->");
		  out.print("<!-- You can delete the links only if you purchased the pro version. -->");
		  out.print("<!-- Licensing information: https://bootstrapmade.com/license/ -->");
		  out.print("<!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->");
		  out.print("Designed by <a href=\"https://jsoft.com/\">Dip Dip</a>");
		out.print("</div>");
		out.print("</footer><!-- End Footer -->");
		
		out.print("<a href=\"#\" class=\"back-to-top d-flex align-items-center justify-content-center\"><i class=\"bi bi-arrow-up-short\"></i></a>");
		
		out.print("<!-- Vendor JS Files -->");
		out.print("<script src=\"/adv/vendor/apexcharts/apexcharts.min.js\"></script>");
		out.print("<script src=\"/adv/js/bootstrap.bundle.min.js\"></script>");
		//out.print("<script src=\"assets/vendor/chart.js/chart.min.js\"></script>");
		//out.print("<script src=\"assets/vendor/echarts/echarts.min.js\"></script>");
		//out.print("<script src=\"assets/vendor/quill/quill.min.js\"></script>");
		//out.print("<script src=\"assets/vendor/simple-datatables/simple-datatables.js\"></script>");
		//out.print("<script src=\"assets/vendor/tinymce/tinymce.min.js\"></script>");
		//out.print("<script src=\"assets/vendor/php-email-form/validate.js\"></script>");
		
		out.print("<!-- Template Main JS File -->");
		out.print("<script src=\"/adv/js/main.js\"></script>");
		
		out.print("</body>");
		
		out.print("</html>");
		
		//Đóng luồng
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

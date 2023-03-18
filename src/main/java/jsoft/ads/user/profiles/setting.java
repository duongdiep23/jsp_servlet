package jsoft.ads.user.profiles;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Setting
 */
@WebServlet("/user/profiles/setting")
public class setting extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Khai báo kiểu nội dung xuất về trình khách(client)
	private static final String CONTENT_TYPE = "text/html; charset = utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public setting() {
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
		
		
		
		out.print("<div class=\"tab-pane fade pt-3\" id=\"profile-settings\">");
		out.print("");
		out.print("<!-- Settings Form -->");
		out.print("<form>");
		out.print("");
		out.print("<div class=\"row mb-3\">");
		out.print("<label for=\"fullName\" class=\"col-md-4 col-lg-3 col-form-label\">Email Notifications</label>");
		out.print("<div class=\"col-md-8 col-lg-9\">");
		out.print("<div class=\"form-check\">");
		out.print("<input class=\"form-check-input\" type=\"checkbox\" id=\"changesMade\" checked>");
		out.print("<label class=\"form-check-label\" for=\"changesMade\">");
		out.print("Changes made to your account");
		out.print("</label>");
		out.print("</div>");
		out.print("<div class=\"form-check\">");
		out.print("<input class=\"form-check-input\" type=\"checkbox\" id=\"newProducts\" checked>");
		out.print("<label class=\"form-check-label\" for=\"newProducts\">");
		out.print("Information on new products and services");
		out.print("</label>");
		out.print("</div>");
		out.print("<div class=\"form-check\">");
		out.print("<input class=\"form-check-input\" type=\"checkbox\" id=\"proOffers\">");
		out.print("<label class=\"form-check-label\" for=\"proOffers\">");
		out.print("Marketing and promo offers");
		out.print("</label>");
		out.print("</div>");
		out.print("<div class=\"form-check\">");
		out.print("<input class=\"form-check-input\" type=\"checkbox\" id=\"securityNotify\" checked disabled>");
		out.print("<label class=\"form-check-label\" for=\"securityNotify\">");
		out.print("Security alerts");
		out.print("</label>");
		out.print("</div>");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"text-center\">");
		out.print("<button type=\"submit\" class=\"btn btn-primary\">Save Changes</button>");
		out.print("</div>");
		out.print("</form><!-- End settings Form -->");
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

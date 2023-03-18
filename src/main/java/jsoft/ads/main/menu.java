package jsoft.ads.main;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class menu
 */
@WebServlet("/menu")
public class menu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	// Khai báo kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset = utf-8";
    public menu() {
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
		
		
		//Xác định vị trí
		String pos = request.getRequestURI().substring(5);
		
		int at = pos.indexOf("/");
		String menu = pos, submenu = "";
		HashMap<String, String> collapsed = new HashMap<>();
		HashMap<String, String> shows = new HashMap<>();
		HashMap<String, String> actives = new HashMap<>();
		
		if(at != -1) {
			
			menu = pos.substring(0, at);
			collapsed.put(menu, "");
			shows.put(menu, "show");
			
			//Xác định menu con
			submenu = pos.substring(at+1);
			if(menu.equalsIgnoreCase("user")) {
				if(submenu.contains("add")) {
					actives.put("add", "class=\"active\" ");
				}else if(submenu.contains("profiles")) {
					actives.put("profiles", "class=\"active\"");
				}else if(submenu.contains("view")) {
					actives.put("view", "class=\"active\"");
				}
				
			}
		}else {
			collapsed.put("view", "");
		}
		
		

		out.print("<!-- ======= Sidebar ======= -->");
		out.print("<aside id=\"sidebar\" class=\"sidebar\">");
	
		out.print("<ul class=\"sidebar-nav\" id=\"sidebar-nav\">");
	
		out.print("<li class=\"nav-item\">");
		out.print("<a class=\"nav-link "+collapsed.getOrDefault("view", "collapsed")+"\" href=\"/adv/view\">");
		out.print("<i class=\"bi bi-grid\"></i>");
		out.print("<span>Dashboard</span>");
		out.print("</a>");
		out.print("</li><!-- End Dashboard Nav -->");
		
		out.print("<li class=\"nav-item\">");
		out.print("<a class=\"nav-link "+collapsed.getOrDefault("user", "collapsed")+"\" data-bs-target=\"#user-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.print("<i class=\"bi bi-menu-button-wide\"></i><span>User management</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.print("</a>");
		out.print("<ul id=\"user-nav\" class=\"nav-content collapse "+shows.getOrDefault("user", "")+" \" data-bs-parent=\"#sidebar-nav\">");
		out.print("<li>");
		out.print("<a href=\"/adv/user/view\" "+actives.getOrDefault("view", "")+" >");
		out.print("<i class=\"bi bi-circle\"></i><span>User account</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("<li>");
		out.print("<a href=\"/adv/user/add\" "+actives.getOrDefault("add", "")+" >");
		out.print("<i class=\"bi bi-circle\"></i><span>Add user</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("<li>");
		out.print("<a href=\"/adv/user/profiles\" "+actives.getOrDefault("profiles", "")+" >");
		out.print("<i class=\"bi bi-circle\"></i><span>Update</span>");
		out.print("</a>");
		out.print("</li>");
		
		out.print("</ul>");
		out.print("</li><!-- End Components Nav -->");
		out.print("");
		out.print("<li class=\"nav-item\">");
		out.print("<a class=\"nav-link "+collapsed.getOrDefault("section", "collapsed")+"\" data-bs-target=\"#section-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.print("<i class=\"bi bi-journal-text\"></i><span>Section management</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.print("</a>");
		out.print("<ul id=\"section-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">");
		out.print("<li>");
		out.print("<a href=\"/adv/section/view\">");
		out.print("<i class=\"bi bi-circle\"></i><span>Section list</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("<li>");
		out.print("<a href=\"/adv/section/add\">");
		out.print("<i class=\"bi bi-circle\"></i><span>Add section</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("<li>");
		out.print("<a href=\"/adv/section/...\">");
		out.print("<i class=\"bi bi-circle\"></i><span>Template</span>");
		out.print("</a>");
		out.print("</li>");
		  
		out.print("</ul>");
		out.print("</li><!-- End Section Nav -->");
		
		out.print("<li class=\"nav-item\">");
		out.print("<a class=\"nav-link collapsed\" data-bs-target=\"#category-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.print("<i class=\"bi bi-layout-text-window-reverse\"></i><span>Category management</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.print("</a>");
		out.print("<ul id=\"category-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">");
		out.print("<li>");
		out.print("<a href=\"/adv/category/view\">");
		out.print("<i class=\"bi bi-circle\"></i><span>Category list</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("<li>");
		out.print("<a href=\"/adv/category/add\">");
		out.print("<i class=\"bi bi-circle\"></i><span>Add category</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("<li>");
		out.print("<a href=\"/adv/category/...\">");
		out.print("<i class=\"bi bi-circle\"></i><span>Template</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("</ul>");
		out.print("</li><!-- End Category Nav -->");
		
		out.print("<li class=\"nav-item\">");
		out.print("<a class=\"nav-link collapsed\" data-bs-target=\"#article-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.print("<i class=\"bi bi-bar-chart\"></i><span>Article management</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.print("</a>");
		out.print("<ul id=\"article-nav\" class=\"nav-content collapse \" data-bs-parent=\"#article-nav\">");
		out.print("<li>");
		out.print("<a href=\"/adv/article/view\">");
		out.print("<i class=\"bi bi-circle\"></i><span>Article list</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("<li>");
		out.print("<a href=\"/adv/article/add\">");
		out.print("<i class=\"bi bi-circle\"></i><span>Add article</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("<li>");
		out.print("<a href=\"/adv/article/....\">");
		out.print("<i class=\"bi bi-circle\"></i><span>Template</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("</ul>");
		out.print("</li><!-- End Article Nav -->");
		
		out.print("<li class=\"nav-item\">");
		out.print("<a class=\"nav-link collapsed\" data-bs-target=\"#icons-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.print("<i class=\"bi bi-gem\"></i><span>Icons</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.print("</a>");
		out.print("<ul id=\"icons-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">");
		out.print("<li>");
		out.print("<a href=\"icons-bootstrap.html\">");
		out.print("<i class=\"bi bi-circle\"></i><span>Bootstrap Icons</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("<li>");
		out.print("<a href=\"icons-remix.html\">");
		out.print("<i class=\"bi bi-circle\"></i><span>Remix Icons</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("<li>");
		out.print("<a href=\"icons-boxicons.html\">");
		out.print("<i class=\"bi bi-circle\"></i><span>Boxicons</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("</ul>");
		out.print("</li><!-- End Icons Nav -->");
		
		out.print("<li class=\"nav-heading\">Pages</li>");
		
		out.print("<li class=\"nav-item\">");
		out.print("<a class=\"nav-link "+collapsed.getOrDefault("profiles", "collapsed")+"\" href=\"users-profile.html\">");
		out.print("<i class=\"bi bi-person\"></i>");
		out.print("<span>Profile</span>");
		out.print("</a>");
		out.print("</li><!-- End Profile Page Nav -->");
		
		
		out.print("<li class=\"nav-item\">");
		out.print("<a class=\"nav-link "+collapsed.getOrDefault("blank", "collapsed")+"\" href=\"pages-blank.html\">");
		out.print("<i class=\"bi bi-file-earmark\"></i>");
		out.print("<span>Blank</span>");
		out.print("</a>");
		out.print("</li><!-- End Blank Page Nav -->");
		
		out.print("</ul>");
		
		out.print("</aside><!-- End Sidebar-->");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

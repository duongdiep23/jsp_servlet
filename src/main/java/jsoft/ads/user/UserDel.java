package jsoft.ads.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsoft.ConnectionPool;
import jsoft.library.Utilities;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class UserDel
 */
@WebServlet("/user/del")
public class UserDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		UserObject user = (UserObject)request.getSession().getAttribute("userLogined");
		
		//Tìm id để xoá
		int id = Utilities.getIntParam(request, "id");
		if(id>0 && id!=user.getUser_id()) {
			//tìm bộ quản lý kết nối
			ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
			
			//tạo đối tượng thực thi chức năg
			UserControl uc = new UserControl(cp);
			if(cp==null) {
				getServletContext().setAttribute("CPool", uc.getCP());
			}
			
			UserObject dUser = new UserObject();
			dUser.setUser_id(id);
			
			//Thực hiện xoá
			boolean result = uc.delUser(dUser);
			
			//trả về kết nối
			uc.releaseConnection();
			
			if(result) {
				response.sendRedirect("/adv/user/view");
			}else {
				response.sendRedirect("/adv/user/view?err=notok");
			}
			
		}else {
			response.sendRedirect("/adv/user/view?err=noid");
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

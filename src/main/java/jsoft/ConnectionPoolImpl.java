package jsoft;

import java.sql.Connection;
import java.sql.*;
import java.util.*;

public class ConnectionPoolImpl implements ConnectionPool {
	
	//tài khoản làm việc với cơ sở dữ liệu
	private String username;
	private String userpass;
	
	//trình điều khiển của mysql
	private String driver;
	
	//đường dẫn chạy mysql
	private String url;
	
	//đối tg lưu trữ kết nối
	private Stack<Connection> pool;
	
	public ConnectionPoolImpl() {
		//xác định t.khoản lm vc
		this.username = "root";
		this.userpass = "";
		
		//xác định trình điều khiển
		this.driver = "com.mysql.jdbc.Driver";
		
		//xác định đường dẫn thực thi
		this.url = "jdbc:mysql://localhost:3306/jp223_data";
		
		//khởi tạo đối tượng lưu trữ
		this.pool = new Stack<>();
		
		//Nạp trình điều khiển
		this.loadDriver();
	}
	
	
	private void loadDriver() {
		//thực hiện nạp trình điều khiển
		try {
			Class.forName(this.driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Connection getConnection(String objectName) throws SQLException {
		// TODO Auto-generated method stub
		
		if(this.pool.isEmpty()) {
			//khởi tạo kết nối mới
			System.out.println(objectName+"have created a new Connection.");
			return DriverManager.getConnection(this.url, this.username, this.userpass);
			
		}else {
			//lấy kết nối đã được lưu trữ trước đó
			System.out.println(objectName+"have popped the Connection.");
			return this.pool.pop();
		}
		
	}

	@Override
	public void releaseConnection(Connection con, String objectName) throws SQLException {
		// TODO Auto-generated method stub
		//yêu cầu đối tg trả về kết nối
		System.out.println(objectName+"have pushed the Connection.");
		this.pool.push(con);

	}
	
	//phương thức dọn dẹp
	protected void finalize() throws Throwable{
		//giải phóng dữ liệu
		this.url = null;
		this.username = null;
		this.userpass = null;
		this.driver = null;
		
		this.pool.clear();
		this.pool = null;
		
		System.out.println("CP is closed.");
	}
	
	
}

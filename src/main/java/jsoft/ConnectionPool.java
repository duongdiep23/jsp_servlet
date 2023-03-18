package jsoft;

import java.sql.*;

public interface ConnectionPool {
	//khởi tạo kết nối
	public Connection getConnection(String objectName) throws SQLException;
	
	//thu hồi kết nối
	public void releaseConnection(Connection con, String objectName) throws SQLException;
	
	
}

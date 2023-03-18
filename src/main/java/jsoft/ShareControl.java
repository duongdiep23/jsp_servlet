package jsoft;

public interface ShareControl {
	//chức năng điều khiển chia sẻ bộ quản lý kết nối giữa các basic
	public ConnectionPool getCP();
	
	//chức năng yêu cầu trả lại kết nối 
	public void releaseConnection();
	
}

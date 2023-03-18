package jsoft.ads.basic;

import java.sql.*;



import jsoft.*;

public class BasicImpl implements Basic {
	
	//đối tượng làm việc với basic
	private String objectName;
	
	//bộ quản lý kết nối của riêng basic làm việc
	private ConnectionPool cp;
	
	// kết nối mà basic sử dụng để làm việc với csdl
	protected Connection con;
	
	public BasicImpl(String objectName, ConnectionPool cp) {
		//xác định tên đối tượng làm việc với basic
		this.objectName = objectName;
		
		//xác định bộ quản lý kết nối làm việc cho basic
		if(cp==null) {
			this.cp = new ConnectionPoolImpl();
		}else {
			this.cp = cp;
		}
		
		//xin kết nối để làm việc 
		try {
			this.con = this.cp.getConnection(this.objectName);
			
			//kiểm tra chế độ thực thi của kết nối
			if(this.con.getAutoCommit()) {
				this.con.setAutoCommit(false); //huỷ thực thi tự động
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * phương thức thực hiện cập nhật vào csdl, dùng chung cho add, edit, del
	 * @param pre
	 * @return
	 */
	private synchronized boolean exe(PreparedStatement pre) {
		if(pre != null) {
			
			try {
				int result = pre.executeUpdate();
				
				if(result==0) {
					//không có bản ghi nào được thực hiện
					this.con.rollback();
					return false;	
				}
				
				//xác nhận thực thi sau cùng vào csdl
				this.con.commit();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			//trở lại trạng thái an toàn của kết nối
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
			}finally {
				pre = null;
			}
		}
		return false;
	}

	@Override
	public boolean add(PreparedStatement pre) {
		// TODO Auto-generated method stub
		return this.exe(pre);
	}

	@Override
	public boolean edit(PreparedStatement pre) {
		// TODO Auto-generated method stub
		return this.exe(pre);
	}

	@Override
	public boolean del(PreparedStatement pre) {
		// TODO Auto-generated method stub
		return this.exe(pre);
	}

	@Override
	public ResultSet get(String sql, int value) {
		// TODO Auto-generated method stub
		
		//Biên dịch câu lệnh sql 
		try {
			PreparedStatement preGet = this.con.prepareStatement(sql);
			if(value>0) {
				preGet.setInt(1, value);
			}
			
			//trả về kết quả
			return preGet.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			//trở về trạng thái an toàn
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public ResultSet get(String sql, String name, String pass) {
		// TODO Auto-generated method stub
		
		try {
			PreparedStatement preGet = this.con.prepareStatement(sql);
			preGet.setString(1, name);
			preGet.setString(2, pass);
			
			//trả về kết quả
			return preGet.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public ResultSet gets(String sql) {
		// TODO Auto-generated method stub
		return this.get(sql, 0);
	}

	@Override
	public ConnectionPool getCP() {
		// TODO Auto-generated method stub
		return this.cp;
	}

	@Override
	public void releaseConnection() {
		// TODO Auto-generated method stub
		
		try {
			this.cp.releaseConnection(this.con, this.objectName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

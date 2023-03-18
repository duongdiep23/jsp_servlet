package jsoft.ads.basic;

import java.sql.ResultSet;
import java.sql.*;
import jsoft.*;
public interface Basic extends ShareControl{
	//đã được biên dịch, đã đc truyền đầy đủ gtri
	
	public boolean add(PreparedStatement pre);
	public boolean edit(PreparedStatement pre);
	public boolean del(PreparedStatement pre);
	
	public ResultSet get(String sql, int value);
	public ResultSet get(String sql, String name, String pass);
	
	public ResultSet gets(String sql);
	

}

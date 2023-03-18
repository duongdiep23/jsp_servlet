package jsoft.ads.user;

import java.sql.*;

import jsoft.ShareControl;
import jsoft.objects.UserObject;


public interface User extends ShareControl {

	//các chức năng cập nhật
	public boolean addUser(UserObject item);
	public boolean editUser(UserObject item);
	public boolean delUser(UserObject item);
	
	public ResultSet getUser(int id);
	public ResultSet getUser(String username, String userpass);
	public ResultSet getUsers(UserObject similar, int at, byte total);
	public ResultSet getUsers(UserObject similar,UserSort us, int at, byte total);
	
	public boolean changePassword(UserObject item);
}

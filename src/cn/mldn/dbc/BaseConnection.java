package cn.mldn.dbc;

import java.sql.Connection;
import java.sql.DriverManager;


public class BaseConnection {

	private static final String DRIVER="com.mysql.jdbc.Driver";
	private static final String URL="jdbc:mysql://localhost:3306/mldn";
	private static final String USER="root";
	private static final String PASSWORD="mysqladmin";
	private static ThreadLocal<Connection> local=new ThreadLocal<Connection>();
	
	private static Connection connectDate() {
		Connection conn=null;
		try {
		if(conn==null) {
			Class.forName(DRIVER);
			conn=DriverManager.getConnection(URL, USER, PASSWORD);
		}
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			
		}
		return conn;
	}
	
	public static Connection getConnection() {
		Connection conn=local.get();
		if(conn==null) {
			conn=connectDate();
			local.set(conn);
		}
		return conn;
	}
	
	public static void close() {
		Connection conn=local.get();
		if(conn!=null) {
			try {
				conn.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			local.remove();
		}
	}
	
}

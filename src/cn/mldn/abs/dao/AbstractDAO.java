package cn.mldn.abs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import cn.mldn.dbc.BaseConnection;

public class AbstractDAO {

	protected PreparedStatement pser;
	protected Connection conn=null;
	public AbstractDAO() {
		conn=BaseConnection.getConnection();
	}
}

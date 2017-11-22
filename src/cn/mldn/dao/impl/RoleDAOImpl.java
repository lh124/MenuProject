package cn.mldn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.mldn.abs.dao.AbstractDAO;
import cn.mldn.dao.IRoleDAO;
import cn.mldn.vo.Role;

public class RoleDAOImpl extends AbstractDAO implements IRoleDAO {

	@Override
	public boolean doCreate(Role vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Role vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<String> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Role findById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findAll(Long pagesize, Integer linesize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findSplit(String culum, String keyword, Long pagesize, Integer linesize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getCount() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getCountSplit(String culum, String keyword) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> findAllByMember(String mid) throws SQLException {
		Set<String> all=new HashSet<>();
		String sql="select rid from member_role where mid= ? ";
		super.pser=super.conn.prepareStatement(sql);
		super.pser.setString(1, mid);
		ResultSet rs=super.pser.executeQuery();
		while(rs.next()) {
			all.add(rs.getString(1));
		}
		return all;
	}

}

package cn.mldn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.mldn.abs.dao.AbstractDAO;
import cn.mldn.dao.IActionDAO;
import cn.mldn.vo.Action;

public class ActionDAOImpl extends AbstractDAO implements IActionDAO{

	@Override
	public boolean doCreate(Action vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Action vo) throws SQLException {
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
	public Action findById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> findAll(Long pagesize, Integer linesize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> findSplit(String culum, String keyword, Long pagesize, Integer linesize) throws SQLException {
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

		String sql="select actid from action where rid in(select rid from member where mid=?)";
		super.pser=super.conn.prepareStatement(sql);
		super.pser.setString(1, mid);
		ResultSet rs=super.pser.executeQuery();
		while(rs.next()) {
			all.add(rs.getString(1));
		}
		return all;
	}

}

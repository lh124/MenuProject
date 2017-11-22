package cn.mldn.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import cn.mldn.abs.dao.AbstractDAO;
import cn.mldn.dao.IMemberLogs;
import cn.mldn.vo.Member_logs;

public class MemberLogsImpl extends AbstractDAO implements IMemberLogs{

	@Override
	public boolean doCreate(Member_logs vo) throws SQLException {
		String sql="insert into member_logs (mid,logintime) value (?,?)";
		super.pser=super.conn.prepareStatement(sql);
		super.pser.setString(1,vo.getMid());
		super.pser.setTimestamp(2, new java.sql.Timestamp(vo.getLogintime().getTime()));
		return super.pser.executeUpdate()>0;
	}

	@Override
	public boolean doUpdate(Member_logs vo) throws SQLException {
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
	public Member_logs findById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member_logs> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member_logs> findAll(Long pagesize, Integer linesize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member_logs> findSplit(String culum, String keyword, Long pagesize, Integer linesize)
			throws SQLException {
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

}

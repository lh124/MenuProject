package cn.mldn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.Set;

import cn.mldn.abs.dao.AbstractDAO;
import cn.mldn.dao.IMemberDAO;
import cn.mldn.factory.Factory;
import cn.mldn.vo.Member;

public class MemberDAOImpl extends AbstractDAO implements IMemberDAO {

	@Override
	public boolean doCreate(Member vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Member vo) throws SQLException {
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
	public Member findById(String id) throws SQLException {

		String sql="select mid,name,password,lastdate,locked,trycount,locktime from member where mid=? ";
		Member member=null;
		super.pser=super.conn.prepareStatement(sql);
		super.pser.setString(1, id);
		ResultSet rs=super.pser.executeQuery();
		while(rs.next()) {
			member=new Member();
			member.setMid(rs.getString(1));
			member.setName(rs.getString(2));
			member.setPassword(rs.getString(3));
			member.setLastdate(rs.getTimestamp(4));
			member.setLocked(rs.getInt(5));
			member.setTrycount(rs.getInt(6));
			member.setLocktime(rs.getTimestamp(7));

		}
		return member;
	}

	@Override
	public List<Member> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> findAll(Long pagesize, Integer linesize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> findSplit(String culum, String keyword, Long pagesize, Integer linesize) throws SQLException {
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


	public static void main(String[] args) throws SQLException {
		IMemberDAO dao=Factory.getInstanece("member.dao");
		System.out.println(dao.findById("mldn"));

	}

	@Override
	public boolean doUpdateTrycount(String id, Integer trycount) throws SQLException {
		String sql="update member set trycount =? where mid=? ";
		super.pser=super.conn.prepareStatement(sql);
		super.pser.setInt(1, trycount);
		super.pser.setString(2, id);

		return super.pser.executeUpdate()>0;
	}

	@Override
	public boolean doUpdateLockedTime(String id, Date date) throws SQLException {
		String sql="update member set locktime =? where mid=? ";
		super.pser=super.conn.prepareStatement(sql);
		if(date==null) {
			super.pser.setNull(1,Types.NULL);
		}else {
			super.pser.setTimestamp(1,new  java.sql.Timestamp(date.getTime()));
		}
		super.pser.setString(2, id);

		return super.pser.executeUpdate()>0;
	}

	@Override
	public boolean doupdateLocked(Member member) throws SQLException {
		String sql="update member set locked=? where mid=?";
		super.pser=super.conn.prepareStatement(sql);
		super.pser.setInt(1, member.getLocked());
		super.pser.setString(2, member.getMid());
		return super.pser.executeUpdate()>0;
	}

	@Override
	public boolean doupdateLastdate(String id, Date date) throws SQLException {
		String sql="update member set lastdate =? where mid=? ";
		super.pser=super.conn.prepareStatement(sql);
		super.pser.setTimestamp(1,new  java.sql.Timestamp(date.getTime()));
		super.pser.setString(2, id);

		return super.pser.executeUpdate()>0;
	}
}

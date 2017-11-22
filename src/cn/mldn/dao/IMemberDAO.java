package cn.mldn.dao;

import java.sql.SQLException;
import java.util.Date;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vo.Member;

public interface IMemberDAO extends IBaseDAO<String, Member>{

	  /**
	   * 尝试次数
	   * @param id
	   * @param trycount
	   * @return
	   * @throws SQLException
	   */
	public boolean doUpdateTrycount(String id,Integer trycount)throws SQLException;
	/**
	 * 获取锁定时间
	 * @param id
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateLockedTime(String id,Date date)throws SQLException;
	/**
	 * 锁定用户
	 * @param member
	 * @return
	 * @throws SQLException
	 */
	public boolean doupdateLocked(Member member)throws SQLException;
	/**
	 * 获取上一次的登录时间
	 * @param id
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	public boolean doupdateLastdate(String id,Date date) throws SQLException;
	
	
}

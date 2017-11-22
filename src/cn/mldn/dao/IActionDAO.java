package cn.mldn.dao;

import java.sql.SQLException;
import java.util.Set;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vo.Action;

public interface IActionDAO extends IBaseDAO<String, Action>{
	public Set<String> findAllByMember(String mid) throws SQLException;
}

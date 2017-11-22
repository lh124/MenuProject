package cn.mldn.util.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;


public interface IBaseDAO<K,V> {

	public boolean doCreate(V vo)throws SQLException;
	public boolean doUpdate(V vo)throws SQLException;
	public boolean doRemove(K id)throws SQLException;
	public boolean doRemove(Set<String> ids)throws SQLException;
	public V findById(K id)throws SQLException;
	public List<V> findAll()throws SQLException;
	public List<V> findAll(Long pagesize,Integer linesize)throws SQLException;
	public List<V> findSplit(String culum,String keyword,Long pagesize,Integer linesize) throws SQLException;
	public Long getCount()throws SQLException;
	public Long getCountSplit(String culum,String keyword) throws SQLException;
	
	
}

package cn.mldn.service.front;

import java.util.Map;

import cn.mldn.vo.Member;

public interface IMemberServiceFront {
	/**
	 * 实现用户的登录处理操作 ，该操作要执行如下几步 
	 * 1.调用IMemberDAO.findById()方法根据用户查询用户的信息
	 * 随后进行密码比对 ，如果密码正确则表示登录成功
	 * 
	 * @param member 包含用户登录名（mid）与密码（password） 两个重要的信息
	 * @return 返回的内容包含有如下数据
	 * key=flag value=登录成功或失败的信息
	 * key=name value=该用户的真实姓名
	 * key=lastdate value=最后一次登录的时间
	 * @throws Exception
	 */

	public Map<String,Object> login(Member member) throws Exception;
	
	
	public Map<String,Object> login(String mid) throws Exception;
}

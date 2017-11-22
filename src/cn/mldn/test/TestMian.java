package cn.mldn.test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import cn.mldn.dao.IActionDAO;
import cn.mldn.dao.IRoleDAO;
import cn.mldn.factory.Factory;
import cn.mldn.service.front.IMemberServiceFront;
import cn.mldn.util.enctype.PasswordUtil;
import cn.mldn.vo.Member;

public class TestMian {
	public static void main(String[] args) throws Exception {

		
		IMemberServiceFront service = Factory.getInstanece("member.service");
		IRoleDAO roledao=Factory.getInstanece("role.dao");
		IActionDAO actiondao=Factory.getInstanece("action.dao");
		Member member=new Member();
		member.setMid("mldn");
		member.setPassword(PasswordUtil.encoder("java"));
		Map<String,Object> map=service.login(member);
		map.put("role",roledao.findAllByMember(member.getMid()));
		map.put("action",actiondao.findAllByMember(member.getMid()));
		Set<String> role=(Set<String>) map.get("role");
		//Set<String> action=(Set<String>) map.get("role");
		Iterator<String> itro=role.iterator();
	//	Iterator<String> actions=role.iterator();
		
		while(itro.hasNext()) {
			String str=itro.next();
		//	String strs[]=action.next().split(":");
			System.out.println(str);
		}
//		System.out.println(map.get("role"));
//		System.out.println(map.get("action"));
		
	}
}

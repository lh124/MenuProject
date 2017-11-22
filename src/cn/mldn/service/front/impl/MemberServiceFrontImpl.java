package cn.mldn.service.front.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import cn.mldn.dao.IActionDAO;
import cn.mldn.dao.IMemberDAO;
import cn.mldn.dao.IMemberLogs;
import cn.mldn.dao.IRoleDAO;
import cn.mldn.exception.login.password.MemberExistesException;
import cn.mldn.exception.login.password.MemberLockedException;
import cn.mldn.exception.login.password.PasswordValidateException;
import cn.mldn.factory.Factory;
import cn.mldn.service.front.IMemberServiceFront;
import cn.mldn.util.MD5Code;
import cn.mldn.util.enctype.PasswordUtil;
import cn.mldn.vo.Member;
import cn.mldn.vo.Member_logs;

public class MemberServiceFrontImpl implements IMemberServiceFront{
	private static final int MAX_TRYCOUNT=3;
	private static final int MAX_UNLOCK_HOST=2;
	@Override
	public Map<String, Object> login(Member member) throws Exception {
		IMemberDAO dao=Factory.getInstanece("member.dao");
		IRoleDAO roledao=Factory.getInstanece("role.dao");
		IActionDAO actiondao=Factory.getInstanece("action.dao");
		IMemberLogs mlog=Factory.getInstanece("memberlog.dao");

		Member_logs member_logs=new Member_logs();
		boolean flag=false;//保存登录成功的标记
		Map<String,Object> map=new HashMap<>();
		Member selectmember=dao.findById(member.getMid());
		if(selectmember==null) {
			throw new MemberExistesException("该用户"+member.getMid()+"信息不存在");
		}

		if(selectmember.getLocked().equals(1)) {
			Long start=System.currentTimeMillis();//当前时间 
			Long end=selectmember.getLocktime().getTime();//被锁定的时间
			if(TimeUnit.HOURS.convert(start-end, TimeUnit.MILLISECONDS)<MAX_UNLOCK_HOST) {
				System.out.println(TimeUnit.HOURS.convert(end-start, TimeUnit.MILLISECONDS));
				throw new MemberLockedException("该用户"+member.getMid()+"被锁定,无法登录");
			}else {
				selectmember.setLocked(0);
				dao.doupdateLocked(selectmember);
				dao.doUpdateLockedTime(selectmember.getMid(), null);
				dao.doUpdateTrycount(selectmember.getMid(), 0);
			}
		}

		System.out.println(member.getPassword());
		System.out.println(selectmember.getPassword());
		if(new MD5Code().getMD5ofStr(member.getPassword()).equals(selectmember.getPassword())) {//登录成功


			flag=true;
			dao.doupdateLastdate(member.getMid(),new Date());

			map.put("name", selectmember.getName());//真实姓名
			map.put("lastdate",selectmember.getLastdate());//最后一次登录的日期时间
			map.put("role",roledao.findAllByMember(member.getMid()));
			map.put("action", actiondao.findAllByMember(member.getMid()));


			dao.doUpdateLockedTime(selectmember.getMid(), null);//日期设为null
			dao.doUpdateTrycount(selectmember.getMid(), 0);//尝试次数为零
			//登录日志
			member_logs.setMid(selectmember.getMid());//取得登录名称
			member_logs.setLogintime(selectmember.getLastdate());//取得前一次登录的时间
			mlog.doCreate(member_logs);


		}else {//用户密码错误
			int trycount=selectmember.getTrycount()+1;
			if(trycount>MAX_TRYCOUNT) {
				selectmember.setLocked(1);
				dao.doupdateLocked(selectmember);
				dao.doUpdateLockedTime(member.getMid(), new Date());

			}else {
				dao.doUpdateTrycount(member.getMid(), trycount);
			}
			throw new PasswordValidateException("密码错误");
		}
		map.put("flag", flag);
		return map;
	}


	public static void main(String[] args) throws Exception {

		//		IMemberServiceFront service = Factory.getInstanece("member.service");
		//		IRoleDAO roledao=Factory.getInstanece("role.dao");
		//		IActionDAO actiondao=Factory.getInstanece("action.dao");
		//		Member member=new Member();
		//		member.setMid("mldn");
		//		member.setPassword(PasswordUtil.encoder("java"));
		//		Map<String,Object> map=service.login(member);
		//		map.put("role",roledao.findAllByMember(member.getMid()));
		//		map.put("action",actiondao.findAllByMember(member.getMid()));
		//		Set<String> role=(Set<String>) map.get("role");
		//		//Set<String> action=(Set<String>) map.get("role");
		//		Iterator<String> itro=role.iterator();
		//	//	Iterator<String> actions=role.iterator();
		//		
		//		while(itro.hasNext()) {
		//			String str=itro.next();
		//		//	String strs[]=action.next().split(":");
		//			System.out.println(str);
		//		}
		////		System.out.println(map.get("role"));
		////		System.out.println(map.get("action"));


	}


	@Override
	public Map<String, Object> login(String mid) throws Exception {
		IMemberDAO dao=Factory.getInstanece("member.dao");
		IRoleDAO roledao=Factory.getInstanece("role.dao");
		IActionDAO actiondao=Factory.getInstanece("action.dao");
		IMemberLogs mlog=Factory.getInstanece("memberlog.dao");

		Member_logs member_logs=new Member_logs();
		Map<String,Object> map=new HashMap<>();
		Member selectmember=dao.findById(mid);
		if(selectmember==null) {
			throw new MemberExistesException("该用户"+mid+"信息不存在");
		}

		if(selectmember.getLocked().equals(1)) {

			throw new MemberLockedException("该用户"+mid+"被锁定");

		}
		dao.doupdateLastdate(mid,new Date());
		map.put("name", selectmember.getName());//真实姓名
		map.put("lastdate",selectmember.getLastdate());//最后一次登录的日期时间
		map.put("role",roledao.findAllByMember(mid));
		map.put("action", actiondao.findAllByMember(mid));

		dao.doUpdateLockedTime(selectmember.getMid(), null);//日期设为null
		dao.doUpdateTrycount(mid, 0);//尝试次数为零
		//登录日志
		member_logs.setMid(mid);//取得登录名称
		member_logs.setLogintime(selectmember.getLastdate());//取得前一次登录的时间
		mlog.doCreate(member_logs);


		return map;
	}

}

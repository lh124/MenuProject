package cn.mldn.exception.login.password;

import cn.mldn.exception.login.LoginException;

@SuppressWarnings("serial")
public class MemberLockedException extends LoginException {

	public MemberLockedException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}

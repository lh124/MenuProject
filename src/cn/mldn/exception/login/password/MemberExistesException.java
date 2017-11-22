package cn.mldn.exception.login.password;

import cn.mldn.exception.login.LoginException;

@SuppressWarnings("serial")
public class MemberExistesException  extends LoginException{

	public MemberExistesException(String msg) {
		super(msg);
	}

}

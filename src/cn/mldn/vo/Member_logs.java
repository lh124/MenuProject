package cn.mldn.vo;

import java.util.Date;

public class Member_logs {

	private int mlid;
	private String mid;
	private Date logintime;
	public int getMlid() {
		return mlid;
	}
	public void setMlid(int mlid) {
		this.mlid = mlid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public Date getLogintime() {
		return logintime;
	}
	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}
	
	@Override
	public String toString() {
		return "Member_logs [mlid=" + mlid + ", mid=" + mid + ", logintime=" + logintime + "]";
	}
}

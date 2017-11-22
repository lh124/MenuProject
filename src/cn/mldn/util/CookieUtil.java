package cn.mldn.util;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mldn.util.enctype.PasswordUtil;

public class CookieUtil {

	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public CookieUtil(HttpServletRequest request,HttpServletResponse response) {
		this.request=request;
		this.response=response;
		
	}
	
	public  void savaMid(String mid) {
		Cookie cookie=new Cookie("member",PasswordUtil.encoder(mid));
		cookie.setPath(this.request.getContextPath());//取得路径
		cookie.setMaxAge((int)TimeUnit.SECONDS.convert(1200000, TimeUnit.SECONDS));
		this.response.addCookie(cookie);
		
	}
	
	public String loadMid() {
		Cookie cookie[]=this.request.getCookies();
		if(cookie==null) {
			return null;
		}
		for(int i=0;i<cookie.length;i++) {
			if("member".equals(cookie[i].getName())) {
				return PasswordUtil.decoderString(cookie[i].getValue());//取得用户名
			}
		}
		return null;
		
	}
	
	
	public void cleaMid() {
		Cookie cookie=new Cookie("member","hello");
		cookie.setPath(this.request.getContextPath());//取得路径
		cookie.setMaxAge(0);
		this.response.addCookie(cookie);
	}
}

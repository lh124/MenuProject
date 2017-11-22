package cn.mldn.util.enctype;

import java.util.Base64;

import cn.mldn.util.MD5Code;

public class PasswordUtil {
	private static final int REPEAT_COUNT=3;
	private static final String SALT="mldnjava";
	
	private PasswordUtil() {
		
	}
	/**
	 * 密码加密处理
	 * @param pwd
	 * @return
	 */
//	public static String encoder(String pwd) {
//		byte date[]=SALT.getBytes();
//		for(int i=0;i<REPEAT_COUNT;i++) {
//			date=Base64.getEncoder().encode(date);
//			
//		}
//		String salPwd="{"+new String(date)+"}"+pwd;
//		for(int x=0;x<REPEAT_COUNT;x++) {
//			salPwd=new MD5Code().getMD5ofStr(salPwd);
//		}
//		return salPwd;
//		//return new String (Base64.getEncoder().encode(pwd.getBytes()));
//	}
	
	
	public static String encoder(String str) {
		byte date[]=str.getBytes();
		for(int i=0;i<REPEAT_COUNT;i++) {
			date=Base64.getEncoder().encode(date);
			
		}
		
		return new String (date);
		//return new String (Base64.getEncoder().encode(pwd.getBytes()));
	}
	
	/**
	 * 解密
	 * @param pwd
	 * @return
	 */
	public static String decoderString(String str) {
		byte date[]=str.getBytes();
		for(int i=0;i<REPEAT_COUNT;i++) {
			date=Base64.getDecoder().decode(date);
		}
	
		return new String(date);
	}
	public static void main(String[] args) {
		String str="hello";
		System.out.println(new MD5Code().getMD5ofStr(PasswordUtil.encoder(str)));
		
		String str1=PasswordUtil.encoder(str);
		String str2=PasswordUtil.decoderString(str1);
		System.out.println(str2);
	}

}

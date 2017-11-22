package cn.mldn.util;

import java.util.ResourceBundle;

public class MessageRes {

	ResourceBundle rs=null;


	public MessageRes(String baseName) {
		if(baseName.contains(".")) {
			this.rs=ResourceBundle.getBundle(baseName)	;	
		}else {
			this.rs=ResourceBundle.getBundle("cn.mldn.resources."+baseName);
		}
	}
	

	public String getMessage(String key) {
		try {
			return this.rs.getString(key);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
}

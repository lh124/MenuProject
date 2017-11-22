package cn.mldn.factory;

import cn.mldn.service.proxy.ServiceProxy;
import cn.mldn.util.MessageRes;

public class Factory {

	private static MessageRes DAO=new MessageRes("dao");
	private static MessageRes SERVICE=new MessageRes("service");
	
	private Factory() {}
	
	public static <T> T getInstanece(String name) {
		String str=name.substring(name.indexOf(".")+1);
		switch (str) {
		case "dao":
			return getDAOInstance(name);
		case "service":
			return getServiceInstance(name);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private static <T> T getServiceInstance(String name) {
		
		try {
			return (T) new ServiceProxy().bind(Class.forName(SERVICE.getMessage(name)).newInstance());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private static <T> T getDAOInstance(String name) {
		
		try {
			return (T) Class.forName(DAO.getMessage(name)).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}

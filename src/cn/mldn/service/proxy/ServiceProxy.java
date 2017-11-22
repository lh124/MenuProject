package cn.mldn.service.proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;

import cn.mldn.dbc.BaseConnection;

	public class ServiceProxy implements InvocationHandler {

		private Object target;

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

			if (this.checkTrancationMethod(method.getName())) {

				BaseConnection.getConnection().setAutoCommit(false);

			}

			Object backResult = null;
			Exception throwException = null;
			try {
				backResult = method.invoke(this.target, args);
				if (this.checkTrancationMethod(method.getName())) {
					BaseConnection.getConnection().commit();
				}

			} catch (Exception e) {
				throwException = e;
				if (e instanceof SQLException) {

					System.out.println("---------inner----------------");

					if (this.checkTrancationMethod(method.getName())) {

						BaseConnection.getConnection().rollback();
					}

				}

				System.out.println("outer");
				throw e;

			} finally {
				
				if (!(throwException instanceof SQLException)) {
					if (this.checkTrancationMethod(method.getName())) {
						BaseConnection.getConnection().commit();
					}
				}
				BaseConnection.close();
			}

			return backResult;
		}

		public Object bind(Object target) {

			this.target = target;
			return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(),
					this);
		}

		private boolean checkTrancationMethod(String methodName) {

			return methodName.startsWith("login") || methodName.startsWith("add") || methodName.startsWith("edit")
					|| methodName.startsWith("delete");
		}

	}
	
	


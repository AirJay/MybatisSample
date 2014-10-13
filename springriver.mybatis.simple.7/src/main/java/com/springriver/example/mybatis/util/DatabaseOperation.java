package com.springriver.example.mybatis.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.ibatis.session.SqlSession;


/**
 * A boiler class to simplify and standarize the mybatis mapper interface execution. 
 * This object takes care of the open/close session, and commit for you.
 * 
 * Usage:
 * 
 *		DatabaseOperation op = new DatabaseOperation() {			
 *			@Override
 *			public Object performTask() {				
 *				define your db operation task
 *			}
 *		};		
 *		return op.invoke();
 * 
 * 
 * @author Hui Wang
 * Spring River Web Development
 *
 */


public abstract class DatabaseOperation {
	private SqlSession session;
	private boolean commitNeeded = false;
	
	public DatabaseOperation(boolean commit){
		commitNeeded = commit;
	}
	
	public DatabaseOperation(){
		this(false);
	}
	
	
	public SqlSession getSession() {
		return session;
	}

	public void setSession(SqlSession session) {
		this.session = session;
	}

	public Object invoke() {

		Object retObject = null;

		try {
			if (session == null)
				session = ConnectionFactory.getSqlSessionFactory()
						.openSession();

			retObject = performAction();

			if (commitNeeded)
				session.commit();
		} finally {

			session.close();
		}

		return retObject;
	}

	public abstract Object performAction();

	public Object executeMapperFunction(Class<?> mapperClass,String action, 
			Object args[])  {

		Class<?> c = mapperClass;
		Method behave = null;
		Object mapper = session.getMapper(c);
		Class<?> paramTypes[] = new Class[args.length];
		Object retObj = null;
		for (int i = 0; i < args.length; i++)
			paramTypes[i] = args[i].getClass();

		
		try {
			behave = c.getMethod(action, paramTypes);
			retObj = behave.invoke(mapper, args);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retObj;
	}
	
	
	public Object executeMapperFunction(Class<?> mapperClass, String operation, Object obj){
		return executeMapperFunction(mapperClass, operation, new Object[]{obj});
	}
	
	public Object executeMapperFunction( Class<?> mapperClass, String operation){
		return executeMapperFunction(mapperClass, operation, new Object[]{});
	}	
}

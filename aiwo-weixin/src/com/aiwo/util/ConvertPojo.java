package com.aiwo.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>
 * 
 * ��װ�����ݵ�ʵ����
 * 
 * 
 * </p>
 * 
 * @author Liuxp
 * @version 1.0
 * @modify version data updata-author updatecontent
 * 
 */
public class ConvertPojo {

	/**
	 * 
	 * 
	 * <p>
	 * �÷����ǽ�����Ľ����װ��ʵ���С�
	 * 
	 * </p>
	 * 
	 * 
	 * @param rs
	 * 
	 *            ���
	 *            
	 * @param object
	 * 
	 * 
	 *            �����ʵ��
	 *            
	 * @return 
	 * 
	 *              ����ʵ�����
	 *              
	 *              
	 * @throws Exception
	 *           
	 *             
	 *             �쳣
	 */
	public static <T> List<T> getBean(ResultSet rs, T object) throws Exception 
	{
		Class<?> classType = object.getClass();
		ArrayList<T> objList = new ArrayList<T>();
		Field[] fields = classType.getDeclaredFields();// �õ������е��ֶ�
		
		while (rs.next()) 
		{
			// ÿ��ѭ��ʱ������ʵ��һ���봫�����Ķ�������һ��Ķ���
			
			
			T objectCopy = getObject(rs,classType,object,fields) ;
			
			objList.add(objectCopy);

		}

		return objList;
	}
	
	
	/**
	 * 
	 * 
	 * ��ȡ��ֵ���ʵ��
	 * 
	 * 
	 * @param rs	
	 * 
	 * 			@see com.java.util.ConverPojo.getValue()#
	 * 			
	 * @param clazz
	 * 			
	 * 			
	 * 			ʵ���Ӧ��class
	 * 				
	 * @param object
	 * 			
	 * 			 û�и�ֵ��ʵ�����
	 * 
	 * @param fields
	 * 
	 * 			������������ʵ��
	 * 
	 * @return
	 * 			���ض���ʵ��
	 * 
	 * @throws InstantiationException
	 * 
	 * @throws IllegalAccessException
	 * 
	 * @throws SQLException
	 * 
	 * @throws SecurityException
	 * 
	 * @throws NoSuchMethodException
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @throws InvocationTargetException
	 * 
	 */
	@SuppressWarnings("unchecked")
	private static <T> T getObject(ResultSet rs,Class<?> clazz,T object,Field[] fields) throws InstantiationException, IllegalAccessException, SQLException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException
	{
		
		T objectInstance  = (T) clazz.newInstance() ;
		
		for(int i = 0 ; i < fields.length ; i++)
		{
			Field filed=fields[i] ;
			String fieldName = filed.getName() ;
			Object value = getValue(rs,filed,fieldName) ;
			
			String setMethodName = "set" + fieldName.replaceFirst(fieldName.substring(1), fieldName.substring(1).toUpperCase());
			
			Method setMethod = clazz.getDeclaredMethod(setMethodName, new Class[]{filed.getType()}) ;
			
			setMethod.invoke(objectInstance, new Object[]{value}) ;
		}
	
		return objectInstance;
		
	}

	
	
	/**
	 * 
	 * ��ݲ��õ�������ͻ�ȡ�������͵�ֵ 
	 * 
	 * 
	 * 
	 * @param rs
	 * 			
	 * 			��װ���			 
	 * 
	 * @param filed
	 * 
	 * 			�ֶ�
	 * 
	 * @param fieldName
	 * 				�ֶ����
	 * @return
	 * 			 ������Ӧ���͵�ֵ
	 * 
	 * @throws SQLException
	 * 
	 * 			�쳣
	 */
	private static Object getValue(ResultSet rs,Field filed, String fieldName) throws SQLException 
	{
		
		
		Class<?> filedType = filed.getType() ;
		
		
		if(filedType.equals(java.lang.String.class))
		{
			return rs.getString(fieldName);
		}
		if(filedType.equals(int.class))
		{
			return rs.getInt(fieldName) ;
		}
		
		if(filedType.equals(Float.class))
		{
			return rs.getFloat(fieldName) ;
		}
		
		if(filedType.equals(java.sql.Date.class))
		{
			return rs.getDate(fieldName) ;
		}
		
		if(filedType.equals(byte[].class))
		{
			return rs.getBytes(fieldName) ;
		}
		
		if(filedType.equals(java.lang.Double.class))
		{
			return rs.getDouble(fieldName) ;
		}	
		
		if(filedType.equals(java.math.BigDecimal.class))
		{
			return rs.getDouble(fieldName) ;
		}
		
		return null;
	}

}

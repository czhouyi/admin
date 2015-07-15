package com.chinadaas.gsinfo.admin.util;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * projectName: gsinfo30-admin<br>
 * desc: TODO<br>
 * date: 2014年7月1日 上午10:21:55<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class PropertyUtil {
	
	public static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 使用反射的方法设置对象的实例变量
	 * desc: 使用反射的方法设置对象的实例变量<br>
	 * date: 2014年7月15日 上午11:28:49<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param entity
	 * @param property
	 * @param value
	 * @throws NoSuchFieldException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws ParseException 
	 */
	public static void set(Object entity, String property, Object value) 
			throws IllegalArgumentException, SecurityException, 
			IllegalAccessException, InvocationTargetException, 
			NoSuchMethodException, NoSuchFieldException, ParseException {
		Class clazz = entity.getClass();
		Class type = clazz.getDeclaredField(property).getType();
		if(type == Integer.class) {
			value = Integer.valueOf(value.toString());
		} else if (type == Double.class) {
			value = Double.valueOf(value.toString());
		} else if (type == Date.class) {
			value = SDF.parse(value.toString());
		}
		clazz.getMethod(setterMethod(property), clazz.getDeclaredField(property).getType()).invoke(entity, value);
	}
	
	/**
	 * 使用反射的方法获取对象的实例变量的值
	 * desc: 使用反射的方法获取对象的实例变量的值<br>
	 * date: 2014年7月15日 上午11:39:41<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param entity
	 * @param property
	 * @param value
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws NoSuchFieldException
	 */
	public static Object get(Object entity, String property) 
			throws IllegalArgumentException, SecurityException, 
			IllegalAccessException, InvocationTargetException, 
			NoSuchMethodException, NoSuchFieldException {
		Class clazz = entity.getClass();
		boolean isFieldBoolean = clazz.getDeclaredField(property).getType() == Boolean.class;
		return clazz.getMethod(getterMethod(property, isFieldBoolean)).invoke(entity);
	}
	
	/**
	 * 获取对象的setter方法名
	 * desc: 获取对象的setter方法名<br>
	 * date: 2014年7月15日 上午11:40:35<br>
	 * @author 开发者真实姓名[Andy]
	 * @param property
	 * @return
	 */
	public static String setterMethod(String property) {
		return "set" + StringUtil.capitalFirst(property);
	}
	
	/**
	 * 获取对象的getter方法名
	 * desc: 获取对象的getter方法名<br>
	 * date: 2014年7月15日 上午11:41:03<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param property
	 * @param isBoolean
	 * @return
	 */
	public static String getterMethod(String property, boolean isBoolean) {
		return (isBoolean ? "is" : "get") + StringUtil.capitalFirst(property);
	}

}


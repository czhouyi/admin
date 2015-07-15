package com.chinadaas.gsinfo.admin.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: 公用工具类<br>
 * date: 2014年9月1日 下午2:50:02<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class CommonUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	
	/**
	 * desc: 将对象列表转化为map列表<br>
	 * date: 2014年8月26日 下午1:38:25<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param list
	 * @return
	 */
	public static List<Map<String, String>> toMapList(List<Object> list) {
		List<Map<String, String>> maplist = new ArrayList<Map<String,String>>();
		for (Object entity: list) {
			maplist.add(toMap(entity));
		}
		
		return maplist;
	}
	
	/**
	 * desc: 将对象转化成map<br>
	 * date: 2014年8月26日 下午1:39:07<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param entity
	 * @return
	 */
	public static Map<String, String> toMap(Object entity) {
		Map<String, String> map = new HashMap<String, String>();
		Field[] fields = entity.getClass().getDeclaredFields();
		try {
			for(Field field: fields) {
				boolean isBoolean = field.getType() == Boolean.class;
				Object value = entity.getClass().getMethod(PropertyUtil.getterMethod(
						field.getName(), isBoolean), new Class[]{}).invoke(entity, new Object[]{});
				if (value != null && value.toString().length() > 0) {
					map.put(field.getName(), StringUtil.getString(value));
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return map;
	}
	
}

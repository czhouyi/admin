package com.chinadaas.gsinfo.admin.util;

import java.util.ResourceBundle;

/**
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: 资源管理类<br>
 * date: 2014年9月1日 下午2:57:02<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class Resource {

	/**
	 * desc: 获取资源对象<br>
	 * date: 2014年9月1日 下午2:57:18<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param baseName
	 * @return
	 */
	public static ResourceBundle getResourceBundle(String baseName){
		return ResourceBundle.getBundle(baseName);
	}
	
	/**
	 * desc: 获取资源对象中的值<br>
	 * date: 2014年9月1日 下午2:57:22<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param baseName
	 * @param key
	 * @return
	 */
	public static String getString(String baseName, String key){
		return getResourceBundle(baseName).getString(key);
	}
}

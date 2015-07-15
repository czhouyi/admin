package com.chinadaas.gsinfo.admin.util;

/**
 * 常量类
 * projectName: gsinfo30-admin<br>
 * desc: 常量类<br>
 * date: 2014年7月8日 下午3:52:35<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class Constants {
	
	// Action相关的常量
	public static final String VALIDATE_CODE = "VALIDATE_CODE"; // 校验码在session中存放的key
	public static final String PARAM_LOGINUSERID = "login_userId";	// 登录名在request参数中的key，也是session中登录名的key
	public static final String PARAM_LOGINUSER = "login_userName";	// 登录名在request参数中的key，也是session中登录名的key
	public static final String PARAM_LOGINPWD = "login_password";	// 登录密码在request参数中的key
	public static final String PARAM_VCODE = "vCode";			// 输入验证码在request参数中的key
	public static final String PARAM_LOGINTIME = "login_time";	// session中登录时间的key
	public static final String PARAM_START = "start";			// request参数中获取记录开始索引
	public static final String PARAM_LIMIT = "limit";		// request参数中获取记录条数
	
	
	public static final String JSON_SUCCESS = "success";		// 返回前台json中的一个key：true表示action处理成功，false表示action处理失败
	public static final String JSON_MSG = "msg";				// 返回前台json中的一个key：OK， ERROR
	public static final String JSON_CODE = "code";				// 返回前台json中的一个key： 记录出错原因
	public static final String JSON_OK = "OK";					// json中msg的一个键值： 表示成功
	public static final String JSON_ERROR = "ERROR";			// json中msg的一个键值：表示出错
	public static final String JSON_CODE_200 = "200";			// json中返回200代码
	public static final String JSON_RESULT = "result";			// 返回前台json中的一个key：存放查找的结果集
	public static final String JSON_TOTALCOUNT = "totalCount";	// 返回前台json中的一个key：存放查找的结果集

}


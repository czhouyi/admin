package com.chinadaas.gsinfo.admin.exception;

import com.chinadaas.gsinfo.admin.util.Resource;

/**
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: 登录异常类<br>
 * date: 2014年9月1日 下午2:55:00<br>
 * @author 开发者真实姓名[裔传洲]
 */
@Deprecated
public class LogException extends Exception{

	public static final String USER_NOT_EXIST = "usernotexist";
	public static final String USER_ACTIVATED_ALREADY = "useractivatedalready";
	public static final String PASSWORD_NOT_CORRECT = "passwordnotcorrect";
	public static final String USER_NOT_ACTIVE = "usernotactive";
	
	
	public LogException(String exType){
		super(Resource.getString("com.andy.nicefi.exception.LogException", exType));
	}
}

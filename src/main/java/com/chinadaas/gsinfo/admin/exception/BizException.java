package com.chinadaas.gsinfo.admin.exception;

/**
 * 业务异常类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年8月15日 下午2:18:06<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class BizException extends Exception {
	
	public static final String DUPLICATION = "1";
	public static final String NOT_EXIST = "2";
	
	/**
	 * 默认构造函数
	 * @param message
	 */
	public BizException(String message) {
		super(message);
	}

}

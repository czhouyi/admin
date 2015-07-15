package com.chinadaas.gsinfo.admin.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinadaas.gsinfo.admin.util.Constants;
import com.chinadaas.gsinfo.admin.util.StringUtil;

/**
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: 首页Action类<br>
 * date: 2014年9月1日 下午2:55:31<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class IndexAction extends BaseAction{
	
	private static final long serialVersionUID = -5553713448845206516L;
	
	private static final Logger logger = LoggerFactory.getLogger(IndexAction.class);
	
	public String execute(){
		return SUCCESS;
	}
	
	/**
	 * desc: 访问到首页/home<br>
	 * date: 2014年8月26日 下午2:08:41<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public String index() {
		String loginUser = (String) getSession().getAttribute(Constants.PARAM_LOGINUSER);
		if (StringUtil.isNull(loginUser)) {
			logger.info("当前没有用户登录");
			return ERROR;
		} else {
			return SUCCESS;
		}
	}

}

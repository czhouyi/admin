package com.chinadaas.gsinfo.admin.interceptor;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.action.BaseAction;
import com.chinadaas.gsinfo.admin.service.UserService;
import com.chinadaas.gsinfo.admin.util.Constants;
import com.chinadaas.gsinfo.admin.util.StringUtil;
import com.chinadaas.gsinfo.admin.vo.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 日志拦截器
 * projectName: gsinfo30-admin<br>
 * desc: 日志拦截器<br>
 * date: 2014年7月9日 上午10:14:00<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class LoggerInterceptor extends MethodFilterInterceptor{
	
	private static final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);
	
	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		BaseAction baseAction = (BaseAction) invocation.getAction();
		HttpSession session = baseAction.getSession();
		
		String url = baseAction.getRequest().getRequestURI();
		String loginUser = StringUtil.getString(session.getAttribute(Constants.PARAM_LOGINUSER));
		String loginUserId = StringUtil.getString(session.getAttribute(Constants.PARAM_LOGINUSERID));
		
		if(StringUtil.isNull(loginUserId) && !StringUtil.isNull(loginUser)) {
			User user = userService.get("from User where user_name='"+loginUser+"'");
			loginUserId = user.getId();
		}
		if(StringUtil.isNull(loginUserId)) {
			logger.info(String.format("user->null, url->%s", url));
		} else {
			logger.info(String.format("user->[%s:%s], url->%s", loginUserId, loginUser, url));
		}
		
		return invocation.invoke();
	}

}


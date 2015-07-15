package com.chinadaas.gsinfo.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.chinadaas.gsinfo.admin.action.BaseAction;
import com.chinadaas.gsinfo.admin.util.Constants;
import com.chinadaas.gsinfo.admin.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 登录拦截器
 * projectName: gsinfo30-admin<br>
 * desc: TODO<br>
 * date: 2014年7月9日 上午10:14:00<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class LoginInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		BaseAction baseAction = (BaseAction) invocation.getAction();
		HttpSession session = baseAction.getSession();

		String loginUser = StringUtil.getString(session.getAttribute(Constants.PARAM_LOGINUSER));
		String loginTime = StringUtil.getString(session.getAttribute(Constants.PARAM_LOGINTIME));
		if (StringUtil.isNull(loginUser) && StringUtil.isNull(loginTime)) {
			return Action.LOGIN;
		}
		
		HttpServletRequest request = baseAction.getRequest();
		
		/** 拒绝为GET请求返回数据 */
		String method = request.getMethod();
		if("GET".equals(method)) {
			return Action.NONE;
		}
		
		/** 拒绝为低端爬虫的请求返回数据 */
		String userAgent = request.getHeader("user-agent");
		if(StringUtil.isNull(userAgent)) {
			return Action.NONE;
		}
		
		return invocation.invoke();
	}

}


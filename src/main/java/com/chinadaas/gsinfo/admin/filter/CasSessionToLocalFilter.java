package com.chinadaas.gsinfo.admin.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.service.UserService;
import com.chinadaas.gsinfo.admin.util.Constants;
import com.chinadaas.gsinfo.admin.util.StringUtil;
import com.chinadaas.gsinfo.admin.vo.User;

/**
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: 将cas的session同步到本系统<br>
 * date: 2014年11月12日 下午2:58:03<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class CasSessionToLocalFilter implements Filter{
	
	private static Logger logger = Logger.getLogger(CasSessionToLocalFilter.class);

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		// _const_cas_assertion_ 是CAS存放登录用户名的session标志
		Object object = session.getAttribute(AuthenticationFilter.CONST_CAS_ASSERTION);
		if (object != null) {
			Assertion assertion = (Assertion) object;
			String loginName = assertion.getPrincipal().getName();
			if (!StringUtil.isNull(loginName)) {
				session.setAttribute(Constants.PARAM_LOGINUSER, loginName);
				session.setAttribute(Constants.PARAM_LOGINTIME, new Date().getTime());
			}
		}
		chain.doFilter(req, res);
	}

	public void init(FilterConfig config) throws ServletException {
		
	}

}

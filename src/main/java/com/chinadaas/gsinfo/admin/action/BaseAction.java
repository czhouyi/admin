package com.chinadaas.gsinfo.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinadaas.gsinfo.admin.util.Constants;
import com.chinadaas.gsinfo.admin.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * action基类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: 实现构建json逻辑<br>
 * date: 2014年7月8日 下午5:42:25<br>
 * @author 开发者真实姓名[裔传洲]
 */
public abstract class BaseAction extends ActionSupport {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseAction.class);
	
	/**
	 * desc: 请求返回对象<br>
	 * date: 2014年9月1日 下午2:59:26<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
	/**
	 * desc: 请求对象<br>
	 * date: 2014年9月1日 下午2:59:47<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	/**
	 * 返回处理结果到前台 - json格式
	 * desc: 返回简单json<br>
	 * date: 2014年7月8日 下午5:42:51<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param success
	 * @param msg
	 * @param code
	 */
	protected void response(boolean success, String msg, String code) {
		HttpServletResponse response = getResponse();
		response.setContentType("text/html; charset=UTF-8;");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			
			JSONObject json = new JSONObject();
			json.put(Constants.JSON_SUCCESS, success);
			json.put(Constants.JSON_MSG, msg);
			json.put(Constants.JSON_CODE, code);
			
			out.write(json.toString());
			out.flush();
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			if(out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * 返回处理结果到前台 - json格式
	 * desc: response的重载方法，增加map参数<br>
	 * date: 2014年7月8日 下午5:42:51<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param success
	 * @param msg
	 * @param code
	 * @param map
	 */
	protected void response(boolean success, String msg, String code, Map<String, Object> map) {
		HttpServletResponse response = getResponse();
		response.setContentType("text/html; charset=UTF-8;");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			
			JSONObject json = new JSONObject();
			json.put(Constants.JSON_SUCCESS, success);
			json.put(Constants.JSON_MSG, msg);
			json.put(Constants.JSON_CODE, code);
			json.putAll(map);
			
			out.write(json.toString());
			out.flush();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if(out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * desc: 获取登录用户ID<br>
	 * date: 2014年9月1日 下午3:00:04<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public String getLoginUser() {
		return StringUtil.getString(getSession().getAttribute(Constants.PARAM_LOGINUSERID));
	}
	
	/**
	 * desc: 获取Session<br>
	 * date: 2014年9月1日 下午3:00:35<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	
}

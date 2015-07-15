package com.chinadaas.gsinfo.admin.handler;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: 网络统一入口类<br>
 * date: 2014年9月1日 下午2:55:57<br>
 * @author 开发者真实姓名[裔传洲]
 */
@Controller
@RequestMapping("/gateway/{pkg}/{handler}/{method}")
public class Gateway {

	@RequestMapping
	public void handle(@PathVariable String pkg, @PathVariable String handler,
			@PathVariable String method, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String className = "com.chinadaas.gsinfo.admin.handler." + pkg + "." + handler
					+ "Handler";
			Class<?> c = Class.forName(className);
			Constructor<?> constructor = c.getConstructor(
					HttpServletRequest.class, HttpServletResponse.class);
			Method m = c.getMethod(method);
			m.invoke(constructor.newInstance(request, response));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

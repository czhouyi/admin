package com.chinadaas.gsinfo.admin.action;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.service.IService;
import com.chinadaas.gsinfo.admin.service.UserService;
import com.chinadaas.gsinfo.admin.util.Enum.UserState;
import com.chinadaas.gsinfo.admin.vo.BaseEntity;
import com.chinadaas.gsinfo.admin.vo.User;

/**
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: 用户Action类<br>
 * date: 2014年9月1日 下午2:53:14<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class UserAction extends BizBaseAction<User> {
	/**
	 * 注入用户Service
	 * @see src/main/webapp/WEB-INF/applicationContext.xml
	 */
	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public IService getBizService() {
		return userService;
	}
	
	@Override
	protected BaseEntity createNewData() throws Exception {
		User user = (User) super.createNewData();
		user.setCreate_time(new Date());
		user.setCreator(getLoginUser());
		user.setState(UserState.ENABLE);
		user.setIs_new_reg("0");
		return user;
	}
	
}

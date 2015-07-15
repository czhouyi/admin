package com.chinadaas.gsinfo.admin.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.service.IService;
import com.chinadaas.gsinfo.admin.service.RoleService;
import com.chinadaas.gsinfo.admin.vo.Role;

/**
 * Role action
 * projectName: gsinfo30-admin<br>
 * desc: TODO<br>
 * date: 2014年7月8日 下午6:00:45<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class RoleAction extends BizBaseAction<Role> {
	
	@Autowired
	private RoleService roleService;

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	public IService getBizService() {
		return roleService;
	}

}


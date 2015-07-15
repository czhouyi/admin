package com.chinadaas.gsinfo.admin.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.service.IService;
import com.chinadaas.gsinfo.admin.service.MenuService;
import com.chinadaas.gsinfo.admin.vo.Menu;

/**
 * Menu action
 * projectName: gsinfo30-admin<br>
 * desc: TODO<br>
 * date: 2014年7月8日 下午6:00:45<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class MenuAction extends BizBaseAction<Menu> {
	
	@Autowired
	private MenuService menuService;
	
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	@Override
	public IService getBizService() {
		return menuService;
	}

}


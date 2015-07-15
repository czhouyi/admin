package com.chinadaas.gsinfo.admin.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.service.GroupCategoryService;
import com.chinadaas.gsinfo.admin.service.IService;
import com.chinadaas.gsinfo.admin.vo.GroupCategory;

/**
 * GroupCategory action
 * projectName: gsinfo30-admin<br>
 * desc: TODO<br>
 * date: 2014年7月8日 下午6:00:45<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class GroupCategoryAction extends BizBaseAction<GroupCategory> {
	
	@Autowired
	private GroupCategoryService groupCategoryService;
	
	public void setGroupCategoryService(GroupCategoryService groupCategoryService) {
		this.groupCategoryService = groupCategoryService;
	}

	@Override
	public IService getBizService() {
		return groupCategoryService;
	}

}


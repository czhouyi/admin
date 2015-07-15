package com.chinadaas.gsinfo.admin.action;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.service.IService;
import com.chinadaas.gsinfo.admin.service.OrgService;
import com.chinadaas.gsinfo.admin.util.Enum.OrgState;
import com.chinadaas.gsinfo.admin.vo.BaseEntity;
import com.chinadaas.gsinfo.admin.vo.Org;

/**
 * Org action
 * projectName: gsinfo30-admin<br>
 * desc: 机构Action<br>
 * date: 2014年7月8日 下午6:00:45<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class OrgAction extends BizBaseAction<Org> {
	/**
	 * 注入机构Service
	 * @see src/main/webapp/WEB-INF/applicationContext.xml
	 */
	@Autowired
	private OrgService orgService;

	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}
	
	@Override
	public IService getBizService() {
		return orgService;
	}

	@Override
	protected BaseEntity createNewData() throws Exception {
		Org org = (Org) super.createNewData();
		org.setCreate_time(new Date());
		org.setId_user_create(getLoginUser());
		org.setState(OrgState.ENABLE);
		return org;
	}
	
}


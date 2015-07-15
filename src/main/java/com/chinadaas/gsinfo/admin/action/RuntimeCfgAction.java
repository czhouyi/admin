package com.chinadaas.gsinfo.admin.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.service.IService;
import com.chinadaas.gsinfo.admin.service.RuntimeCfgService;
import com.chinadaas.gsinfo.admin.vo.RuntimeCfg;

/**
 * RuntimeCfg action
 * projectName: gsinfo30-admin<br>
 * desc: TODO<br>
 * date: 2014年7月8日 下午6:00:45<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class RuntimeCfgAction extends BizBaseAction<RuntimeCfg> {
	
	@Autowired
	private RuntimeCfgService runtimeCfgService;

	public void setRuntimeCfgService(RuntimeCfgService runtimeCfgService) {
		this.runtimeCfgService = runtimeCfgService;
	}

	@Override
	public IService getBizService() {
		return runtimeCfgService;
	}

}


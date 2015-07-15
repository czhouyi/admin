package com.chinadaas.gsinfo.admin.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.service.ContractService;
import com.chinadaas.gsinfo.admin.service.IService;
import com.chinadaas.gsinfo.admin.vo.Contract;

/**
 * Contract action
 * projectName: gsinfo30-admin<br>
 * desc: TODO<br>
 * date: 2014年7月8日 下午6:00:45<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class ContractAction extends BizBaseAction<Contract> {
	/**
	 * 注入合同Service
	 * @see src/main/webapp/WEB-INF/applicationContext.xml
	 */
	@Autowired
	private ContractService contractService;

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	@Override
	public IService getBizService() {
		return contractService;
	}

}


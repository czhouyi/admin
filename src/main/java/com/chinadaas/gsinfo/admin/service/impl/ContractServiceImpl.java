package com.chinadaas.gsinfo.admin.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.dao.ContractDAO;
import com.chinadaas.gsinfo.admin.dao.IDAO;
import com.chinadaas.gsinfo.admin.service.BaseService;
import com.chinadaas.gsinfo.admin.service.ContractService;
import com.chinadaas.gsinfo.admin.vo.Contract;


/**
 * Contract service实现类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年7月8日 下午3:05:37<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class ContractServiceImpl extends BaseService<Contract> implements ContractService{
	
	private static final Logger logger = LoggerFactory.getLogger(ContractServiceImpl.class);
	
	@Autowired
	private ContractDAO contractDAO;

	public void setContractDAO(ContractDAO contractDAO) {
		this.contractDAO = contractDAO;
	}

	@Override
	protected IDAO<Contract> getDao() {
		return this.contractDAO;
	}

	@Override
	protected String getListSQL(Map param) {
		return null;
	}

	@Override
	protected String getSelectSQL(Map param) {
		return null;
	}

}

package com.chinadaas.gsinfo.admin.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.dao.ContractDAO;
import com.chinadaas.gsinfo.admin.dao.IDAO;
import com.chinadaas.gsinfo.admin.dao.CustomerDAO;
import com.chinadaas.gsinfo.admin.dao.OrgDAO;
import com.chinadaas.gsinfo.admin.exception.BizException;
import com.chinadaas.gsinfo.admin.service.BaseService;
import com.chinadaas.gsinfo.admin.service.CustomerService;
import com.chinadaas.gsinfo.admin.util.Constants;
import com.chinadaas.gsinfo.admin.util.Enum;
import com.chinadaas.gsinfo.admin.util.StringUtil;
import com.chinadaas.gsinfo.admin.util.Enum.OrgState;
import com.chinadaas.gsinfo.admin.vo.Contract;
import com.chinadaas.gsinfo.admin.vo.Customer;
import com.chinadaas.gsinfo.admin.vo.Org;

/**
 * customer service实现类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年7月8日 下午3:05:37<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class CustomerServiceImpl extends BaseService<Customer> implements CustomerService{
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private OrgDAO orgDAO;
	
	@Autowired
	private ContractDAO contractDAO;
	
	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	public void setOrgDAO(OrgDAO orgDAO) {
		this.orgDAO = orgDAO;
	}

	public void setContractDAO(ContractDAO contractDAO) {
		this.contractDAO = contractDAO;
	}

	@Override
	protected IDAO<Customer> getDao() {
		return customerDAO;
	}
	
	@Override
	protected void checkBeforeSave(Customer instance, Map param) throws BizException {
		super.checkBeforeSave(instance, param);
		if (StringUtil.isNull(instance.getId())) {
			String name = instance.getFull_name();
			if (customerDAO.exist("from Customer where full_name = ?", name)) {
				throw new BizException(String.format("客户[fullName=%s]已存在", name));
			}
		}
	}
	
	@Override
	protected void doAfterSave(String id, Map param) throws BizException {
		super.doAfterSave(id, param);
		
		Customer customer = getById(id);
		
		String contractType = StringUtil.getParam(param, "CONTRACT_TYPE");
		String effectDate = StringUtil.getParam(param, "EFFECT_DATE");
		String loseDate = StringUtil.getParam(param, "LOSE_DATE");
		String loginUser = StringUtil.getParam(param, Constants.PARAM_LOGINUSER);
		
		// 同步新增合同
		Contract contract = new Contract();
		contract.setContract_name("工商基本信息查询-" + customer.getFull_name());
		contract.setContract_type(contractType);
		contract.setCreate_time(new Date());
		contract.setId_user_create(loginUser);
		contract.setId_customer(id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(!StringUtil.isNull(effectDate)) {
				contract.setEffect_date(sdf.parse(effectDate));
			}
			if(!StringUtil.isNull(loseDate)) {
				contract.setLose_date(sdf.parse(loseDate));
			}
		} catch (ParseException e) {
			throw new BizException(e.getMessage());
		}
		contractDAO.save(contract);
		
		// 同步新增默认机构
		Org org = new Org();
		org.setBrief_name("未分配");
		org.setFull_name(customer.getFull_name() + "-未分配");
		org.setOrg_type(Enum.OrgType.CUSTOMER);
		org.setCreate_time(new Date());
		org.setId_user_create(loginUser);
		org.setId_customer(id);
		org.setState(OrgState.ENABLE);
		orgDAO.save(org);
	}
	
	@Override
	protected void doAfterUpdate(Customer instance, Map param) throws BizException {
		super.doAfterUpdate(instance, param);
		
		String contractType = StringUtil.getParam(param, "CONTRACT_TYPE");
		String effectDate = StringUtil.getParam(param, "EFFECT_DATE");
		String loseDate = StringUtil.getParam(param, "LOSE_DATE");
		
		String sql = " update t_contract set ";
		if(!StringUtil.isNull(effectDate)) {
			sql += " effect_date = to_date('"+effectDate+"','yyyy-mm-dd'), ";
		} else {
			sql += " effect_date = null,";
		}
		if(!StringUtil.isNull(loseDate)) {
			sql += " lose_date = to_date('"+loseDate+"','yyyy-mm-dd'), ";
		} else {
			sql += " lose_date = null,";
		}
		
		if(!StringUtil.isNull(contractType)) {
			sql += " contract_type = '"+contractType+"' ";
		} else {
			sql += " contract_type = '' ";
		}
		sql += " where id_customer = '"+instance.getId()+"'";
		
		execSQLUpdate(sql);
	}

	@Override
	protected String getListSQL(Map param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append(" C.ID, ");														// 客户ID
		sql.append(" C.FULL_NAME, ");												// 客户名称
		sql.append(" C.STATE, ");													// 客户状态
		sql.append(" C.IP, ");														// 信任IP
		sql.append(" TO_CHAR(C.CREATE_TIME,'YYYY-MM-DD HH:MI:SS') CREATE_TIME, ");	// 创建时间
		sql.append(" CT.CONTRACT_TYPE, ");											// 合同类型
		sql.append(" TO_CHAR(CT.EFFECT_DATE,'YYYY-MM-DD HH:MI:SS') EFFECT_DATE, ");	// 起始日期
		sql.append(" TO_CHAR(CT.LOSE_DATE,'YYYY-MM-DD HH:MI:SS') LOSE_DATE ");		// 截止日期
		sql.append(" FROM T_CUSTOMER C ");
		sql.append(" LEFT JOIN T_CONTRACT CT ON CT.ID_CUSTOMER=C.ID ");
		sql.append(" WHERE 1=1 ");
		
		String customerName = StringUtil.getParam(param, "customerName");
		if(customerName != null && customerName.length() > 0) {
			sql.append(" AND C.FULL_NAME LIKE '%").append(customerName).append("%' ");
		}
		
		return sql.toString();
	}

	@Override
	protected String getSelectSQL(Map param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append(" C.ID, ");										// 客户ID
		sql.append(" C.FULL_NAME ");								// 客户名称
		sql.append(" FROM T_CUSTOMER C ");
		return sql.toString();
	}

}

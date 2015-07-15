package com.chinadaas.gsinfo.admin.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.exception.BizException;
import com.chinadaas.gsinfo.admin.service.CustomerService;
import com.chinadaas.gsinfo.admin.service.IService;
import com.chinadaas.gsinfo.admin.util.Constants;
import com.chinadaas.gsinfo.admin.util.Enum.CustomerState;
import com.chinadaas.gsinfo.admin.vo.BaseEntity;
import com.chinadaas.gsinfo.admin.vo.Customer;

/**
 * Customer action
 * projectName: gsinfo30-admin<br>
 * desc: Customer action<br>
 * date: 2014年7月8日 下午6:00:45<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class CustomerAction extends BizBaseAction<Customer> {
	
	/**
	 * 注入客户Service
	 * @see src/main/webapp/WEB-INF/applicationContext.xml
	 */
	@Autowired
	private CustomerService customerService;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public IService getBizService() {
		return customerService;
	}
	
	@Override
	protected BaseEntity createNewData() throws Exception {
		Customer c = (Customer) super.createNewData();
		c.setCreate_time(new Date());
		c.setId_user_create(getLoginUser());
		c.setState(CustomerState.ENABLE);
		return c;
	}
	
	@Override
	protected BaseEntity parseObject(Map params) throws Exception {
		Customer customer = (Customer) super.parseObject(params);
		customer.setBrief_name(customer.getFull_name());
		return customer;
	}
	
	@Override
	public void doSave(BaseEntity entity, Map params) throws BizException {
		Customer customer = (Customer) entity;
		
		HttpServletRequest request = getRequest();
		request.setAttribute(Constants.PARAM_LOGINUSER, getLoginUser());
		
		customerService.save(customer, request.getParameterMap());
	};
	
}


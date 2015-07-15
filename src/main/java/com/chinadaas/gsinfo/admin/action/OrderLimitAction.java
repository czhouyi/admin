package com.chinadaas.gsinfo.admin.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.service.IService;
import com.chinadaas.gsinfo.admin.service.OrderLimitService;
import com.chinadaas.gsinfo.admin.vo.OrderLimit;

/**
 * OrderLimit action
 * projectName: gsinfo30-admin<br>
 * desc: TODO<br>
 * date: 2014年7月8日 下午6:00:45<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class OrderLimitAction extends BizBaseAction<OrderLimit> {
	
	@Autowired
	private OrderLimitService orderLimitService;
	
	public void setOrderLimitService(OrderLimitService orderLimitService) {
		this.orderLimitService = orderLimitService;
	}

	@Override
	public IService getBizService() {
		return orderLimitService;
	}
	
}


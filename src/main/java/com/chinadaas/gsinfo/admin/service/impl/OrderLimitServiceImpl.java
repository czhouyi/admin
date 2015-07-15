package com.chinadaas.gsinfo.admin.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.dao.IDAO;
import com.chinadaas.gsinfo.admin.dao.OrderLimitDAO;
import com.chinadaas.gsinfo.admin.service.BaseService;
import com.chinadaas.gsinfo.admin.service.OrderLimitService;
import com.chinadaas.gsinfo.admin.vo.OrderLimit;

/**
 * OrderLimit service实现类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年7月8日 下午3:05:37<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class OrderLimitServiceImpl extends BaseService<OrderLimit> implements OrderLimitService{
	
	private static final Logger logger = LoggerFactory.getLogger(OrderLimitServiceImpl.class);
	
	@Autowired
	private OrderLimitDAO orderLimitDAO;


	public void setOrderLimitDAO(OrderLimitDAO orderLimitDAO) {
		this.orderLimitDAO = orderLimitDAO;
	}

	@Override
	protected IDAO<OrderLimit> getDao() {
		return orderLimitDAO;
	}

	@Override
	protected String getListSQL(Map param) {
		return null;
	}

	@Override
	protected String getSelectSQL(Map param) {
		return null;
	}

	@Override
	public void update(String idUser, String poid, String orderLimit) {
		String sql = "update t_order_limit set limit_cnt=? where id_user=? and id_product_order=?";
		execSQLUpdate(sql, new Object[]{orderLimit, idUser, poid});
	}

}

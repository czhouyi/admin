package com.chinadaas.gsinfo.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinadaas.gsinfo.admin.vo.OrderLimit;

/**
 * OrderLimit service接口类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年7月8日 下午2:48:32<br>
 * @author 开发者真实姓名[裔传洲]
 */
@Service
public interface OrderLimitService extends IService<OrderLimit> {
	@Transactional
	public void update(String idUser, String poid, String orderLimit);
}

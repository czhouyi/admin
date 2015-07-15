package com.chinadaas.gsinfo.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinadaas.gsinfo.admin.exception.BizException;
import com.chinadaas.gsinfo.admin.vo.ProductOrder;

/**
 * ProductOrder service接口类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年7月8日 下午2:48:32<br>
 * @author 开发者真实姓名[裔传洲]
 */
@Service
public interface ProductOrderService extends IService<ProductOrder> {

	/**
	 * desc: 保存产品实例，分配用户字段<br>
	 * date: 2014年9月4日 上午11:08:23<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param tplcode
	 * @param userid
	 * @throws BizException
	 */
	@Transactional
	public void saveAndAssign(String tplcode, String ukeyNo, String orderLimit) throws BizException;
	
	/**
	 * desc: 删除产品实例和分配的用户字段<br>
	 * date: 2014年9月4日 上午11:09:54<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param tplcode
	 * @param userid
	 * @throws BizException
	 */
	@Transactional
	public void deleteObjAndAssign(String poid, String userid) throws BizException;
	
	/**
	 * desc: 为产品实例分配字段<br>
	 * date: 2014年9月4日 下午5:33:34<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param poid
	 * @param assignResult
	 * @throws BizException
	 */
	@Transactional
	public void assign(String poid, String assignResult, String role, String idUser, String tplCode) throws BizException;
	
}

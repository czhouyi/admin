package com.chinadaas.gsinfo.admin.service;

import org.springframework.stereotype.Service;

import com.chinadaas.gsinfo.admin.exception.BizException;
import com.chinadaas.gsinfo.admin.vo.ProductTpl;

/**
 * ProductTpl service接口类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年7月8日 下午2:48:32<br>
 * @author 开发者真实姓名[裔传洲]
 */
@Service
public interface ProductTplService extends IService<ProductTpl> {
	
	/**
	 * desc: 为产品模板分配字段分组<br>
	 * date: 2014年9月4日 下午5:32:54<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param tplCode
	 * @param assignResult
	 * @throws BizException
	 */
	public void assign(String tplCode, String assignResult ) throws BizException;
	
}

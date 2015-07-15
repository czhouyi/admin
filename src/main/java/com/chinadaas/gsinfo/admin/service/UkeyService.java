package com.chinadaas.gsinfo.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinadaas.gsinfo.admin.exception.BizException;
import com.chinadaas.gsinfo.admin.vo.Ukey;

/**
 * UKey service接口类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年7月8日 下午2:48:32<br>
 * @author 开发者真实姓名[裔传洲]
 */
@Service
public interface UkeyService extends IService<Ukey> {

	/**
	 * 获取查询列表
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午1:59:43<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param param
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> tree(Map param);
	
	/**
	 * desc: 批量更新ip<br>
	 * date: Dec 25, 2014 3:20:28 PM<br>
	 * @author 开发者真实姓名[HuPeng]
	 * @param ukeyNos
	 * @param ip
	 * @throws BizException
	 */
	@Transactional
	public Boolean assign(String[] ukeyNos, String ip);
}

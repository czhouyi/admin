package com.chinadaas.gsinfo.admin.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.chinadaas.gsinfo.admin.dao.BaseDAO;
import com.chinadaas.gsinfo.admin.dao.UkeyDAO;
import com.chinadaas.gsinfo.admin.vo.Ukey;


/**
 * UKey持久化层私有方法实现
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年7月8日 下午3:03:41<br>
 * @author 开发者真实姓名[裔传洲]
 */
@Repository
public class UkeyDAOImpl extends BaseDAO<Ukey> implements UkeyDAO{

	@Override
	public String getIdUserByUkeyNo(String ukeyNo) {
		// TODO Auto-generated method stub
		String hql = "from Ukey u where u.ukey_no=?";
		@SuppressWarnings("unchecked")
		List<Ukey> ukeys = (List<Ukey>) filter(hql, ukeyNo);
		return ukeys.get(0).getUser_id();
	}

}

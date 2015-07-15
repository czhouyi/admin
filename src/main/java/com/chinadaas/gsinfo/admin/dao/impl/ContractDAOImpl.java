package com.chinadaas.gsinfo.admin.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.chinadaas.gsinfo.admin.dao.BaseDAO;
import com.chinadaas.gsinfo.admin.dao.ContractDAO;
import com.chinadaas.gsinfo.admin.vo.Contract;


/**
 * Contract持久化层私有方法实现
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年7月8日 下午3:03:41<br>
 * @author 开发者真实姓名[裔传洲]
 */
@Repository
public class ContractDAOImpl extends BaseDAO<Contract> implements ContractDAO{
	
	@Override
	public String save(Contract instance) throws DataAccessException {
		List rs = this.execSQLQuery("select SEQ_CONTRACT.NEXTVAL from dual");
		Map nv = (Map) rs.get(0);
		instance.setId(String.valueOf(nv.get("NEXTVAL")));
		return super.save(instance);
	}
}

package com.chinadaas.gsinfo.admin.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.dao.IDAO;
import com.chinadaas.gsinfo.admin.dao.OrgDAO;
import com.chinadaas.gsinfo.admin.exception.BizException;
import com.chinadaas.gsinfo.admin.service.BaseService;
import com.chinadaas.gsinfo.admin.service.OrgService;
import com.chinadaas.gsinfo.admin.util.StringUtil;
import com.chinadaas.gsinfo.admin.vo.Org;

/**
 * Org service实现类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年7月8日 下午3:05:37<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class OrgServiceImpl extends BaseService<Org> implements OrgService{
	
	private static final Logger logger = LoggerFactory.getLogger(OrgServiceImpl.class);
	
	@Autowired
	private OrgDAO orgDAO;


	public void setOrgDAO(OrgDAO orgDAO) {
		this.orgDAO = orgDAO;
	}

	@Override
	protected IDAO<Org> getDao() {
		return orgDAO;
	}
	
	@Override
	protected void checkBeforeSave(Org instance, Map param) throws BizException {
		super.checkBeforeSave(instance, param);
		if (StringUtil.isNull(instance.getId())) {
			String name = instance.getFull_name();
			if (orgDAO.exist("from Org where full_name = ?", name)) {
				throw new BizException(String.format("机构[full_name=%s]已存在", name));
			}
		}
	}

	@Override
	protected String getListSQL(Map param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append(" O.ID, ");														// 机构ID
		sql.append(" O.ID_CUSTOMER, ");												// 客户编码
		sql.append(" O.BRIEF_NAME, ");												// 机构简称
		sql.append(" O.FULL_NAME, ");												// 机构全称
		sql.append(" O.ORG_TYPE, ");												// 机构类型
		sql.append(" TO_CHAR(O.CREATE_TIME,'YYYY-MM-DD HH:MI:SS') CREATE_TIME "); 	// 创建时间
		sql.append(" FROM T_ORG O ");
		sql.append(" LEFT JOIN T_CUSTOMER C ON C.ID=O.ID_CUSTOMER ");
		sql.append(" WHERE 1=1 ");
		
		String orgName = StringUtil.getParam(param, "orgName");
		if(orgName != null && orgName.length() > 0) {
			sql.append(" AND (O.BRIEF_NAME LIKE '%").append(orgName).append("%' ");
			sql.append(" OR O.FULL_NAME LIKE '%").append(orgName).append("%') ");
		}
		
		String customerName = StringUtil.getParam(param, "customerName");;
		if(customerName != null && customerName.length() > 0) {
			sql.append(" AND C.FULL_NAME LIKE '%").append(customerName).append("%' ");
		}
		
		return sql.toString();
	}

	@Override
	protected String getSelectSQL(Map param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append(" O.ID, ");										// 机构ID
		sql.append(" O.FULL_NAME ");								// 机构名称
		sql.append(" FROM T_ORG O ");
		sql.append(" WHERE 1=1 ");
		
		return sql.toString();
	}

}

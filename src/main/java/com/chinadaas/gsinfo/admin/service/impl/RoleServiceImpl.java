package com.chinadaas.gsinfo.admin.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.dao.IDAO;
import com.chinadaas.gsinfo.admin.dao.RoleDAO;
import com.chinadaas.gsinfo.admin.service.BaseService;
import com.chinadaas.gsinfo.admin.service.RoleService;
import com.chinadaas.gsinfo.admin.util.StringUtil;
import com.chinadaas.gsinfo.admin.vo.Role;

/**
 * Role service实现类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年7月8日 下午3:05:37<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class RoleServiceImpl extends BaseService<Role> implements RoleService{
	
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Autowired
	private RoleDAO roleDAO;


	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	@Override
	protected IDAO<Role> getDao() {
		return roleDAO;
	}

	@Override
	protected String getListSQL(Map param) {
		return null;
	}

	@Override
	protected String getSelectSQL(Map param) {
		String tplCode = StringUtil.getParam(param, "tplCode");
		String idUser = StringUtil.getParam(param, "idUser");
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT A.RID RID,RN ");
		sql.append(" ,CASE WHEN B.ID_ROLE IS NULL THEN 'FALSE' ELSE 'TRUE' END CHECKED  ");
		sql.append(" FROM(SELECT '%S' IDU,R.ID RID,R.ROLE_NAME RN ");
		sql.append(" FROM T_ROLE R ");
		sql.append(" WHERE R.ID_CUSTOMER='默认角色' ");
		sql.append(" AND R.DESC_INFO='%S')A ");
		sql.append(" LEFT JOIN T_USER_ROLE B ");
		sql.append(" ON A.IDU=B.ID_USER AND A.RID=B.ID_ROLE ");
		sql.append(" ORDER BY A.RID ");
		
		return String.format(sql.toString(), idUser, tplCode);
	}

}

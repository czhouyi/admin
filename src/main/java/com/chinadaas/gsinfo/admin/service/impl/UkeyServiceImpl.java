package com.chinadaas.gsinfo.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.dao.IDAO;
import com.chinadaas.gsinfo.admin.dao.UkeyDAO;
import com.chinadaas.gsinfo.admin.exception.BizException;
import com.chinadaas.gsinfo.admin.service.BaseService;
import com.chinadaas.gsinfo.admin.service.UkeyService;
import com.chinadaas.gsinfo.admin.util.StringUtil;
import com.chinadaas.gsinfo.admin.vo.Ukey;

/**
 * ukey service实现类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年7月8日 下午3:05:37<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class UkeyServiceImpl extends BaseService<Ukey> implements UkeyService{
	
	private static final Logger logger = LoggerFactory.getLogger(UkeyServiceImpl.class);
	
	@Autowired
	private UkeyDAO ukeyDAO;


	public void setUkeyDAO(UkeyDAO ukeyDAO) {
		this.ukeyDAO = ukeyDAO;
	}

	@Override
	protected IDAO<Ukey> getDao() {
		return ukeyDAO;
	}
	
	@Override
	protected void checkBeforeSave(Ukey instance, Map param) throws BizException {
		super.checkBeforeSave(instance, param);
		if (StringUtil.isNull(instance.getId())) {
			String no = instance.getUkey_no();
			if (ukeyDAO.exist("from Ukey where ukey_no = ?", no)) {
				throw new BizException(String.format("Ukey[ukey_no=%s]已存在", no));
			}
		}
	}

	@Override
	protected String getListSQL(Map param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append(" UK.U_ID ID, ");									// Ukey ID
		sql.append(" UK.UKEY_NO, ");									// Ukey编码
		sql.append(" UK.PIN, ");										// Ukey PIN
		sql.append(" UK.IS_ADMIN, ");									// 是否客户管理员
		sql.append(" UK.IS_ADMIN_ORG, ");								// 是否机构管理员
		sql.append(" UK.IS_FREE, ");									// Ukey类型
		sql.append(" UK.STATE, ");										// Ukey状态
		sql.append(" UK.BAND_IP, ");									// 绑定IP
		sql.append(" UK.PUBLIC_KEY, ");									// 公钥
		sql.append(" TO_CHAR(UK.CREATE_TIME,'YYYY-MM-DD HH:MI:SS') CREATE_TIME, "); // 创建时间
		sql.append(" U.USER_NAME ");									// 用户名
		sql.append(" FROM T_UKEY UK ");
		sql.append(" LEFT JOIN T_USER U ON U.ID=UK.ID_USER_USE ");
		sql.append(" WHERE 1=1 ");
		
		String username = StringUtil.getParam(param, "username");
		if(username != null && username.length() > 0) {
			sql.append(" AND U.USER_NAME LIKE '%").append(StringUtil.decodeString(username)).append("%' ");
		}
		
		String ukeyNo = StringUtil.getParam(param, "ukeyNo");
		if(ukeyNo != null && ukeyNo.length() > 0) {
			sql.append(" AND UK.UKEY_NO LIKE '%").append(ukeyNo).append("%' ");
		}
		
		return sql.toString();
	}

	@Override
	protected String getSelectSQL(Map param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append(" UK.U_ID ID, ");									// Ukey ID
		sql.append(" UK.UKEY_NO ");										// Ukey编码
		sql.append(" FROM T_UKEY UK ");
		sql.append(" WHERE UK.ID_USER_USE IS NULL ");
		
		return sql.toString();
	}
	
	@Override
	public List<Map<String, Object>> tree(Map param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append(" UK.U_ID ID, ");									// Ukey ID
		sql.append(" UK.UKEY_NO ");										// Ukey编码
		sql.append(" FROM T_UKEY UK ");

		List<Map<String, Object>> all = execSQLQuery(sql.toString());

		return all;
	}

	@Override
	public Boolean assign(String[] ukeyNos, String ip) {
		List<String> ukeyList = new ArrayList<String>();
		for (String ukeyNo : ukeyNos) {
			String[] tmps = ukeyNo.split("-",-1);
			if (tmps.length>1) {
				if (tmps[0].matches("[0-9]{8}") && tmps[1].matches("[0-9]{8}")) {
					Integer start = Integer.valueOf(tmps[0]);
					Integer end = Integer.valueOf(tmps[1]);
					for (Integer i=start; i<=end; i++) {
						StringBuffer sb = new StringBuffer();
						for (int p=0; p<8-i.toString().length(); p++) {
							sb.append("0");
						}
						sb.append(i);
						ukeyList.add(sb.toString());
					}
				}
			} else {
				ukeyList.add(ukeyNo);
			}
		}
		if (ukeyList.size()>0) {
			Object[] params = new Object[ukeyList.size()+1];
			params[0] = ip;
			String sql = "update t_ukey set band_ip=? where 1=1";		
			List<String> placeHolderList = new ArrayList<String>();
			for (int i=0,len=ukeyList.size(); i<len; i++) {
					placeHolderList.add("?");
					params[i+1] = ukeyList.get(i);
			}
			sql += " and ukey_no in ("+StringUtils.join(placeHolderList, ",")+")";		
			execSQLUpdate(sql, params);
			return true;
		} else {
			return false;
		}
	}

}

package com.chinadaas.gsinfo.admin.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.dao.IDAO;
import com.chinadaas.gsinfo.admin.dao.OrgDAO;
import com.chinadaas.gsinfo.admin.dao.UkeyDAO;
import com.chinadaas.gsinfo.admin.dao.UserDAO;
import com.chinadaas.gsinfo.admin.exception.BizException;
import com.chinadaas.gsinfo.admin.service.BaseService;
import com.chinadaas.gsinfo.admin.service.UserService;
import com.chinadaas.gsinfo.admin.util.StringUtil;
import com.chinadaas.gsinfo.admin.vo.Org;
import com.chinadaas.gsinfo.admin.vo.Ukey;
import com.chinadaas.gsinfo.admin.vo.User;

/**
 * 系统用户service实现类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年7月8日 下午3:05:37<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class UserServiceImpl extends BaseService<User> implements UserService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UkeyDAO ukeyDAO;
	
	@Autowired
	private OrgDAO orgDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void setUkeyDAO(UkeyDAO ukeyDAO) {
		this.ukeyDAO = ukeyDAO;
	}

	public void setOrgDAO(OrgDAO orgDAO) {
		this.orgDAO = orgDAO;
	}

	@Override
	protected IDAO<User> getDao() {
		return this.userDAO;
	}

	@Override
	public boolean existUser(String userName) {
		return this.userDAO.exist("from User where user_name=?", userName);
	}

	@Override
	public boolean passAuth(String userid, String password) {
		boolean nullpasswd = password == null || password.length() <= 0;
		String md5 = nullpasswd ? "" : StringUtil.getMD5String(password);
		User u = this.userDAO.getObjectByHQL("from User where id=?", userid);
		if(StringUtil.isNull(md5) && StringUtil.isNull(u.getStop_reason())) {
			return true;
		}
		return md5.equals(u.getStop_reason());
	}

	@Override
	public void changePassword(String userid, String newpassword) {
		boolean nullpasswd = newpassword == null || newpassword.length() <= 0;
		String newmd5 = nullpasswd ? "" : StringUtil.getMD5String(newpassword);
		this.userDAO.execSQLUpdate("update t_user set stop_reason=? where id=?", newmd5, userid);
	}
	
	@Override
	protected void checkBeforeSave(User instance, Map param) throws BizException {
		super.checkBeforeSave(instance, param);
		if (StringUtil.isNull(instance.getId())) {
			String name = instance.getUser_name();
			if (userDAO.exist("from User where user_name = ?", name)) {
				throw new BizException(String.format("用户[userName=%s]已存在", name));
			}
		}
		// 如果机构id不存在 则把id当成名字进行查询
		if (!orgDAO.exist("from Org where id = ?", instance.getId_org())) {
			Org o = orgDAO.getObjectByHQL("from Org where full_name = ?", instance.getId_org());
			// 如果根据名字查不到该机构 则跑出异常     否则修改实例的机构id
			if (o == null || StringUtil.isNull(o.getId())) {
				throw new BizException("机构不存在");
			} else {
				instance.setId_org(o.getId());
			}
		}
		// 保存前从机构里取得客户号填充到用户信息里
		String orgid = instance.getId_org();
		Org org = orgDAO.getObjectById(orgid);
		if(org != null) {
			instance.setId_customer(org.getId_customer());
		}
	}
	
	@Override
	protected void doAfterSave(String id, Map param) throws BizException {
		super.doAfterSave(id, param);
		
		String ukeyid = StringUtil.getParam(param, "UKEY_ID");
		if(ukeyid != null && ukeyid.length() > 0) {
			// update ukey by user id
			Ukey uk = ukeyDAO.getObjectById(ukeyid);
			uk.setUser_id(id);
			ukeyDAO.update(uk);
		}
	}
	
	@Override
	protected void doAfterUpdate(User instance, Map param) throws BizException {
		super.doAfterUpdate(instance, param);
		
		String ip = StringUtil.getParam(param, "BAND_IP");
		if(ip != null && ip.length() > 0) {
			// 更新Ukey 绑定IP
			String sql = " UPDATE T_UKEY SET BAND_IP=? WHERE ID_USER_USE=? ";
			execSQLUpdate(sql, ip, instance.getId());
		}
		
		String orgid = StringUtil.getParam(param, "ID_ORG");
		if(orgid != null && orgid.length() > 0) {
			// 更新user的客户id
			updateCustomerOfUser(instance.getId(), orgid);
		}
	}
	
	private void updateCustomerOfUser(String userid, String orgid) {
		String sql = " update t_user set id_customer = " +
				 " (select id_customer from t_org where id=?) " +
				 " where id=? ";
		execSQLUpdate(sql, orgid, userid);
	}

	@Override
	protected String getListSQL(Map param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append(" U.ID, ");											// 用户ID
		sql.append(" U.USER_NAME, ");									// 用户名
		sql.append(" U.STATE, ");										// 用户注册状态
		sql.append(" U.IS_NEW_REG, ");									// 是否新注册
		sql.append(" U.MOBILE, ");										// 手机号
		sql.append(" U.EMAIL, ");										// email
		sql.append(" U.ADDRESS_INFO, ");								// 地址
		sql.append(" TO_CHAR(U.CREATE_TIME,'YYYY-MM-DD HH:MI:SS') CREATE_TIME, "); // 创建时间
		sql.append(" C.FULL_NAME CUSTOMER_FULL_NAME, ");				// 客户名
		sql.append(" UK.UKEY_NO, ");									// Ukey号
		sql.append(" UK.IS_ADMIN, ");									// 是否客户管理员
		sql.append(" UK.IS_ADMIN_ORG, ");								// 是否机构管理员
		sql.append(" UK.BAND_IP, ");									// 绑定IP
		sql.append(" O.FULL_NAME ORG_FULL_NAME, ");						// 机构名
		sql.append(" U.ID_CUSTOMER, ");									// 客户ID
		sql.append(" U.ID_ORG ");										// 机构ID
		sql.append(" FROM T_USER U ");
		sql.append(" LEFT JOIN T_CUSTOMER C ON C.ID=U.ID_CUSTOMER ");
		sql.append(" LEFT JOIN T_UKEY UK ON U.ID=UK.ID_USER_USE ");
		sql.append(" LEFT JOIN T_ORG O ON O.ID=U.ID_ORG ");
		sql.append(" WHERE 1=1 ");
		
		String username = StringUtil.getParam(param, "username");
		if(username != null && username.length() > 0) {
			sql.append(" AND U.USER_NAME LIKE '%").append(username).append("%' ");
		}
		
		String customerName = StringUtil.getParam(param, "customerName");
		if(customerName != null && customerName.length() > 0) {
			sql.append(" AND C.FULL_NAME LIKE '%").append(customerName).append("%' ");
		}
		
		String ukeyNo = StringUtil.getParam(param, "ukeyNo");
		if(ukeyNo != null && ukeyNo.length() > 0) {
			sql.append(" AND UK.UKEY_NO LIKE '%").append(ukeyNo).append("%' ");
		}
		
		return sql.toString();
	}

	@Override
	protected String getSelectSQL(Map param) {
		return null;
	}
	
}

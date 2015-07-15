package com.chinadaas.gsinfo.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.service.UserService;
import com.chinadaas.gsinfo.admin.util.Constants;
import com.chinadaas.gsinfo.admin.util.StringUtil;

/**
 * projectName: gsinfo30-admin<br>
 * desc: 视图展示Action<br>
 * date: 2014年8月27日 上午11:46:33<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class ViewAction extends BaseAction {
	/**
	 * 注入用户Service
	 * @see src/main/webapp/WEB-INF/applicationContext.xml
	 */
	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * desc: 用户信息大全视图<br>
	 * date: 2014年8月27日 下午12:01:43<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public String userInfo() {
		HttpServletRequest request = getRequest();
		String userName = request.getParameter("userName");
		String userid = request.getParameter("userid");
		String ukeyNo = request.getParameter("ukeyNo");
		
		StringBuffer sql = new StringBuffer();
		/**
		 * USER_NAME 	--用户名称
		 * ID_USER 		--用户ID
		 * U_ID 		--CA证书编号U_ID
		 * UKEY_NO 		--UKEY物理编号
		 * PUBLIC_KEY 	--CA证书内容
		 * ID_ORG 		--所属机构ID
		 * ID_CUSTOMER 	--所属客户ID
		 * CUSTOMER_NAME--所属客户
		 * KEY_IP		--UKEY绑定IP
		 * CUSTOMER_IP 	--客户通用IP
		 * IS_REG		--是否已注册--1是0非
		 * USER_STAT 	--用户状态--0禁用1正常2正在审核3信息不
		 * KEY_STAT 	--KEY状态--1正常0禁用
		 * ORG_STAT 	--机构状态--1正常0禁用
		 * CUSTOMER_STAT--客户状态--0 正常 1 人工停用 2 系统停用
		 */
		sql.append(" select ");
		sql.append(" * ");
		sql.append(" from v_user ");
		sql.append(" where 1=1 ");
		if(!StringUtil.isNull(userName)) {
			sql.append(" and user_name like '%").append(userName).append("%' ");
		}
		if(!StringUtil.isNull(userid)) {
			sql.append(" and id_user = '").append(userid).append("' ");
		}
		if(!StringUtil.isNull(ukeyNo)) {
			sql.append(" and ukey_no like '%").append(ukeyNo).append("%' ");
		}
		
		responseJSON(sql.toString());
		
		return NONE;
	}
	
	/**
	 * desc: 用户可用产品信息视图<br>
	 * date: 2014年8月27日 下午12:01:43<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public String userProductInfo() {
		HttpServletRequest request = getRequest();
		String userName = request.getParameter("userName");
		String userid = request.getParameter("userid");
		String ukeyNo = request.getParameter("ukeyNo");
		
		StringBuffer sql = new StringBuffer();
		/**
		 * USER_NAME 		--用户名称
		 * ID_USER 			--用户ID
		 * U_ID 			--CA证书编号U_ID
		 * UKEY_NO 			--UKEY物理编号
		 * ID_PRODUCT_ORDER --使用产品实例ID
		 * CODE_PRODUCT_TPL --产品模版编号
		 * PRODUCT_TPL_NAME --产品名称
		 * CNT_GRP 			--可查看组别数
		 * CNT_COL 			--可查看字段数
		 * LIMIT_CNT 		--该产品限制条数
		 * ORDER_CNT 		--已查条数
		 */
		sql.append(" select ");
		sql.append(" * ");
		sql.append(" from v_user_product_order ");
		sql.append(" where 1=1 ");
		if(!StringUtil.isNull(userName)) {
			sql.append(" and user_name like '%").append(userName).append("%' ");
		}
		if(!StringUtil.isNull(userid)) {
			sql.append(" and id_user = '").append(userid).append("' ");
		}
		if(!StringUtil.isNull(ukeyNo)) {
			sql.append(" and ukey_no like '%").append(ukeyNo).append("%' ");
		}
		
		responseJSON(sql.toString());
		
		return NONE;
	}
	
	/**
	 * desc: 用户菜单信息视图<br>
	 * date: 2014年8月27日 下午12:01:43<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public String userMenuInfo() {
		HttpServletRequest request = getRequest();
		String userName = request.getParameter("userName");
		String userid = request.getParameter("userid");
		String ukeyNo = request.getParameter("ukeyNo");
		
		StringBuffer sql = new StringBuffer();
		/**
		 * USER_NAME 	--用户名称
		 * ID_USER		--用户ID
		 * U_ID 		--CA证书编号U_ID
		 * UKEY_NO 		--UKEY物理编号
		 * ID_ROLE 		--所分配角色ID
		 * ROLE_NAME 	--角色描述
		 * CNT_MENU 	--角色可看菜单数
		 * CNT_P_TPL 	--角色可看产品数
		 */
		sql.append(" select ");
		sql.append(" * ");
		sql.append(" from v_user_memu ");
		sql.append(" where 1=1 ");
		if(!StringUtil.isNull(userName)) {
			sql.append(" and user_name like '%").append(userName).append("%' ");
		}
		if(!StringUtil.isNull(userid)) {
			sql.append(" and id_user = '").append(userid).append("' ");
		}
		if(!StringUtil.isNull(ukeyNo)) {
			sql.append(" and ukey_no like '%").append(ukeyNo).append("%' ");
		}
		
		responseJSON(sql.toString());
		
		return NONE;
	}
	
	/**
	 * desc: 用户可查看字段视图<br>
	 * date: 2014年8月27日 下午12:01:43<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public String userColumnInfo() {
		HttpServletRequest request = getRequest();
		String userName = request.getParameter("userName");
		String userid = request.getParameter("userid");
		String ukeyNo = request.getParameter("ukeyNo");
		
		StringBuffer sql = new StringBuffer();
		/**
		 * USER_NAME 			--用户名称
		 * ID_USER 				--用户ID
		 * UKEY_NO 				--UKEY物理编号
		 * ID_PRODUCT_ORDER		--产品实例ID
		 * P_TPL				--产品模板编号
		 * PRODUCT_TPL_NAME		--产品名称
		 * COLUMN_GROUP_NAME	--字段组别名
		 * ID_COLUMN			--字段ID
		 * COL_NAME				--字段名
		 * COL_DESC				--字段描述
		 * GRANT_TYP			--字段类型
		 * REVOKE_TIME			--回收时间
		 */
		sql.append(" select ");
		sql.append(" * ");
		sql.append(" from v_user_column ");
		sql.append(" where 1=1 ");
		if(!StringUtil.isNull(userName)) {
			sql.append(" and user_name like '%").append(userName).append("%' ");
		}
		if(!StringUtil.isNull(userid)) {
			sql.append(" and id_user = '").append(userid).append("' ");
		}
		if(!StringUtil.isNull(ukeyNo)) {
			sql.append(" and ukey_no like '%").append(ukeyNo).append("%' ");
		}
		
		responseJSON(sql.toString());
		
		return NONE;
	}
	
	/**
	 * desc: 返回json格式的response<br>
	 * date: 2014年8月27日 下午12:01:07<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param sql
	 */
	private void responseJSON(String sql) {
		HttpServletRequest request = getRequest();
		String start = request.getParameter(Constants.PARAM_START);
		String limit = request.getParameter(Constants.PARAM_LIMIT);
		
		List rslist = null;
		long totalCount = 0;
		boolean paged = start != null && limit.length() > 0 && start != null && limit.length() > 0;
		
		totalCount = userService.getCount(sql);
		if(paged) {
			rslist = userService.execSQLQuery(sql, Integer.valueOf(start), Integer.valueOf(limit));
		} else {
			rslist = userService.execSQLQuery(sql);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.JSON_TOTALCOUNT, totalCount);
		map.put(Constants.JSON_RESULT, StringUtil.toJson(rslist));

		response(true, Constants.JSON_OK, Constants.JSON_CODE_200, map);
	}
}


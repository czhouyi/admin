package com.chinadaas.gsinfo.admin.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.dao.IDAO;
import com.chinadaas.gsinfo.admin.dao.OrderLimitDAO;
import com.chinadaas.gsinfo.admin.dao.ProductOrderDAO;
import com.chinadaas.gsinfo.admin.dao.RoleDAO;
import com.chinadaas.gsinfo.admin.dao.UkeyDAO;
import com.chinadaas.gsinfo.admin.exception.BizException;
import com.chinadaas.gsinfo.admin.service.BaseService;
import com.chinadaas.gsinfo.admin.service.ProductOrderService;
import com.chinadaas.gsinfo.admin.util.StringUtil;
import com.chinadaas.gsinfo.admin.vo.OrderLimit;
import com.chinadaas.gsinfo.admin.vo.ProductOrder;

/**
 * ProductOrder service实现类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年7月8日 下午3:05:37<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class ProductOrderServiceImpl extends BaseService<ProductOrder> implements ProductOrderService{
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductOrderServiceImpl.class);
	
	@Autowired
	private ProductOrderDAO productOrderDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private UkeyDAO ukeyDAO;
	
	@Autowired
	private OrderLimitDAO orderLimitDAO;
	
	public void setOrderLimitDAO(OrderLimitDAO orderLimitDAO) {
		this.orderLimitDAO = orderLimitDAO;
	}
	
	public void setUkeyDAO(UkeyDAO ukeyDAO) {
		this.ukeyDAO = ukeyDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public void setProductOrderDAO(ProductOrderDAO productOrderDAO) {
		this.productOrderDAO = productOrderDAO;
	}

	@Override
	protected IDAO<ProductOrder> getDao() {
		return productOrderDAO;
	}

	@Override
	public void saveAndAssign(String tplcode, String ukeyNo, String orderLimit)
			throws BizException {
		String userid = ukeyDAO.getIdUserByUkeyNo(ukeyNo);
		// 保存product_order
		ProductOrder po = new ProductOrder();
		po.setCode_product_tpl(tplcode);
		String poid = save(po);
		
		// 分配product_user表
		execSQLUpdate(" insert into t_product_user values(?, ?) ", poid, userid);
		
		// 分配默认字段T_COLUMN default_typ not in (1,4)
		StringBuffer sqlbuf = new StringBuffer();
		sqlbuf.append(" insert into t_product_order_col ");
		sqlbuf.append(" 	select ?, c.id, sysdate, '1', sysdate, '', sysdate from t_column c ");
		sqlbuf.append(" 	left join t_column_group_pro_tpl cgpt on cgpt.code_column_group = c.code_column_group ");
		sqlbuf.append(" 	where cgpt.code_product_tpl=? and c.default_use_typ not in (1,4) ");
		execSQLUpdate(sqlbuf.toString(), poid, tplcode);
		LOG.info("Assign column successfully!");
		
		//分配角色
		assignRole(tplcode, userid);
		
		//分配订单数
		OrderLimit ol = new OrderLimit();
		ol.setCode_product_tpl(tplcode);
		ol.setId_product_order(poid);
		ol.setId_user(userid);
		ol.setLimit_cnt(Long.valueOf(orderLimit));
		ol.setOrder_cnt(0l);
		orderLimitDAO.save(ol);
//		StringBuffer sqlsb = new StringBuffer();
//		sqlsb.append("insert into t_order_limit ");
//		sqlsb.append("values(SEQ_ORDER_LIMIT.Nextval,'%s','%s','%s','%s',0,'','')");
//		execSQLUpdate(String.format(sqlsb.toString(), userid, tplcode, poid, orderLimit));
	}
	
	private void assignRole(String tplcode, String userid) {
		//删除公共角色
		String sql = "delete from t_user_role ur "
				+ " where ur.id_user=? "
				+ " and ur.id_role in "
				+ " (select id from t_role r "
				+ " where r.id_customer='默认角色' "
				+ " and r.desc_info='0') ";
		execSQLUpdate(sql, userid);
		
		//新增产品模板相关所有角色
		sql = "insert into t_user_role select ?, id "
				+ " from t_role "
				+ " where 1=1"
				+ " and id_customer='默认角色' "
				+ " and (desc_info=? or desc_info='0')";
		execSQLUpdate(sql, userid, tplcode);
		
//		//新建一个角色
//		Role role = new Role();
//		role.setState("1");
//		role.setUdt(new Date());
//		String roleId = roleDAO.save(role);
//		//角色关联菜单（菜单角色表）
//		StringBuffer sb = new StringBuffer();
//		sb.append("insert into t_menu_role ");
//		sb.append(" select id, '%s' ");
//		sb.append(" from t_menu where code_product_tpl='%s'");
//		execSQLUpdate(String.format(sb.toString(), roleId, tplcode));
//		//角色关联用户（角色用户表）
//		sql = "insert into t_user_role values('%s', '%s') ";
//		execSQLUpdate(String.format(sql, userid, roleId));
		LOG.info("Assign role successfully!");
	}

	@Override
	public void deleteObjAndAssign(String poid, String userid)
			throws BizException {
		// 删除product_user表数据
		String sql = " delete t_product_user where id_product_order=? and id_user=? ";
		execSQLUpdate(sql, poid, userid);
		
		/** 
		 * 清除残留的无用实例
		 * 残留判断标准：产品实例在product_user里无分配信息
		 */
		
		String sql1 = " delete t_product_order where id=? and not exists (select * from t_product_user where id_product_order=?) ";
		execSQLUpdate(sql1, poid, poid);
		
		/**
		 * 清楚残留的字段分配信息
		 * 残留判断标准：产品实例在product_user里无分配信息
		 */
		String sql2 = " delete t_product_order_col where id_product_order=? and not exists (select * from t_product_user where id_product_order=?) ";
		execSQLUpdate(sql2, poid, poid);
	}

	@Override
	public void assign(String poid, String assignResult, String role, String idUser, String tplCode) throws BizException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//分配角色
		if (idUser != null) {
			//删除该用户  该产品模板下的全部角色
			StringBuffer sql = new StringBuffer();
			sql.append(" delete from t_user_role ");
			sql.append(" where id_user=?  ");
			sql.append(" and id_role in ");
			sql.append(" (select r.id  ");
			sql.append(" from t_role r  ");
			sql.append(" where r.id_customer='默认角色' ");
			sql.append(" and r.desc_info=?) ");
			execSQLUpdate(sql.toString(), idUser, tplCode);
			
			//新增角色关系
			String [] roles = role.split(",");
			for (String r : roles) {
				execSQLUpdate("insert into t_user_role values(?, ?)", idUser, r);
			}
		}
		//分配字段
		if (poid != null && poid.length() > 0 && assignResult != null) {
			execSQLUpdate(" DELETE t_product_order_col where id_product_order=? ", poid);
			
			for(String record : assignResult.split(";")){
				if(StringUtil.isNull(record)) {
					continue;
				}
				String colid = record.split(",", -1)[0];
				String granttype = record.split(",", -1)[1];
				if(StringUtil.isNull(granttype)) {
					granttype = "1";
				}
				String revoketime = record.split(",", -1)[2];
				if(StringUtil.isNull(revoketime)) {
					revoketime = sdf.format(new Date());
				}
				execSQLUpdate(" INSERT INTO t_product_order_col values(?, ?, sysdate, ?, to_date(?,'yyyy-mm-dd'), '', '') ", 
						poid, colid, granttype, revoketime);
			}
		}
		
	}
	public static void main(String[] args) {
		String a="";
		String[] b = a.split(",");
		System.out.println(b.toString());
	}

	@Override
	protected String getListSQL(Map param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append(" pt.product_tpl_name, ");						// 产品名称
		sql.append(" uk.ukey_no ukeyNo, ");							// Ukey编号
		sql.append(" u.user_name, ");								// 用户名称
		sql.append(" u.id userid, ");								// 用户ID
		sql.append(" po.id poid, ");								// 产品实例ID
		sql.append(" pt.code tplcode ,");							// 产品模板编号
		sql.append(" ol.limit_cnt orderlimit, ");					// 订单限制
		sql.append(" ol.order_cnt ordercnt ");						// 订单查询量
		sql.append(" FROM T_PRODUCT_ORDER PO ");
		sql.append(" left join t_product_tpl pt on pt.code=po.code_product_tpl ");
		sql.append(" left join t_product_user pu on pu.id_product_order=po.id ");
		sql.append(" left join t_user u on u.id=pu.id_user ");
		sql.append(" left join t_ukey uk on u.id=uk.id_user_use ");
		sql.append(" left join t_order_limit ol on po.id=ol.id_product_order and u.id=ol.id_user");
		sql.append(" where 1=1 ");
		
		String userName = StringUtil.getParam(param, "userName");
		if(!StringUtil.isNull(userName)) {
			sql.append(" and u.user_name LIKE '%"+userName+"%' ");
		}
		
		String ukeyNo =StringUtil.getParam(param, "ukeyNo");
		if(!StringUtil.isNull(ukeyNo)) {
			sql.append(" and uk.ukey_no LIKE '%"+ukeyNo+"%' ");
		}
		
		sql.append(" order by u.id, pt.code ");
		return sql.toString();
	}

	@Override
	protected String getSelectSQL(Map param) {
		return null;
	}
}

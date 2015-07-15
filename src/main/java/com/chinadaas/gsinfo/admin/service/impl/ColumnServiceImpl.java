package com.chinadaas.gsinfo.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.dao.ColumnDAO;
import com.chinadaas.gsinfo.admin.dao.IDAO;
import com.chinadaas.gsinfo.admin.exception.BizException;
import com.chinadaas.gsinfo.admin.service.BaseService;
import com.chinadaas.gsinfo.admin.service.ColumnService;
import com.chinadaas.gsinfo.admin.util.StringUtil;
import com.chinadaas.gsinfo.admin.vo.Column;


/**
 * Column service实现类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年7月8日 下午3:05:37<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class ColumnServiceImpl extends BaseService<Column> implements ColumnService{
	
	private static final Logger logger = LoggerFactory.getLogger(ColumnServiceImpl.class);
	
	@Autowired
	private ColumnDAO columnDAO;

	public void setColumnDAO(ColumnDAO columnDAO) {
		this.columnDAO = columnDAO;
	}

	@Override
	protected IDAO<Column> getDao() {
		return this.columnDAO;
	}
	
	@Override
	protected void checkBeforeSave(Column instance, Map param) throws BizException {
		super.checkBeforeSave(instance, param);
		if (StringUtil.isNull(instance.getId())) {
			String name = instance.getCol_name();
			if (columnDAO.exist("from Column where col_name = ?", name)) {
				throw new BizException(String.format("字段[col_name=%s]已存在", name));
			}
		}
	}

	@Override
	protected String getListSQL(Map param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append(" C.ID, ");											// 列ID
		sql.append(" C.CODE_COLUMN_GROUP, ");							// 列分组
		sql.append(" CG.COLUMN_GROUP_NAME, ");							// 列分组名称
		sql.append(" C.COL_NAME, ");									// 列名
		sql.append(" C.COL_DESC, ");									// 列描述
		sql.append(" C.ORDER_NUM, ");									// 列顺序号
		sql.append(" C.STATE, ");										// 列状态
		sql.append(" TO_CHAR(C.UDT,'YYYY-MM-DD HH:MI:SS') UDT, ");		// UDT
		sql.append(" C.NODE_NAME, ");									// NODENAME
		sql.append(" C.DEFAULT_USE_TYP DEFAULT_USE_TYPE");				// 列类型
		sql.append(" FROM T_COLUMN C ");
		sql.append(" LEFT JOIN T_COLUMN_GROUP CG ON CG.CODE=C.CODE_COLUMN_GROUP ");
		sql.append(" WHERE 1=1 ");
		
		String columnGroup = StringUtil.getParam(param, "columnGroup");
		if(columnGroup != null && columnGroup.length() > 0) {
			sql.append(" AND CG.COLUMN_GROUP_NAME like '%").append(columnGroup).append("%' ");
		}
		
		sql.append(" ORDER BY C.CODE_COLUMN_GROUP, C.ORDER_NUM ");
		
		return sql.toString();
	}

	@Override
	protected String getSelectSQL(Map param) {
		return null;
	}

	@Override
	public List<Map<String, Object>> tree(Map param) {
		String tplCode = StringUtil.getParam(param, "tplCode");
		String userid = StringUtil.getParam(param, "userid");

		StringBuffer sql = new StringBuffer();
		sql.append(" select t.cgcode, t.id colid, t.cgname, t.colname, t.func_code, ");
		sql.append(" b.grant_typ, to_char(b.revoke_time,'yyyy-mm-dd hh:mi:ss') revoke_time, t.DEFAULT_USE_TYP, ");
		sql.append(" case when b.id is null then 'false' else 'true' end checked ");
		sql.append(" from ");
		sql.append(" ( ");
		sql.append(" select pt.code ptcode, cg.code cgcode, c.DEFAULT_USE_TYP, ");
		sql.append(" cg.column_group_name cgname, c.id ,c.col_desc colname, cg.func_code ");
		sql.append(" from t_column_group_pro_tpl cgpt ");
		sql.append(" left outer join t_column_group cg on cg.code=cgpt.code_column_group ");
		sql.append(" left outer join t_product_tpl pt on pt.code=cgpt.code_product_tpl ");
		sql.append(" inner join t_column c on c.code_column_group=cg.code ");
		sql.append(" order by pt.code, cg.code ");
		sql.append(" ) T ");
		sql.append(" left outer join ( ");
		sql.append(" SELECT distinct  po.code_product_tpl, c.code_column_group, c.id, pu.id_user, poc.grant_typ, poc.revoke_time ");
		sql.append(" from t_product_order_col poc ");
		sql.append(" left outer join t_product_order po on po.id=poc.id_product_order ");
		sql.append(" left outer join t_product_user pu on pu.id_product_order=po.id ");
		sql.append(" inner join t_column c on c.id=poc.id_column ");
		sql.append(" where pu.id_user=?  ");
		sql.append(" ) B on b.id=t.id and t.ptcode=b.code_product_tpl and b.code_column_group=t.cgcode ");
		sql.append(" where t.ptcode=? ");

		List<Map<String, Object>> all = execSQLQuery(sql.toString(), userid, tplCode);

		List newlist = new ArrayList();
		for(Object obj : all) {
			Map m = (Map) obj;
			Map newm = new HashMap();
			for (Iterator iterator = m.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry) iterator.next();
				String key = (String) entry.getKey();
				Object value = entry.getValue();
				if("CHECKED".equals(key)){
					newm.put(key.toLowerCase(), Boolean.parseBoolean(value.toString()));
				} else if("REVOKE_TIME".equals(key)) {
					newm.put(key.toLowerCase(), value == null ? null : value.toString());
				} else {
					newm.put(key.toLowerCase(), value);
				}
			}
			newlist.add(newm);
		}
		
		return newlist;
	}

}

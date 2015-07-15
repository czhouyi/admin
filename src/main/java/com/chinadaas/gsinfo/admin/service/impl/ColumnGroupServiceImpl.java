package com.chinadaas.gsinfo.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.dao.ColumnGroupDAO;
import com.chinadaas.gsinfo.admin.dao.IDAO;
import com.chinadaas.gsinfo.admin.exception.BizException;
import com.chinadaas.gsinfo.admin.service.BaseService;
import com.chinadaas.gsinfo.admin.service.ColumnGroupService;
import com.chinadaas.gsinfo.admin.util.StringUtil;
import com.chinadaas.gsinfo.admin.vo.ColumnGroup;


/**
 * ColumnGroup service实现类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年7月8日 下午3:05:37<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class ColumnGroupServiceImpl extends BaseService<ColumnGroup> implements ColumnGroupService{
	
	private static final Logger logger = LoggerFactory.getLogger(ColumnGroupServiceImpl.class);
	
	@Autowired
	private ColumnGroupDAO columnGroupDAO;

	public void setColumnGroupDAO(ColumnGroupDAO columnGroupDAO) {
		this.columnGroupDAO = columnGroupDAO;
	}

	@Override
	protected IDAO<ColumnGroup> getDao() {
		return this.columnGroupDAO;
	}
	
	@Override
	protected void checkBeforeSave(ColumnGroup instance, Map param) throws BizException {
		super.checkBeforeSave(instance, param);
		if (StringUtil.isNull(instance.getId())) {
			String name = instance.getColumn_group_name();
			if (columnGroupDAO.exist("from ColumnGroup where column_group_name = ?", name)) {
				throw new BizException(String.format("字段组别[column_group_name=%s]已存在", name));
			}
		}
	}

	@Override
	protected String getListSQL(Map param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append(" CG.CODE ID, ");									// 列分组ID
		sql.append(" CG.COLUMN_GROUP_NAME, ");							// 列分组名称
		sql.append(" CG.FUNC_CODE ");									// person/enterprise
		sql.append(" FROM T_COLUMN_GROUP CG ");
		sql.append(" WHERE 1=1 ");
		
		return sql.toString();
	}

	@Override
	protected String getSelectSQL(Map param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append(" CG.CODE CODE_COLUMN_GROUP, ");						// 列分组ID
		sql.append(" CG.COLUMN_GROUP_NAME ");							// 列分组名称
		sql.append(" FROM T_COLUMN_GROUP CG ");
		sql.append(" WHERE 1=1 ");
		
		return sql.toString();
	}

	@Override
	public List<Map<String, Object>> tree(Map param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append(" CG.CODE ID, ");																	// 列分组ID
		sql.append(" CG.COLUMN_GROUP_NAME TEXT, ");														// 列分组名称
		sql.append(" 'true' LEAF, ");																	// 列分组名称
		sql.append(" CASE WHEN CGPT.CODE_COLUMN_GROUP IS NULL THEN 'false' ELSE 'true' END CHECKED");	// 是否选中
		sql.append(" FROM T_COLUMN_GROUP CG ");

		String tplCode = StringUtil.getParam(param, "tplCode");
		if(tplCode != null && tplCode.length() > 0) {
			sql.append(" LEFT OUTER JOIN (SELECT * FROM T_COLUMN_GROUP_PRO_TPL WHERE CODE_PRODUCT_TPL='"+tplCode+"') CGPT ON CGPT.CODE_COLUMN_GROUP=CG.CODE ");
		} else {
			sql.append(" LEFT OUTER JOIN T_COLUMN_GROUP_PRO_TPL CGPT ON CGPT.CODE_COLUMN_GROUP=CG.CODE ");
		}
		sql.append(" WHERE 1=1 ");

		List rslist = execSQLQuery(sql.toString());

		List newlist = new ArrayList();
		for(Object obj : rslist) {
			Map m = (Map) obj;
			Map newm = new HashMap();
			for (Iterator iterator = m.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry) iterator.next();
				String key = (String) entry.getKey();
				Object value = entry.getValue();
				if("LEAF".equals(key) || "CHECKED".equals(key)){
					newm.put(key.toLowerCase(), Boolean.parseBoolean(value.toString()));
				} else {
					newm.put(key.toLowerCase(), value);
				}
			}
			newlist.add(newm);
		}

		return newlist;
	}
	
	

}

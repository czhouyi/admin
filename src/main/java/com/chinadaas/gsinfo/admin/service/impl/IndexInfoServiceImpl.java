package com.chinadaas.gsinfo.admin.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.dao.IDAO;
import com.chinadaas.gsinfo.admin.dao.IndexInfoDAO;
import com.chinadaas.gsinfo.admin.service.BaseService;
import com.chinadaas.gsinfo.admin.service.IndexInfoService;
import com.chinadaas.gsinfo.admin.vo.IndexInfo;

/**
 * IndexInfo service实现类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年7月8日 下午3:05:37<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class IndexInfoServiceImpl extends BaseService<IndexInfo> implements IndexInfoService{
	
	private static final Logger logger = LoggerFactory.getLogger(IndexInfoServiceImpl.class);
	
	@Autowired
	private IndexInfoDAO indexInfoDAO;


	public void setIndexInfoDAO(IndexInfoDAO indexInfoDAO) {
		this.indexInfoDAO = indexInfoDAO;
	}

	@Override
	protected IDAO<IndexInfo> getDao() {
		return indexInfoDAO;
	}

	@Override
	protected String getListSQL(Map param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append(" ID, ");								// ID
		sql.append(" INFO_TYPE, ");							// 类型
		sql.append(" TITLE, ");								// 标题
		sql.append(" INFO_CONTENT, ");						// 正文
		sql.append(" STATUS, ");							// 状态
		sql.append(" TO_CHAR(START_TIME,'YYYY-MM-DD HH:MI:SS') START_TIME, ");						// 起始日期
		sql.append(" TO_CHAR(END_TIME,'YYYY-MM-DD HH:MI:SS') END_TIME ");							// 截止日期
		sql.append(" FROM T_INDEX_INFO ");
		sql.append(" WHERE 1=1 ");
		
		return sql.toString();
	}

	@Override
	protected String getSelectSQL(Map param) {
		return null;
	}

}

package com.chinadaas.gsinfo.admin.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.dao.IDAO;
import com.chinadaas.gsinfo.admin.dao.ProductTplDAO;
import com.chinadaas.gsinfo.admin.exception.BizException;
import com.chinadaas.gsinfo.admin.service.BaseService;
import com.chinadaas.gsinfo.admin.service.ProductTplService;
import com.chinadaas.gsinfo.admin.util.StringUtil;
import com.chinadaas.gsinfo.admin.vo.ProductTpl;

/**
 * ProductTpl service实现类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年7月8日 下午3:05:37<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class ProductTplServiceImpl extends BaseService<ProductTpl> implements ProductTplService{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductTplServiceImpl.class);
	
	@Autowired
	private ProductTplDAO productTplDAO;


	public void setProductTplDAO(ProductTplDAO productTplDAO) {
		this.productTplDAO = productTplDAO;
	}

	@Override
	protected IDAO<ProductTpl> getDao() {
		return productTplDAO;
	}
	
	@Override
	protected void checkBeforeSave(ProductTpl instance, Map param) throws BizException {
		super.checkBeforeSave(instance, param);
		if (StringUtil.isNull(instance.getId())) {
			String name = instance.getProduct_tpl_name();
			if (productTplDAO.exist("from ProductTpl where product_tpl_name = ?", name)) {
				throw new BizException(String.format("产品模板[product_tpl_name=%s]已存在", name));
			}
		}
	}

	@Override
	public void assign(String tplCode, String assignResult) throws BizException {
		if(tplCode != null && tplCode.length() > 0 
				&& assignResult != null && assignResult.length() > 0){
			execSQLUpdate(" DELETE T_COLUMN_GROUP_PRO_TPL WHERE CODE_PRODUCT_TPL=? ", tplCode);
			
			for(String id : assignResult.split(",")){
				execSQLUpdate("INSERT INTO T_COLUMN_GROUP_PRO_TPL values(?, ?)", tplCode, id);
			}
		}
	}
	
	@Override
	protected void checkBeforeDelete(String id) throws BizException {
		super.checkBeforeDelete(id);
		
		List<Map> count = execSQLQuery(" select count(1) cnt from t_product_order where code_product_tpl =? ", id);
		BigDecimal cnt = (BigDecimal) count.get(0).get("cnt");
		if (cnt.intValue() > 0) {
			throw new BizException(String.format("产品模板[id=%s]已生成产品实例[%d个],不能删除", id, cnt.intValue()));
		}
	}
	
	@Override
	protected void doAfterDelete(String id) throws BizException {
		super.doAfterDelete(id);
		// 删除产品模板分配的信息
		execSQLUpdate(" DELETE T_COLUMN_GROUP_PRO_TPL WHERE CODE_PRODUCT_TPL=? ", id);
	}

	@Override
	protected String getListSQL(Map param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append(" TPL.CODE ID, ");												// 产品ID
		sql.append(" TPL.PRODUCT_TPL_NAME, ");										// 产品名称
		sql.append(" TO_CHAR(TPL.CREATE_TIME,'YYYY-MM-DD HH:MI:SS') CREATE_TIME ");	// 创建时间
		sql.append(" FROM T_PRODUCT_TPL TPL ");
		sql.append(" WHERE 1=1 ");
		sql.append(" ORDER BY TO_NUMBER(TPL.CODE) ");
		
		return sql.toString();
	}

	@Override
	protected String getSelectSQL(Map param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append(" TPL.CODE TPLCODE, ");									// 产品ID
		sql.append(" TPL.PRODUCT_TPL_NAME ");								// 产品名称
		sql.append(" FROM T_PRODUCT_TPL TPL ");
		sql.append(" WHERE 1=1 ");
		sql.append(" ORDER BY TO_NUMBER(TPL.CODE) ");
		
		return sql.toString();
	}
}

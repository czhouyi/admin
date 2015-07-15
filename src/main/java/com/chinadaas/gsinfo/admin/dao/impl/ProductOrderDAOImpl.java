package com.chinadaas.gsinfo.admin.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.chinadaas.gsinfo.admin.dao.BaseDAO;
import com.chinadaas.gsinfo.admin.dao.ProductOrderDAO;
import com.chinadaas.gsinfo.admin.vo.ProductOrder;


/**
 * ProductOrder持久化层私有方法实现
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年7月8日 下午3:03:41<br>
 * @author 开发者真实姓名[裔传洲]
 */
@Repository
public class ProductOrderDAOImpl extends BaseDAO<ProductOrder> implements ProductOrderDAO{

	@Override
	public String save(ProductOrder instance) {
		List rs = this.execSQLQuery("SELECT SEQ_PRODUCT_ORDER.NEXTVAL FROM DUAL");
		Map nv = (Map) rs.get(0);
		instance.setId(String.valueOf(nv.get("NEXTVAL")));
		return super.save(instance);
	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String queryPoid(String tplcode, String idCustomer) {
		String sql = "SELECT ID FROM T_PRODUCT_ORDER T WHERE T.ID_USER_CREATE='%s' AND T.CODE_PRODUCT_TPL='%s'";
		System.out.println("queryPoid sql= " + sql.format(sql, idCustomer, tplcode));
		List<Map<String, String>> rs = this.execSQLQuery(sql.format(sql, idCustomer, tplcode));
		if (rs == null || rs.isEmpty()) return null;
		return rs.get(0).get("ID");
	}
}

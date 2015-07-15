package com.chinadaas.gsinfo.admin.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: 产品实例实体类<br>
 * date: 2014年9月1日 下午2:50:44<br>
 * @author 开发者真实姓名[裔传洲]
 */
@Entity
@Table(name = "T_PRODUCT_USER")
public class ProductUser extends BaseEntity{
	
	@Column(name = "ID_PRODUCT_ORDER", length = 50)
	private String product_order_id;
	
	@Column(name = "ID_USER", length = 50)
	private String user_id;

	public String getProduct_order_id() {
		return product_order_id;
	}

	public void setProduct_order_id(String product_order_id) {
		this.product_order_id = product_order_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	

}

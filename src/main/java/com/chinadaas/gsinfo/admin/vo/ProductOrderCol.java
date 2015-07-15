package com.chinadaas.gsinfo.admin.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: 产品实例分配字段实体类<br>
 * date: 2014年9月1日 下午2:52:18<br>
 * @author 开发者真实姓名[裔传洲]
 */
@Entity
@Table(name = "T_PRODUCT_ORDER_COL")
public class ProductOrderCol extends BaseEntity{
	
	@Column(name = "ID_PRODUCT_ORDER", length = 50)
	private String product_order_id;
	
	@Column(name = "ID_COLUMN", length = 100)
	private String column_id;
	
	@Column(name = "GRANT_TIME")
	private Date grant_time;
	
	@Column(name = "GRANT_TYP", length = 2)
	private String grant_type;
	
	@Column(name = "REVOKE_TIME")
	private Date revoke_time;
	
	@Column(name = "ID_USER_MODIFY", length = 50)
	private String modifier;

	@Column(name = "MODIFY_TIME")
	private Date modify_time;

	public String getProduct_order_id() {
		return product_order_id;
	}

	public void setProduct_order_id(String product_order_id) {
		this.product_order_id = product_order_id;
	}

	public String getColumn_id() {
		return column_id;
	}

	public void setColumn_id(String column_id) {
		this.column_id = column_id;
	}

	public Date getGrant_time() {
		return grant_time;
	}

	public void setGrant_time(Date grant_time) {
		this.grant_time = grant_time;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public Date getRevoke_time() {
		return revoke_time;
	}

	public void setRevoke_time(Date revoke_time) {
		this.revoke_time = revoke_time;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

}

package com.chinadaas.gsinfo.admin.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: Jul 9, 2014 5:33:06 PM<br>
 * @author 开发者真实姓名[HuPeng]
 */
@Entity
@Table(name="T_ORDER_LIMIT")
public class OrderLimit extends BaseEntity{
	
	@Id
	@Column( name = "ID", unique = true, nullable = false, length=50 )
//	@GeneratedValue( strategy = GenerationType.SEQUENCE )
	private String id;
	
	@Column( length = 50 )
	private String id_user;
	
	@Column( length = 20 )
	private String code_product_tpl;
	
	@Column( length = 50 )
	private String id_product_order;
	
	@Column
	private Long limit_cnt;
	
	@Column
	private Long order_cnt;
	
	@Column
	private Date last_login_time;
	
	@Column( length = 100 )
	private String last_login_ip;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId_user() {
		return id_user;
	}
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}
	public String getCode_product_tpl() {
		return code_product_tpl;
	}
	public void setCode_product_tpl(String code_product_tpl) {
		this.code_product_tpl = code_product_tpl;
	}
	public String getId_product_order() {
		return id_product_order;
	}
	public void setId_product_order(String id_product_order) {
		this.id_product_order = id_product_order;
	}
	public Long getLimit_cnt() {
		return limit_cnt;
	}
	public void setLimit_cnt(Long limit_cnt) {
		this.limit_cnt = limit_cnt;
	}
	public Long getOrder_cnt() {
		return order_cnt;
	}
	public void setOrder_cnt(Long order_cnt) {
		this.order_cnt = order_cnt;
	}
	public Date getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}
	public String getLast_login_ip() {
		return last_login_ip;
	}
	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}
	
	
}


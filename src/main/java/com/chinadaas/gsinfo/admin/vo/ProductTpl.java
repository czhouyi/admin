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
 * date: Jul 9, 2014 6:06:27 PM<br>
 * @author 开发者真实姓名[HuPeng]
 */
@Entity
@Table(name="T_PRODUCT_TPL")
public class ProductTpl extends BaseEntity{
	@Id
	@Column(name = "CODE", unique = true, nullable = false,length=20)
//	@GeneratedValue( strategy = GenerationType.SEQUENCE )
	private String id;
	
	@Column( length = 100, nullable = false )
	private String product_tpl_name;
	
	@Column( length = 2 )
	private String state;
	
	@Column( length = 2 )
	private String calc_fee;
	
	@Column( length = 50 )
	private String id_user_manager;
	
	@Column
	private Date test_date;
	
	@Column
	private Date online_date;
	
	@Column( length = 50 )
	private String id_user_create;
	
	@Column
	private Date create_time;
	
	@Column( length = 50 )
	private String id_user_modify;
	
	@Column
	private Date modify_time;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProduct_tpl_name() {
		return product_tpl_name;
	}
	public void setProduct_tpl_name(String product_tpl_name) {
		this.product_tpl_name = product_tpl_name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCalc_fee() {
		return calc_fee;
	}
	public void setCalc_fee(String calc_fee) {
		this.calc_fee = calc_fee;
	}
	public String getId_user_manager() {
		return id_user_manager;
	}
	public void setId_user_manager(String id_user_manager) {
		this.id_user_manager = id_user_manager;
	}
	public Date getTest_date() {
		return test_date;
	}
	public void setTest_date(Date test_date) {
		this.test_date = test_date;
	}
	public Date getOnline_date() {
		return online_date;
	}
	public void setOnline_date(Date online_date) {
		this.online_date = online_date;
	}
	public String getId_user_create() {
		return id_user_create;
	}
	public void setId_user_create(String id_user_create) {
		this.id_user_create = id_user_create;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getId_user_modify() {
		return id_user_modify;
	}
	public void setId_user_modify(String id_user_modify) {
		this.id_user_modify = id_user_modify;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	
	
}


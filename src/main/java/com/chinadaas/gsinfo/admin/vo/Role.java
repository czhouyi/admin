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
 * date: Jul 10, 2014 10:34:35 AM<br>
 * @author 开发者真实姓名[HuPeng]
 */
@Entity
@Table(name="T_ROLE")
public class Role extends BaseEntity{

	@Id
	@Column( name = "ID", unique = true, nullable = false, length=50 )
	private String id;
	
	@Column( length = 200 )
	private String role_name;
	
	@Column( length = 50 )
	private String id_customer;
	
	@Column( length = 2 )
	private String role_type;
	
	@Column( length = 100 )
	private String desc_info;
	
	@Column( length = 2 )
	private String state;
	
	@Column
	private Date udt;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getId_customer() {
		return id_customer;
	}
	public void setId_customer(String id_customer) {
		this.id_customer = id_customer;
	}
	public String getRole_type() {
		return role_type;
	}
	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}
	public String getDesc_info() {
		return desc_info;
	}
	public void setDesc_info(String desc_info) {
		this.desc_info = desc_info;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getUdt() {
		return udt;
	}
	public void setUdt(Date udt) {
		this.udt = udt;
	}
	
}


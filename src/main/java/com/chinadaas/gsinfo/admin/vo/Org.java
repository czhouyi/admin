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
 * date: Jul 9, 2014 5:52:55 PM<br>
 * @author 开发者真实姓名[HuPeng]
 */
@Entity
@Table(name="T_ORG")
public class Org extends BaseEntity {
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 50)
//	@GeneratedValue( strategy = GenerationType.SEQUENCE )
	private String id;
	
	@Column( length = 50 )
	private String id_customer;
	
	@Column
	private Long id_area;
	
	@Column( length = 50 )
	private String p_code;
	
	@Column( length = 100 )
	private String brief_name;
	
	@Column( length = 300 )
	private String full_name;
	
	@Column( length = 2 )
	private String org_type;
	
	@Column( length = 2 )
	private String state;
	
	@Column( length = 20 )
	private String linkman;
	
	@Column( length = 11 )
	private String mobile;
	
	@Column( length = 100 )
	private String email;
	
	@Column( length = 20 )
	private String phone;
	
	@Column( length = 200 )
	private String address_info;
	
	@Column( length = 6 )
	private String zipcode;
	
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
	public String getId_customer() {
		return id_customer;
	}
	public void setId_customer(String id_customer) {
		this.id_customer = id_customer;
	}
	public Long getId_area() {
		return id_area;
	}
	public void setId_area(Long id_area) {
		this.id_area = id_area;
	}
	public String getP_code() {
		return p_code;
	}
	public void setP_code(String p_code) {
		this.p_code = p_code;
	}
	public String getBrief_name() {
		return brief_name;
	}
	public void setBrief_name(String brief_name) {
		this.brief_name = brief_name;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getOrg_type() {
		return org_type;
	}
	public void setOrg_type(String org_type) {
		this.org_type = org_type;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress_info() {
		return address_info;
	}
	public void setAddress_info(String address_info) {
		this.address_info = address_info;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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


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
 * desc: TODO<br>
 * date: 2014年7月9日 下午4:18:47<br>
 * @author 开发者真实姓名[Andy]
 */
@Entity
@Table(name="T_CUSTOMER")
public class Customer extends BaseEntity{
	
	@Id
	@Column( name = "ID", unique = true, nullable = false, length=50 )
//	@GeneratedValue( strategy = GenerationType.SEQUENCE )
	private String id;
	
	@Column( length = 50 )
	private String id_account_cust;
	
	@Column( length = 100 )
	private String brief_name;
	
	@Column( length = 200 )
	private String full_name;
	
	@Column( length = 300 )
	private String comp_name;
	
	@Column
	private Long id_area;
	
	@Column( length = 2 )
	private String state;
	
	@Column( length = 2 )
	private String state_sale;
	
	@Column( length = 400 )
	private String stop_reason;
	
	@Column( length = 50 )
	private String id_user_sale;
	
	@Column( length = 50 )
	private String id_user_sale_tmp;
	
	@Column( length = 300 )
	private String ip;
	
	@Column( length = 50 )
	private String link_man;
	
	@Column( length = 11 )
	private String mobile;
	
	@Column( length = 100 )
	private String email;
	
	@Column( length = 200 )
	private String address_info;
	
	@Column( length = 6 )
	private String zipcode;
	
	@Column( length = 20 )
	private String phone;
	
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
	public String getId_account_cust() {
		return id_account_cust;
	}
	public void setId_account_cust(String id_account_cust) {
		this.id_account_cust = id_account_cust;
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
	public String getComp_name() {
		return comp_name;
	}
	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}
	public Long getId_area() {
		return id_area;
	}
	public void setId_area(Long id_area) {
		this.id_area = id_area;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getState_sale() {
		return state_sale;
	}
	public void setState_sale(String state_sale) {
		this.state_sale = state_sale;
	}
	public String getStop_reason() {
		return stop_reason;
	}
	public void setStop_reason(String stop_reason) {
		this.stop_reason = stop_reason;
	}
	public String getId_user_sale() {
		return id_user_sale;
	}
	public void setId_user_sale(String id_user_sale) {
		this.id_user_sale = id_user_sale;
	}
	public String getId_user_sale_tmp() {
		return id_user_sale_tmp;
	}
	public void setId_user_sale_tmp(String id_user_sale_tmp) {
		this.id_user_sale_tmp = id_user_sale_tmp;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLink_man() {
		return link_man;
	}
	public void setLink_man(String link_man) {
		this.link_man = link_man;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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


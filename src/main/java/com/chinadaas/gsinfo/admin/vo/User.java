package com.chinadaas.gsinfo.admin.vo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: Jul 10, 2014 10:57:33 AM<br>
 * @author 开发者真实姓名[HuPeng]
 */
@Entity
@Table(name = "T_USER")
public class User extends BaseEntity{

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 50)
//	@SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER") 
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_USER")
	private String id;
	
	@Column(name = "ID_ORG", length = 50)
	private String id_org;
	
	@Column(name = "ID_CUSTOMER", length = 50)
	private String id_customer;

	@Column(name = "PASSWD", length = 20)
	private String passwd;
	
	@Column(name = "USER_NAME", length = 20)
	private String user_name;
	
	@Column(name = "STATE", length = 2)
	private String state;
	
	@Column(name = "STOP_REASON", length = 400)
	private String stop_reason;

	@Column(name = "ID_AREA")
	private Integer id_area;

	@Column(name = "MOBILE", length = 11)
	private String mobile;

	@Column(name = "EMAIL", length = 100)
	private String email;

	@Column(name = "ADDRESS_INFO", length = 200)
	private String address_info;

	@Column(name = "ZIPCODE", length = 6)
	private String zipcode;

	@Column(name = "PHONE", length = 20)
	private String phone;

	@Column(name = "FAX", length = 20)
	private String fax;

	@Column(name = "LOGIN_CNT")
	private BigDecimal login_cnt;

	@Column(name = "LAST_LOGIN_TIME")
	private Date last_login_time;
	
	@Column(name = "LAST_LOGIN_IP", length = 100)
	private String last_login_ip;
	
	@Column(name = "ONLINE_TIMES", length = 20)
	private BigDecimal online_times;
	
	@Column(name = "IS_NEW_REG", length = 2)
	private String is_new_reg;
	
	@Column(name = "ID_USER_CREATE", length = 50)
	private String creator;
	
	@Column(name = "CREATE_TIME")
	private Date create_time;
	
	@Column(name = "ID_USER_MODIFY", length = 50)
	private String modifier;
	
	@Column(name = "MODIFY_TIME")
	private Date modify_time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getId_org() {
		return id_org;
	}

	public void setId_org(String id_org) {
		this.id_org = id_org;
	}

	public String getId_customer() {
		return id_customer;
	}

	public void setId_customer(String id_customer) {
		this.id_customer = id_customer;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStop_reason() {
		return stop_reason;
	}

	public void setStop_reason(String stop_reason) {
		this.stop_reason = stop_reason;
	}

	public Integer getId_area() {
		return id_area;
	}

	public void setId_area(Integer id_area) {
		this.id_area = id_area;
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

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public BigDecimal getLogin_cnt() {
		return login_cnt;
	}

	public void setLogin_cnt(BigDecimal login_cnt) {
		this.login_cnt = login_cnt;
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

	public BigDecimal getOnline_times() {
		return online_times;
	}

	public void setOnline_times(BigDecimal online_times) {
		this.online_times = online_times;
	}

	public String getIs_new_reg() {
		return is_new_reg;
	}

	public void setIs_new_reg(String is_new_reg) {
		this.is_new_reg = is_new_reg;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
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

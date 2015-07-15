package com.chinadaas.gsinfo.admin.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UKey实体类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: 使用注解的方式配置orm<br>
 * date: 2014年7月8日 下午5:46:18<br>
 * @author 开发者真实姓名[裔传洲]
 */
@Entity
@Table(name="T_UKEY")
public class Ukey extends BaseEntity{
	
	@Id
	@Column(name = "U_ID", unique = true, nullable = false, length = 50)
//	@GeneratedValue( strategy = GenerationType.SEQUENCE )
	private String id;
	@Column(name = "UKEY_NO", length = 20)
	private String ukey_no;
	@Column(name = "PIN", length = 50)
	private String pin;
	@Column(name = "ID_USER_USE", length = 50)
	private String user_id;
	@Column(name = "IS_ADMIN", length = 2)
	private String is_admin;
	@Column(name = "IS_ADMIN_ORG", length = 2)
	private String is_admin_org;
	@Column(name = "IS_FREE", length = 2)
	private String is_free;
	@Column(name = "BAND_IP", length = 500)
	private String band_ip;
	@Column(name = "STATE", length = 2)
	private String state;
	@Column(name = "PUBLIC_KEY", length = 4000)
	private String public_key;
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
	
	public String getUkey_no() {
		return ukey_no;
	}
	public void setUkey_no(String ukey_no) {
		this.ukey_no = ukey_no;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getIs_admin() {
		return is_admin;
	}
	public void setIs_admin(String is_admin) {
		this.is_admin = is_admin;
	}
	public String getIs_admin_org() {
		return is_admin_org;
	}
	public void setIs_admin_org(String is_admin_org) {
		this.is_admin_org = is_admin_org;
	}
	public String getIs_free() {
		return is_free;
	}
	public void setIs_free(String is_free) {
		this.is_free = is_free;
	}
	public String getBand_ip() {
		return band_ip;
	}
	public void setBand_ip(String band_ip) {
		this.band_ip = band_ip;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPublic_key() {
		return public_key;
	}
	public void setPublic_key(String public_key) {
		this.public_key = public_key;
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

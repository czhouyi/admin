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
 * date: Jul 9, 2014 5:20:22 PM<br>
 * @author 开发者真实姓名[HuPeng]
 */
@Entity
@Table(name="T_MENU")
public class Menu extends BaseEntity{
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 50)
	@GeneratedValue( strategy = GenerationType.SEQUENCE )
	private String id;
	
	@Column(length = 50)
	private String pid;
	
	@Column(length = 100)
	private String menu_name;
	
	@Column(length = 2)
	private String order_num;
	
	@Column(length = 2)
	private String lev;
	
	@Column(length = 2)
	private String state;
	
	@Column(length = 2)
	private String is_inside;
	
	@Column(length = 200)
	private String url_info;
	
	@Column(length = 50)
	private String icon;
	
	@Column(length = 20)
	private String code_product_tpl;
	
	@Column
	private Date udt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public String getLev() {
		return lev;
	}
	public void setLev(String lev) {
		this.lev = lev;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIs_inside() {
		return is_inside;
	}
	public void setIs_inside(String is_inside) {
		this.is_inside = is_inside;
	}
	public String getUrl_info() {
		return url_info;
	}
	public void setUrl_info(String url_info) {
		this.url_info = url_info;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getCode_product_tpl() {
		return code_product_tpl;
	}
	public void setCode_product_tpl(String code_product_tpl) {
		this.code_product_tpl = code_product_tpl;
	}
	public Date getUdt() {
		return udt;
	}
	public void setUdt(Date udt) {
		this.udt = udt;
	}
}


package com.chinadaas.gsinfo.admin.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 合同实体类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: 合同实体类<br>
 * date: Jul 9, 2014 5:20:22 PM<br>
 * @author 开发者真实姓名[裔传洲]
 */
@Entity
@Table(name="T_CONTRACT")
public class Contract extends BaseEntity{
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 50)
//	@GeneratedValue( strategy = GenerationType.SEQUENCE )
	private String id;
	
	@Column(length = 200)
	private String contract_name;
	
	@Column(length = 2)
	private String contract_type;
	
	@Column(length = 50)
	private String id_customer;
	
	@Column
	private Date sign_date;
	
	@Column
	private Date effect_date;
	
	@Column
	private Date lose_date;
	
	@Column(length = 50)
	private String id_user_create;
	
	@Column
	private Date create_time;
	
	@Column(length = 2)
	private String sale_channel;
	
	@Column(length = 50)
	private String id_user_sale;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContract_name() {
		return contract_name;
	}
	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
	}
	public String getContract_type() {
		return contract_type;
	}
	public void setContract_type(String contract_type) {
		this.contract_type = contract_type;
	}
	public String getId_customer() {
		return id_customer;
	}
	public void setId_customer(String id_customer) {
		this.id_customer = id_customer;
	}
	public Date getSign_date() {
		return sign_date;
	}
	public void setSign_date(Date sign_date) {
		this.sign_date = sign_date;
	}
	public Date getEffect_date() {
		return effect_date;
	}
	public void setEffect_date(Date effect_date) {
		this.effect_date = effect_date;
	}
	public Date getLose_date() {
		return lose_date;
	}
	public void setLose_date(Date lose_date) {
		this.lose_date = lose_date;
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
	public String getSale_channel() {
		return sale_channel;
	}
	public void setSale_channel(String sale_channel) {
		this.sale_channel = sale_channel;
	}
	public String getId_user_sale() {
		return id_user_sale;
	}
	public void setId_user_sale(String id_user_sale) {
		this.id_user_sale = id_user_sale;
	}
}


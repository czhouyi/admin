package com.chinadaas.gsinfo.admin.vo;

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
 * date: Jul 10, 2014 10:57:18 AM<br>
 * @author 开发者真实姓名[HuPeng]
 */
@Entity
@Table(name="T_GROUP_CATEGORY")
public class GroupCategory extends BaseEntity{
	
	@Id
	@Column( name = "ID", unique = true, nullable = false, length=50 )
//	@GeneratedValue( strategy = GenerationType.SEQUENCE )
	private String id;
	
	@Column( length = 100 )
	private String name;
	
	@Column( length = 100 )
	private String group_codes;
	
	@Column( length = 50 )
	private String func_code;
	
	@Column
	private Long order_num;
	
	@Column( length = 50 )
	private String state;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroup_codes() {
		return group_codes;
	}
	public void setGroup_codes(String group_codes) {
		this.group_codes = group_codes;
	}
	public String getFunc_code() {
		return func_code;
	}
	public void setFunc_code(String func_code) {
		this.func_code = func_code;
	}
	public Long getOrder_num() {
		return order_num;
	}
	public void setOrder_num(Long order_num) {
		this.order_num = order_num;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	} 
	
	
}


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
 * date: Jul 10, 2014 10:39:46 AM<br>
 * @author 开发者真实姓名[HuPeng]
 */
@Entity
@Table(name="T_RUNTIME_CFG")
public class RuntimeCfg extends BaseEntity{
	
	@Id
	@Column( name = "ID", unique = true, nullable = false, length=50 )
	@GeneratedValue( strategy = GenerationType.SEQUENCE )
	private String id;
	
	@Column( length = 50 )
	private String prop_name;
	
	@Column( length = 200 )
	private String prop_value;
	
	@Column( length = 500 )
	private String prop_desc;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProp_name() {
		return prop_name;
	}
	public void setProp_name(String prop_name) {
		this.prop_name = prop_name;
	}
	public String getProp_value() {
		return prop_value;
	}
	public void setProp_value(String prop_value) {
		this.prop_value = prop_value;
	}
	public String getProp_desc() {
		return prop_desc;
	}
	public void setProp_desc(String prop_desc) {
		this.prop_desc = prop_desc;
	}
	
}


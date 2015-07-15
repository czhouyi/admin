package com.chinadaas.gsinfo.admin.vo;

import java.math.BigDecimal;
import java.util.Date;

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
 * desc: 列实体类<br>
 * date: 2014年9月1日 下午2:51:49<br>
 * @author 开发者真实姓名[裔传洲]
 */
@Entity
@Table(name = "T_COLUMN")
public class Column extends BaseEntity{
	
	@Id
	@javax.persistence.Column(name = "ID", nullable = false, length = 50)
//	@GeneratedValue( strategy = GenerationType.SEQUENCE )
	private String id;
	
	@javax.persistence.Column(name = "CODE_COLUMN_GROUP", length = 50)
	private String code_column_group;
	
	@javax.persistence.Column(name = "COL_NAME", length = 100)
	private String col_name;

	@javax.persistence.Column(name = "COL_DESC", length = 200)
	private String col_desc;

	@javax.persistence.Column(name = "ORDER_NUM")
	private Integer order_num;

	@javax.persistence.Column(name = "STATE", length = 2)
	private String state;
	
	@javax.persistence.Column(name = "PRICE")
	private BigDecimal price;

	@javax.persistence.Column(name = "UDT")
	private Date udt;
	
	@javax.persistence.Column(name = "NODE_NAME", length = 100)
	private String node_name;

	@javax.persistence.Column(name = "DEFAULT_USE_TYP", length = 2)
	private String default_use_type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode_column_group() {
		return code_column_group;
	}

	public void setCode_column_group(String code_column_group) {
		this.code_column_group = code_column_group;
	}

	public String getCol_name() {
		return col_name;
	}

	public void setCol_name(String col_name) {
		this.col_name = col_name;
	}

	public String getCol_desc() {
		return col_desc;
	}

	public void setCol_desc(String col_desc) {
		this.col_desc = col_desc;
	}

	public Integer getOrder_num() {
		return order_num;
	}

	public void setOrder_num(Integer order_num) {
		this.order_num = order_num;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getUdt() {
		return udt;
	}

	public void setUdt(Date udt) {
		this.udt = udt;
	}

	public String getNode_name() {
		return node_name;
	}

	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}

	public String getDefault_use_type() {
		return default_use_type;
	}

	public void setDefault_use_type(String default_use_type) {
		this.default_use_type = default_use_type;
	}
	
}

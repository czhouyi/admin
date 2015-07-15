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
 * date: Jul 10, 2014 10:45:04 AM<br>
 * @author 开发者真实姓名[HuPeng]
 */

@Entity
@Table(name="T_UKEY_STAT")
public class UkeyStat extends BaseEntity {
	
	@Id
	@Column( name = "ID", unique = true, nullable = false, length = 50 )
	@GeneratedValue( strategy = GenerationType.SEQUENCE )
	private String id;
	
	@Column( length = 50 )
	private String u_id;
	
	@Column
	private Long order_cnt;
	
	@Column
	private Long limit_cnt;
	
	@Column
	private Date last_login_time;
	
	@Column( length = 100 )
	private String last_login_ip;
	
	@Column
	private Date udt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public Long getOrder_cnt() {
		return order_cnt;
	}
	public void setOrder_cnt(Long order_cnt) {
		this.order_cnt = order_cnt;
	}
	public Long getLimit_cnt() {
		return limit_cnt;
	}
	public void setLimit_cnt(Long limit_cnt) {
		this.limit_cnt = limit_cnt;
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
	public Date getUdt() {
		return udt;
	}
	public void setUdt(Date udt) {
		this.udt = udt;
	}
	
	
}


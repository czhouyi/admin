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
 * date: 2014年7月9日 下午4:20:48<br>
 * @author 开发者真实姓名[Andy]
 */
@Entity
@Table(name="T_INDEX_INFO")
public class IndexInfo extends BaseEntity {
	@Id
	@Column( name = "ID", unique = true, nullable = false, length=50 )
//	@GeneratedValue( strategy = GenerationType.SEQUENCE )
	private String id;
	
	@Column( length = 2 )
	private String info_type;
	
	@Column( length = 200 )
	private String info_type_info;
	
	@Column( length = 200 )
	private String title;
	
	@Column( length = 4000 )
	private String info_content;
	
	@Column( length = 2 )
	private String status;
	
	@Column
	private Date start_time;
	
	@Column
	private Date end_time;
	
	@Column( length = 4000 )
	private String comments;
	
	@Column( length = 50 )
	private String id_user_modify;
	
	@Column
	private Date udt;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInfo_type() {
		return info_type;
	}
	public void setInfo_type(String info_type) {
		this.info_type = info_type;
	}
	public String getInfo_type_info() {
		return info_type_info;
	}
	public void setInfo_type_info(String info_type_info) {
		this.info_type_info = info_type_info;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInfo_content() {
		return info_content;
	}
	public void setInfo_content(String info_content) {
		this.info_content = info_content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getId_user_modify() {
		return id_user_modify;
	}
	public void setId_user_modify(String id_user_modify) {
		this.id_user_modify = id_user_modify;
	}
	public Date getUdt() {
		return udt;
	}
	public void setUdt(Date udt) {
		this.udt = udt;
	}
	
	
}


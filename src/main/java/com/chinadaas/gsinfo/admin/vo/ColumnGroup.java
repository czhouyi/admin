package com.chinadaas.gsinfo.admin.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: 列分组实体类<br>
 * date: 2014年9月1日 下午2:51:15<br>
 * @author 开发者真实姓名[裔传洲]
 */
@Entity
@Table(name = "T_COLUMN_GROUP")
public class ColumnGroup extends BaseEntity{
	
	@Id
	@Column(name = "CODE", nullable = false, length = 50)
	private String id;
	
	@Column(name = "COLUMN_GROUP_NAME", length = 100)
	private String column_group_name;
	
	@Column(name = "FUNC_CODE", length = 100)
	private String func_code;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getColumn_group_name() {
		return column_group_name;
	}

	public void setColumn_group_name(String column_group_name) {
		this.column_group_name = column_group_name;
	}

	public String getFunc_code() {
		return func_code;
	}

	public void setFunc_code(String func_code) {
		this.func_code = func_code;
	}

}

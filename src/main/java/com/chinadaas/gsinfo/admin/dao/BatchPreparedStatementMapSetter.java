package com.chinadaas.gsinfo.admin.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

/**
 * projectName: gsinfo30-admin<br>
 * desc: 批量更新Statement<br>
 * date: 2014年9月4日 下午3:55:45<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class BatchPreparedStatementMapSetter implements BatchPreparedStatementSetter{
	
	private List<Map> params;
	private String[] keys;
	
	public BatchPreparedStatementMapSetter(List<Map> params, String[] keys) {
		this.params = params;
		this.keys = keys;
	}

	@Override
	public int getBatchSize() {
		return params.size();
	}

	@Override
	public void setValues(PreparedStatement ps, int i) throws SQLException {
		Map param = params.get(i);
		int index = 1;
		for(String key : keys) {
			ps.setString(index, (String)param.get(key));
			index ++;
		}
	}

}


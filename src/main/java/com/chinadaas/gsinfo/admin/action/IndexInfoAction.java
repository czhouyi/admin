package com.chinadaas.gsinfo.admin.action;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.service.IService;
import com.chinadaas.gsinfo.admin.service.IndexInfoService;
import com.chinadaas.gsinfo.admin.vo.BaseEntity;
import com.chinadaas.gsinfo.admin.vo.IndexInfo;

/**
 * IndexInfo action
 * projectName: gsinfo30-admin<br>
 * desc: TODO<br>
 * date: 2014年7月8日 下午6:00:45<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class IndexInfoAction extends BizBaseAction<IndexInfo> {
	
	@Autowired
	private IndexInfoService indexInfoService;

	public void setIndexInfoService(IndexInfoService indexInfoService) {
		this.indexInfoService = indexInfoService;
	}

	@Override
	public IService getBizService() {
		return indexInfoService;
	}
	
	@Override
	protected BaseEntity createNewData() throws Exception {
		IndexInfo ii = (IndexInfo) super.createNewData();
		ii.setStart_time(new Date());
		ii.setEnd_time(new Date());
		ii.setId_user_modify(getLoginUser());
		return ii;
	}

}


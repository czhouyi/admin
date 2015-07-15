package com.chinadaas.gsinfo.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.service.ColumnGroupService;
import com.chinadaas.gsinfo.admin.service.IService;
import com.chinadaas.gsinfo.admin.util.Constants;
import com.chinadaas.gsinfo.admin.util.StringUtil;
import com.chinadaas.gsinfo.admin.vo.ColumnGroup;

/**
 * ColumnGroup action
 * projectName: gsinfo30-admin<br>
 * desc: TODO<br>
 * date: 2014年7月8日 下午6:00:45<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class ColumnGroupAction extends BizBaseAction<ColumnGroup> {
	/**
	 * 注入列分组Service
	 * @see src/main/webapp/WEB-INF/applicationContext.xml
	 */
	@Autowired
	private ColumnGroupService columnGroupService;

	public void setColumnGroupService(ColumnGroupService columnGroupService) {
		this.columnGroupService = columnGroupService;
	}

	@Override
	public IService getBizService() {
		return columnGroupService;
	}

	/**
	 * desc: 获取列分组选择树结构<br>
	 * date: 2014年8月26日 下午1:59:00<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public String tree() {
		HttpServletRequest request = getRequest();
		
		List newlist = columnGroupService.tree(request.getParameterMap());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.JSON_TOTALCOUNT, newlist.size());
		map.put(Constants.JSON_RESULT, StringUtil.toJson(newlist));

		response(true, Constants.JSON_OK, Constants.JSON_CODE_200, map);
		
		return NONE;
	}

}


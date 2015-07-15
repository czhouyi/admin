package com.chinadaas.gsinfo.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.service.ColumnService;
import com.chinadaas.gsinfo.admin.service.IService;
import com.chinadaas.gsinfo.admin.util.Constants;
import com.chinadaas.gsinfo.admin.util.StringUtil;
import com.chinadaas.gsinfo.admin.vo.Column;

/**
 * Column action
 * projectName: gsinfo30-admin<br>
 * desc: Column action<br>
 * date: 2014年7月8日 下午6:00:45<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class ColumnAction extends BizBaseAction<Column> {
	/**
	 * 注入列Service
	 * @see src/main/webapp/WEB-INF/applicationContext.xml
	 */
	@Autowired
	private ColumnService columnService;

	public void setColumnService(ColumnService columnService) {
		this.columnService = columnService;
	}

	@Override
	public IService getBizService() {
		return columnService;
	}
	
	/**
	 * desc: 构建二级树json：字段组别-字段，输入参数：用户ID，产品ID<br>
	 * date: 2014年8月21日 上午10:30:44<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public String tree() {
		HttpServletRequest request = getRequest();
		String tplCode = request.getParameter("tplCode");
		String userid = request.getParameter("userid");
		if(StringUtil.isNull(tplCode) || StringUtil.isNull(userid)) {
			response(false, "需要提供参数：{tplCode}{userid}", Constants.JSON_CODE_200);
			return NONE;
		}
		
		List newlist = columnService.tree(request.getParameterMap());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.JSON_TOTALCOUNT, newlist.size());
		map.put(Constants.JSON_RESULT, StringUtil.toJson(newlist));

		response(true, Constants.JSON_OK, Constants.JSON_CODE_200, map);
		
		return NONE;
	}

}


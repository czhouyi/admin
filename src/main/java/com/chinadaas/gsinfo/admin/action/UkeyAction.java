package com.chinadaas.gsinfo.admin.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.service.IService;
import com.chinadaas.gsinfo.admin.service.UkeyService;
import com.chinadaas.gsinfo.admin.util.Constants;
import com.chinadaas.gsinfo.admin.util.Enum.UkeyState;
import com.chinadaas.gsinfo.admin.util.StringUtil;
import com.chinadaas.gsinfo.admin.vo.BaseEntity;
import com.chinadaas.gsinfo.admin.vo.Ukey;

/**
 * Ukey action
 * projectName: gsinfo30-admin<br>
 * desc: Ukey Action<br>
 * date: 2014年7月8日 下午6:00:45<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class UkeyAction extends BizBaseAction<Ukey> {
	/**
	 * 注入Ukey Service
	 * @see src/main/webapp/WEB-INF/applicationContext.xml
	 */
	@Autowired
	private UkeyService ukeyService;

	public void setUkeyService(UkeyService ukeyService) {
		this.ukeyService = ukeyService;
	}

	@Override
	public IService getBizService() {
		return ukeyService;
	}
	
	public String assign() {
		HttpServletRequest request = getRequest();
		String ukeyStr = request.getParameter("UKEY").trim().replaceAll("\n", "").replaceAll("\r", "");
		String ip = request.getParameter("BANDIP");
		
		if (!StringUtil.isNull(ukeyStr) && !StringUtil.isNull(ip)) {
			String[] ukeyNos = ukeyStr.split(";", -1);
			if (ukeyNos.length>0) {
				if (ukeyService.assign(ukeyNos, ip)) {
					response(true, "更新成功", Constants.JSON_CODE_200);
					return NONE;
				}
			}
		} 
		response(false, "更新失败", Constants.JSON_CODE_200);
		return NONE;
	}
	
	@Override
	protected BaseEntity createNewData() throws Exception {
		Ukey ukey = (Ukey) super.createNewData();
		ukey.setCreate_time(new Date());
		ukey.setCreator(getLoginUser());
		ukey.setState(UkeyState.ENABLE);
		ukey.setPin("1E3A028086C4DE11");
		return ukey;
	}

}
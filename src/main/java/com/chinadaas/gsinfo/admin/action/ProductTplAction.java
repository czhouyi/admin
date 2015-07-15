package com.chinadaas.gsinfo.admin.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.exception.BizException;
import com.chinadaas.gsinfo.admin.service.IService;
import com.chinadaas.gsinfo.admin.service.ProductTplService;
import com.chinadaas.gsinfo.admin.util.Constants;
import com.chinadaas.gsinfo.admin.vo.BaseEntity;
import com.chinadaas.gsinfo.admin.vo.ProductTpl;

/**
 * ProductTpl action
 * projectName: gsinfo30-admin<br>
 * desc: TODO<br>
 * date: 2014年7月8日 下午6:00:45<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class ProductTplAction extends BizBaseAction<ProductTpl> {
	/**
	 * 注入产品模板Service
	 * @see src/main/webapp/WEB-INF/applicationContext.xml
	 */
	@Autowired
	private ProductTplService productTplService;

	public void setProductTplService(ProductTplService productTplService) {
		this.productTplService = productTplService;
	}
	
	@Override
	public IService getBizService() {
		return productTplService;
	}

	@Override
	protected BaseEntity createNewData() throws Exception {
		ProductTpl pt = (ProductTpl) super.createNewData();
		pt.setCreate_time(new Date());
		pt.setId_user_create(getLoginUser());
		return pt;
	}
	
	/**
	 * desc: 分配产品模板的字段组别<br>
	 * date: 2014年8月26日 下午2:06:58<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public String assign() {
		HttpServletRequest request = getRequest();
		
		String tplCode = request.getParameter("tplCode");
		String assignResult = request.getParameter("assignResult");
		
		try {
			if(tplCode != null && tplCode.length() > 0 && assignResult != null){
				productTplService.assign(tplCode, assignResult);
			}
		} catch (BizException e) {
			response(false, e.getMessage(), Constants.JSON_CODE_200);
		}
		response(true, Constants.JSON_OK, Constants.JSON_CODE_200);
		return NONE;
	}

}
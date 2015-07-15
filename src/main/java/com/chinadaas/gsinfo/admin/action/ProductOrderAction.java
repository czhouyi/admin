package com.chinadaas.gsinfo.admin.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.exception.BizException;
import com.chinadaas.gsinfo.admin.service.IService;
import com.chinadaas.gsinfo.admin.service.OrderLimitService;
import com.chinadaas.gsinfo.admin.service.ProductOrderService;
import com.chinadaas.gsinfo.admin.util.Constants;
import com.chinadaas.gsinfo.admin.util.StringUtil;
import com.chinadaas.gsinfo.admin.vo.BaseEntity;
import com.chinadaas.gsinfo.admin.vo.ProductOrder;

/**
 * ProductOrder action
 * projectName: gsinfo30-admin<br>
 * desc: 产品实例Action<br>
 * date: 2014年7月8日 下午6:00:45<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class ProductOrderAction extends BizBaseAction<ProductOrder> {
	/**
	 * 注入产品实例Service
	 * @see src/main/webapp/WEB-INF/applicationContext.xml
	 */
	@Autowired
	private ProductOrderService productOrderService;
	
	@Autowired
	private OrderLimitService orderLimitService;

	public void setProductOrderService(ProductOrderService productOrderService) {
		this.productOrderService = productOrderService;
	}
	
	public void setOrderLimitService(OrderLimitService orderLimitService) {
		this.orderLimitService = orderLimitService;
	}

	@Override
	public IService getBizService() {
		return productOrderService;
	}
	
	@Override
	protected BaseEntity createNewData() throws Exception {
		ProductOrder po = (ProductOrder) super.createNewData();
		po.setCreate_time(new Date());
		po.setCreator(getLoginUser());
		return po;
	}
	
	@Override
	public String update() {
		HttpServletRequest request = getRequest();
		String idUser = request.getParameter("USERID");
		String poid = request.getParameter("POID");
		String orderLimit = request.getParameter("ORDERLIMIT");
		orderLimitService.update(idUser, poid, orderLimit);
		response(true, "保存成功", Constants.JSON_CODE_200);
		return NONE;
	}
	
	/**
	 * desc: 分配字段<br>
	 * date: 2014年8月21日 下午1:51:50<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public String assign() {
		HttpServletRequest request = getRequest();
		String poid = request.getParameter("poid");						// 产品实例ID
		String assignResult = request.getParameter("assignResult");		// column id 按逗号分隔
		String role = request.getParameter("role");
		String idUser = request.getParameter("idUser");
		String tplCode = request.getParameter("tplCode");
		try {
			if(poid != null && poid.length() > 0 && assignResult != null){
				productOrderService.assign(poid, assignResult, role, idUser, tplCode);
			}
		} catch (BizException e) {
			response(false, e.getMessage(), Constants.JSON_CODE_200);
		}

		response(true, Constants.JSON_OK, Constants.JSON_CODE_200);

		return NONE;
	}
	
	@Override
	public String save() {
		HttpServletRequest request = getRequest();
		String tplCode = request.getParameter("TPLCODE");
		String ukeyNo = request.getParameter("UKEYNO");
		String orderLimit = request.getParameter("ORDERLIMIT");
		
		if(StringUtil.isNull(tplCode) || StringUtil.isNull(ukeyNo) || StringUtil.isNull(orderLimit)) {
			response(false, "需要参数{tplCode}{userid}{orderLimit}", Constants.JSON_CODE_200);
			return NONE;
		}
		try {
			productOrderService.saveAndAssign(tplCode, ukeyNo, orderLimit);
		} catch (BizException e) {
			response(false, e.getMessage(), Constants.JSON_CODE_200);
		}
		
		response(true, Constants.JSON_OK, Constants.JSON_CODE_200);
		
		return NONE;
	}
	
	@Override
	public String delete() {
		HttpServletRequest request = getRequest();
		String poid = request.getParameter("POID");
		String userid = request.getParameter("USERID");
		
		if(StringUtil.isNull(poid) || StringUtil.isNull(userid)) {
			response(false, "需要参数{poid}{userid}", Constants.JSON_CODE_200);
			return NONE;
		}
		try {
			productOrderService.deleteObjAndAssign(poid, userid);
		} catch (BizException e) {
			response(false, e.getMessage(), Constants.JSON_CODE_200);
		}
		
		response(true, Constants.JSON_OK, Constants.JSON_CODE_200);
		
		return NONE;
	}
	
}


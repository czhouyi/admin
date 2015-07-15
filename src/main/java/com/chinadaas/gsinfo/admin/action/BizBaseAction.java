package com.chinadaas.gsinfo.admin.action;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinadaas.gsinfo.admin.exception.BizException;
import com.chinadaas.gsinfo.admin.service.IService;
import com.chinadaas.gsinfo.admin.util.Constants;
import com.chinadaas.gsinfo.admin.util.PropertyUtil;
import com.chinadaas.gsinfo.admin.util.StringUtil;
import com.chinadaas.gsinfo.admin.vo.BaseEntity;

/**
 * action业务基类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: 主要用于实现增删改查功能模板<br>
 * date: 2014年7月8日 下午5:42:25<br>
 * @author 开发者真实姓名[裔传洲]
 */
public abstract class BizBaseAction<T extends BaseEntity> extends BaseAction {
	
	private static final Logger logger = LoggerFactory.getLogger(BizBaseAction.class);
	
	/**
	 * 获取当前类的实际泛型
	 * desc: 获取当前类的实际泛型<br>
	 * date: 2014年8月15日 下午1:30:39<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String getGenericClassName() {
		Class<T> beanClass = null;
		Type type = getClass().getGenericSuperclass();
		Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
		beanClass = (Class<T>) trueType;
	
		return beanClass.getName();
	}
	
	/**
	 * 通用list查询 - 包含全部查询和分页查询
	 * desc: 通用list查询 - 包含全部查询和分页查询<br>
	 * date: 2014年7月9日 下午2:12:42<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public String list() {
		HttpServletRequest request = getRequest();

		List<Map<String, Object>> rslist = null;
		long totalCount = 0l;
		try {
			rslist = getBizService().list(request.getParameterMap());
			totalCount = getBizService().listCount(request.getParameterMap());
		} catch (BizException e) {
			logger.error(e.getMessage());
			response(false, e.getMessage(), Constants.JSON_CODE_200);
			return NONE;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.JSON_TOTALCOUNT, totalCount);
		map.put(Constants.JSON_RESULT, StringUtil.toJson(rslist));

		response(true, Constants.JSON_OK, Constants.JSON_CODE_200, map);

		return NONE;
	}
	
	/**
	 * desc: 通用select查询 - 自定义查询<br>
	 * date: 2014年8月18日 下午6:44:28<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public String select() {
		HttpServletRequest request = getRequest();
		
		List<Map<String, Object>> rslist = null;
		try {
			rslist = getBizService().select(request.getParameterMap());
		} catch (BizException e) {
			logger.error(e.getMessage());
			response(false, e.getMessage(), Constants.JSON_CODE_200);
			return NONE;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.JSON_TOTALCOUNT, rslist.size());
		map.put(Constants.JSON_RESULT, StringUtil.toJson(rslist));

		response(true, Constants.JSON_OK, Constants.JSON_CODE_200, map);
		
		return NONE;
	}
	
	/**
	 * 通用保存方法
	 * desc: 通用保存方法<br>
	 * date: 2014年7月9日 下午2:14:15<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public String save() {
		HttpServletRequest request = getRequest();
		
		Map params = request.getParameterMap();
		
		BaseEntity entity = null;
		try {
			entity = parseObject(params);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response(false, e.getMessage(), Constants.JSON_CODE_200);
			return NONE;
		}
		
		if (entity != null) {
			try {
				doSave(entity, params);
			} catch (BizException e) {
				logger.error(e.getMessage());
				response(false, e.getMessage(), Constants.JSON_CODE_200);
				return NONE;
			}
			response(true, "保存成功", Constants.JSON_CODE_200);
		}
		
		return NONE;
	}
	
	/**
	 * desc: 真正的保存方法<br>
	 * date: 2014年11月14日 下午1:56:56<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param entity
	 * @throws BizException
	 */
	protected void doSave(BaseEntity entity, Map params) throws BizException {
		getBizService().save(entity, params);
	}
	
	/**
	 * 通用更新方法
	 * desc: 通用更新方法<br>
	 * date: 2014年7月9日 下午2:14:15<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public String update() {
		HttpServletRequest request = getRequest();
		
		Map params = request.getParameterMap();
		
		String id = request.getParameter("ID");
		
		BaseEntity entity = null;
		if(id != null && id.length() > 0) {
			entity = getBizService().getById(id);
		} else {
			response(false, "ID值不存在", Constants.JSON_CODE_200);
			return NONE;
		}
		try {
			entity = parseUpdateObject(entity, params);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response(false, e.getMessage(), Constants.JSON_CODE_200);
			return NONE;
		}
		
		if (entity != null) {
			try {
				doUpdate(entity, params);
			} catch (BizException e) {
				logger.error(e.getMessage());
				response(false, e.getMessage(), Constants.JSON_CODE_200);
				return NONE;
			}
			response(true, "保存成功", Constants.JSON_CODE_200);
		}
		return NONE;
	}
	
	/**
	 * desc: 真正的更新方法<br>
	 * date: 2014年11月14日 下午1:57:20<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param entity
	 * @throws BizException
	 */
	protected void doUpdate(BaseEntity entity, Map params) throws BizException {
		getBizService().update(entity, params);
	}
	
	/**
	 * 新增业务表记录时设置默认值
	 * desc: 新增业务表记录时设置默认值<br>
	 * date: 2014年8月15日 下午1:28:09<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 * @throws Exception
	 */
	protected BaseEntity createNewData() throws Exception {
		Class clazz = Class.forName(getGenericClassName());
		return (BaseEntity) clazz.newInstance();
	}
	
	/**
	 * 在保存或更新记录时从请求里获取参数并转化成对象<br>
	 * desc: 在保存或更新记录时从请求里获取参数并转化成对象<br>
	 * date: 2014年8月15日 下午1:28:42<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param params
	 * @return
	 * @throws Exception
	 */
	protected BaseEntity parseObject(Map params) throws Exception {
		Class clazz = Class.forName(getGenericClassName());
		BaseEntity entity = createNewData();
		List<String> fields = new ArrayList<String>();
		for(Field f : clazz.getDeclaredFields()) {
			fields.add(f.getName());
		}
		for (Iterator iterator = params.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry) iterator.next();
			String key = StringUtil.getString(entry.getKey());
			String property = key.toLowerCase();
			if(fields.contains(property)) {
				PropertyUtil.set(entity, property, getRequest().getParameter(key));
			}
		}
		
		return entity;
	}
	
	/**
	 * 在保存或更新记录时从请求里获取参数并转化成对象<br>
	 * desc: 在保存或更新记录时从请求里获取参数并转化成对象<br>
	 * date: 2014年8月15日 下午1:28:42<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param entity
	 * @param params
	 * @return
	 * @throws Exception
	 */
	protected BaseEntity parseUpdateObject(BaseEntity entity, Map params) throws Exception {
		Class clazz = Class.forName(getGenericClassName());
		if(entity == null) {
			entity = createNewData();
		}
		List<String> fields = new ArrayList<String>();
		for(Field f : clazz.getDeclaredFields()) {
			fields.add(f.getName());
		}
		for (Iterator iterator = params.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry) iterator.next();
			String key = StringUtil.getString(entry.getKey());
			String property = key.toLowerCase();
			if(fields.contains(property)) {
				PropertyUtil.set(entity, property, getRequest().getParameter(key));
			}
		}
		
		return entity;
	}
	
	/**
	 * desc: 通用删除方法<br>
	 * date: 2014年7月9日 下午2:14:15<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public String delete() {
		HttpServletRequest request = getRequest();
		
		String id = request.getParameter("id");
		if (id == null || id.length() <= 0) {
			response(false, "parameter id is needed.", Constants.JSON_CODE_200);
			return NONE;
		}
		
		try {
			doDelete(id);
		} catch (BizException e) {
			logger.error(e.getMessage());
			response(false, e.getMessage(), Constants.JSON_CODE_200);
			return NONE;
		}
		
		response(true, "删除成功", Constants.JSON_CODE_200);

		return NONE;
	}
	
	/**
	 * desc: 真正的删除方法<br>
	 * date: 2014年11月14日 下午1:57:42<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param id
	 * @throws BizException
	 */
	protected void doDelete(String id) throws BizException {
		getBizService().delete(id);
	}
	
	/**
	 * 获取实际的service对象Hook
	 * desc: 此方法在子类里通过spring注入来实现<br>
	 * date: 2014年8月15日 下午1:29:29<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public abstract IService getBizService();

}

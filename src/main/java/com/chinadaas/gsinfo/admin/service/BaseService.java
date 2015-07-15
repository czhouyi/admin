package com.chinadaas.gsinfo.admin.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinadaas.gsinfo.admin.dao.IDAO;
import com.chinadaas.gsinfo.admin.exception.BizException;
import com.chinadaas.gsinfo.admin.util.Constants;
import com.chinadaas.gsinfo.admin.util.PropertyUtil;
import com.chinadaas.gsinfo.admin.util.StringUtil;
import com.chinadaas.gsinfo.admin.vo.BaseEntity;

/**
 * service通用模板实现类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: service通用模板接口实现类，实现所有在接口模板中定义的接口方法<br>
 * date: 2014年7月8日 下午2:48:32<br>
 * @author 开发者真实姓名[裔传洲]
 * @see com.chinadaas.gsinfo.admin.service.IService
 */
@Service
public abstract class BaseService<T extends BaseEntity> implements IService<T>{
	
	private static final Logger logger = LoggerFactory.getLogger(BaseService.class);
	
	/**
	 * Hook of DAO
	 * desc: 数据访问接口钩子<br>
	 * date: 2014年7月10日 下午3:42:06<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	protected abstract IDAO<T> getDao();

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list() {
		return (List<T>) this.getDao().all();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list(int start, int limit) {
		return (List<T>) this.getDao().all(start, limit);
	}
	
	@Override
	public List<Map<String, Object>> list(Map param) throws BizException {
		String sql = getListSQL(param);
		
		if(StringUtil.isNull(sql)) {
			throw new BizException("List SQL is not set.");
		}
		
		String start = StringUtil.getParam(param, Constants.PARAM_START);
		String limit = StringUtil.getParam(param, Constants.PARAM_LIMIT);
		
		List<Map<String, Object>> rslist = null;
		boolean paged = start != null && limit.length() > 0 && start != null && limit.length() > 0;

		if(paged) {
			rslist = execSQLQuery(sql, Integer.valueOf(start), Integer.valueOf(limit));
		} else {
			rslist = execSQLQuery(sql);
		}
		
		return rslist;
	}
	
	@Override
	public long listCount(Map param) throws BizException {
		String sql = getListSQL(param);
		
		if(StringUtil.isNull(sql)) {
			throw new BizException("List SQL is not set.");
		}
		
		return getCount(sql);
	}

	@Override
	public List<Map<String, Object>> select(Map param) throws BizException {
		String sql = getSelectSQL(param);

		if(StringUtil.isNull(sql)) {
			throw new BizException("Select SQL is not set.");
		}

		return execSQLQuery(sql.toString());
	}
	
	/**
	 * desc: 获取列表的sql语句，这是一个钩子<br>
	 * date: 2014年11月14日 下午1:58:09<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param param
	 * @return
	 */
	protected abstract String getListSQL(Map param);
	
	/**
	 * desc: 获取选择列表的sql语句，这是一个钩子<br>
	 * date: 2014年11月14日 下午1:58:50<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param param
	 * @return
	 */
	protected abstract String getSelectSQL(Map param);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> filter(String hql, Object... values) {
		return (List<T>) this.getDao().filter(hql, values);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> filter(int start, int limit, String hql) {
		return (List<T>) this.getDao().filter(hql, start, limit);
	}

	@Override
	public String save(T instance) throws BizException {
		checkBeforeSave(instance, null);
		String id = (String) this.getDao().save(instance);
		doAfterSave(id, null);
		return id;
	}
	
	@Override
	public String save(T instance, Map param) throws BizException {
		checkBeforeSave(instance, param);
		String id = (String) this.getDao().save(instance);
		doAfterSave(id, param);
		return id;
	}
	
	/**
	 * desc: 通用保存校验:<br>
	 * 1.id不能为空<br>
	 * 2.id不能重复<br>
	 * date: 2014年8月26日 上午10:02:26<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param instance
	 * @throws BizException
	 */
	protected void checkBeforeSave(T instance, Map param) throws BizException {
		try {
			String id = (String) PropertyUtil.get(instance, "id");
//			if(id == null || id.length() <= 0) {
//				throw new BizException("Error occurs while saving "+instance.getClass().getSimpleName()+" : ID未指定");
//			}
			if(id != null && id.length() > 0 && getById(id) != null) {
				throw new BizException("Error occurs while saving "+instance.getClass().getSimpleName()+" : ID已存在[id="+id+"]");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException(e.getMessage());
		}
	}
	
	/**
	 * desc: 通用保存后动作:<br>
	 * date: 2014年8月26日 上午10:03:45<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param id
	 * @throws BizException
	 */
	protected void doAfterSave(String id, Map param) throws BizException {
		
	}
	
	@Override
	public void update(T instance) throws BizException {
		checkBeforeUpdate(instance, null);
		this.getDao().update(instance);
		doAfterUpdate(instance, null);
	}
	
	@Override
	public void update(T instance, Map param) throws BizException {
		checkBeforeUpdate(instance, param);
		this.getDao().update(instance);
		doAfterUpdate(instance, param);
	}
	
	/**
	 * desc: 通用更新校验:<br>
	 * 1.id不能为空<br>
	 * 2.id必须已存在<br>
	 * date: 2014年8月26日 上午10:04:05<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param instance
	 * @throws BizException
	 */
	protected void checkBeforeUpdate(T instance, Map param) throws BizException {
		try {
			String id = (String) PropertyUtil.get(instance, "id");
			if(id == null || id.length() <= 0) {
				throw new BizException("Error occurs while updating "+instance.getClass().getSimpleName()+" : ID未指定");
			}
			if(getById(id) == null) {
				throw new BizException("Error occurs while updating "+instance.getClass().getSimpleName()+" : ID不存在[id="+id+"]");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException(e.getMessage());
		}
	}
	
	/**
	 * desc: 通用更新后动作:<br>
	 * date: 2014年8月26日 上午10:03:45<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param id
	 * @throws BizException
	 */
	protected void doAfterUpdate(T instance, Map param) throws BizException {
		
	}

	@Override
	public void delete(String id) throws BizException {
		checkBeforeDelete(id);
		this.getDao().delete(id);
		doAfterDelete(id);
	}
	
	/**
	 * desc: 通用删除校验<br>
	 * date: 2014年8月26日 上午10:07:11<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param id
	 * @throws BizException
	 */
	protected void checkBeforeDelete(String id) throws BizException {}
	
	/**
	 * desc: 通用删除校验<br>
	 * date: 2014年8月26日 上午10:07:33<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param instance
	 * @throws BizException
	 */
	protected void checkBeforeDelete(T instance) throws BizException {}
	
	/**
	 * desc: 删除后动作<br>
	 * date: 2014年9月4日 下午5:39:29<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param id
	 * @throws BizException
	 */
	protected void doAfterDelete(String id) throws BizException {}
	
	/**
	 * desc: 删除后动作<br>
	 * date: 2014年9月4日 下午5:39:43<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param instance
	 * @throws BizException
	 */
	protected void doAfterDelete(T instance) throws BizException {}

	@Override
	public void delete(T instance) throws BizException {
		checkBeforeDelete(instance);
		this.getDao().delete(instance);
		doAfterDelete(instance);
	}

	@Override
	public boolean exist(String hql) {
		return this.getDao().exist(hql);
	}
	
	@Override
	public T getById(String id) {
		return this.getDao().getObjectById(id);
	}

	@Override
	public T get(String hql) {
		return this.getDao().getObjectByHQL(hql);
	}
	
	@Override
	public long getTotalCount() {
		return this.getDao().getTotalCount();
	}
	
	@Override
	public long getTotalCount(String hql, Object... values) {
		return this.getDao().getTotalCount(hql, values);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List execSQLQuery(String sql) {
		return this.getDao().execSQLQuery(sql);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List execSQLQuery(String sql, Object... params) {
		return this.getDao().execSQLQuery(sql, params);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List execSQLQuery(String sql, int start, int limit) {
		return this.getDao().execSQLQuery(sql, start, limit);
	}
	
	@Override
	public void execSQLUpdate(String sql) {
		this.getDao().execSQLUpdate(sql);
	}
	
	@Override
	public void execSQLUpdate(String sql, Object... params) {
		this.getDao().execSQLUpdate(sql, params);
	}
	
	@Override
	public int getCount(String sql) {
		return this.getDao().getCount(sql);
	}

}

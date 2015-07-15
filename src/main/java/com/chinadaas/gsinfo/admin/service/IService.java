package com.chinadaas.gsinfo.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinadaas.gsinfo.admin.exception.BizException;
import com.chinadaas.gsinfo.admin.vo.BaseEntity;

/**
 * service通用模板接口类<br>
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: service通用模板接口类，定义所有子类都会用到的接口方法，并预先实现<br>
 * date: 2014年7月8日 下午2:48:32<br>
 * @author 开发者真实姓名[裔传洲]
 * @see com.chinadaas.gsinfo.admin.service.BaseService
 * @param <T>
 */
@Service
public interface IService<T extends BaseEntity> {

	/**
	 * 获取查询列表，返回的是对象列表
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午1:59:43<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	@Transactional
	public List<T> list();
	
	/**
	 * 获取分页查询列表，返回的是对象列表
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午2:01:01<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param start
	 * @param limit
	 * @return
	 */
	@Transactional
	public List<T> list(int start, int limit);
	
	/**
	 * 获取查询列表，返回的是字典列表
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午1:59:43<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param param
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> list(Map param) throws BizException;
	
	/**
	 * 获取查询列表总数
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午1:59:43<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param param
	 * @return
	 */
	@Transactional
	public long listCount(Map param) throws BizException;
	
	/**
	 * 获取选择查询列表，返回的是字典列表
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午2:01:01<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param param
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> select(Map param) throws BizException;
	
	/**
	 * 获取带过滤的查询列表
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午2:01:18<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param hql
	 * @param values
	 * @return
	 */
	@Transactional
	public List<T> filter(String hql, Object... values);
	
	/**
	 * 获取带过滤分页查询列表
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午2:01:36<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param start
	 * @param limit
	 * @param hql
	 * @return
	 */
	@Transactional
	public List<T> filter(int start, int limit, String hql);
	
	/**
	 * 保存实体对象
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午2:01:54<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param instance
	 * @throws BizException
	 */
	@Transactional
	public String save(T instance) throws BizException ;
	
	/**
	 * 保存实体对象
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午2:01:54<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param instance
	 * @throws BizException
	 */
	@Transactional
	public String save(T instance, Map param) throws BizException ;
	
	/**
	 * 更新实体对象
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午2:02:11<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param instance
	 * @throws BizException
	 */
	@Transactional
	public void update(T instance) throws BizException ;
	
	/**
	 * 更新实体对象
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午2:02:11<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param instance
	 * @throws BizException
	 */
	@Transactional
	public void update(T instance, Map param) throws BizException ;
	
	/**
	 * 按主键删除实体对象
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午2:02:22<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param id
	 * @throws BizException
	 */
	@Transactional
	public void delete(String id) throws BizException ;
	
	/**
	 * 删除实体对象
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午2:02:38<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param instance
	 * @throws BizException
	 */
	@Transactional
	public void delete(T instance) throws BizException ;
	
	/**
	 * 判断HQL语句是否存在记录
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午2:02:54<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param hql
	 * @return
	 */
	@Transactional
	public boolean exist(String hql);
	
	/**
	 * 根据主键获取对象
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午2:03:15<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param id
	 * @return
	 */
	@Transactional
	public T getById(String id);
	
	/**
	 * 根据HQL获取对象
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午2:03:29<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param hql
	 * @return
	 */
	@Transactional
	public T get(String hql);
	
	/**
	 * desc: 获取总记录条数<br>
	 * date: 2014年8月15日 下午2:03:44<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	@Transactional
	public long getTotalCount();
	
	/**
	 * desc: 获取HQL查询记录条数<br>
	 * date: 2014年8月15日 下午2:04:00<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param hql
	 * @param values
	 * @return
	 */
	@Transactional
	public long getTotalCount(String hql, Object... values);
	
	/**
	 * desc: 执行查询SQL<br>
	 * date: 2014年8月18日 上午10:02:49<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param sql
	 * @return
	 */
	@Transactional
	public List execSQLQuery(String sql);
	
	/**
	 * desc: 执行查询SQL<br>
	 * date: 2014年8月18日 上午10:02:49<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param sql
	 * @param params
	 * @return
	 */
	@Transactional
	public List execSQLQuery(String sql, Object... params);
	
	/**
	 * desc: 执行查询分页SQL<br>
	 * date: 2014年8月18日 上午10:38:18<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param sql
	 * @param start
	 * @param limit
	 * @return
	 */
	@Transactional
	public List execSQLQuery(String sql, int start, int limit);
	
	/**
	 * desc: 执行数据更新SQL<br>
	 * date: 2014年8月18日 上午10:02:55<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param sql
	 */
	@Transactional
	public void execSQLUpdate(String sql);
	
	/**
	 * desc: 执行数据更新SQL<br>
	 * date: 2014年8月18日 上午10:02:55<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param sql
	 * @param params
	 */
	@Transactional
	public void execSQLUpdate(String sql, Object... params);
	
	/**
	 * desc: 获取SQL返回记录条数<br>
	 * date: 2014年8月18日 上午11:05:44<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param sql
	 * @return
	 */
	@Transactional
	public int getCount(String sql);
}

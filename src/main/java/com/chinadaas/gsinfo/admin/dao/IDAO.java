package com.chinadaas.gsinfo.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.chinadaas.gsinfo.admin.vo.BaseEntity;

/**
 * 持久化层公共模板接口
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: 持久化层公共模板接口类，定义并预先实现所有子类通用的方法<br>
 * date: 2014年7月8日 下午1:36:07<br>
 * @author 开发者真实姓名[裔传洲]
 * @see com.chinadaas.gsinfo.admin.dao.BaseDAO
 * @param <T>
 */
public interface IDAO<T extends BaseEntity> {
	
	/**
	 * 保存对象
	 * desc: TODO<br>
	 * date: 2014年7月8日 下午1:36:56<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param instance
	 */
	public String save(T instance) throws DataAccessException;
	
	/**
	 * 更新对象
	 * desc: TODO<br>
	 * date: 2014年7月8日 下午1:37:10<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param instance
	 */
	public void update(T instance) throws DataAccessException;
	
	/**
	 * 保存或更新对象
	 * desc: TODO<br>
	 * date: 2014年7月8日 下午1:37:10<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param instance
	 */
	public void saveOrUpdate(T instance) throws DataAccessException;
	
	/**
	 * 删除对象
	 * desc: TODO<br>
	 * date: 2014年7月8日 下午1:38:05<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param instance
	 */
	public void delete(T instance) throws DataAccessException;
	
	/**
	 * 按主键删除对象
	 * desc: TODO<br>
	 * date: 2014年7月8日 下午1:38:21<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param id
	 */
	public void delete(String id) throws DataAccessException;
	
	/**
	 * 获取对象全体集合
	 * desc: TODO<br>
	 * date: 2014年7月8日 下午1:38:56<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public List<?> all() throws DataAccessException;
	
	/**
	 * 获取对象集合 - 带过滤条件
	 * desc: TODO<br>
	 * date: 2014年7月8日 下午1:38:56<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param hql
	 * @param values
	 * @return
	 */
	public List<?> filter(String hql, Object... values) throws DataAccessException;
	
	/**
	 * 获取分页对象集合
	 * desc: TODO<br>
	 * date: 2014年7月8日 下午1:40:08<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<?> all(int pageNo, int pageSize);
	
	/**
	 * 获取分页对象集合 - 带过滤条件
	 * desc: TODO<br>
	 * date: 2014年7月8日 下午1:40:08<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<?> filter(String hql, int pageNo, int pageSize);
	
	/**
	 * 根据ID获取对象
	 * desc: TODO<br>
	 * date: 2014年7月8日 下午1:42:22<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param id
	 * @return
	 */
	public T getObjectById(String id) throws DataAccessException;
	
	/**
	 * 根据HQL获取对象
	 * desc: TODO<br>
	 * date: 2014年7月8日 下午1:42:26<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param hql
	 * @return
	 */
	public T getObjectByHQL(String hql, Object... values) throws DataAccessException;
	
	/**
	 * 是否存在对象
	 * desc: TODO<br>
	 * date: 2014年7月8日 下午1:43:02<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param hql
	 * @param values
	 * @return
	 */
	public boolean exist(String hql, Object... values) throws DataAccessException;
	
	/**
	 * 获取实体表的总记录条数
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午1:57:07<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public long getTotalCount();
	
	/**
	 * 获取按hql查询的总记录条数
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午1:57:42<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param hql
	 * @param values
	 * @return
	 */
	public long getTotalCount(String hql, Object... values);
	
	/**
	 * 执行查询SQL
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午1:57:45<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param sql
	 * @return
	 */
	public List execSQLQuery(String sql);
	
	/**
	 * 执行查询SQL
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午1:57:45<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param sql
	 * @param params
	 * @return
	 */
	public List execSQLQuery(String sql, Object... params);
	
	/**
	 * desc: 执行查询分页SQL<br>
	 * date: 2014年8月18日 上午10:36:40<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param sql
	 * @param start
	 * @param limit
	 * @return
	 */
	public List execSQLQuery(String sql, int start, int limit);
	
	/**
	 * desc: 获取sql返回记录条数<br>
	 * date: 2014年8月18日 上午11:00:25<br>
	 * @author 开发者真实姓名[Andy]
	 * @param sql
	 * @return
	 */
	public int getCount(String sql) ;
	
	/**
	 * 执行数据更新SQL
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午1:57:49<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param sql
	 */
	public void execSQLUpdate(String sql);
	
	/**
	 * 带参数执行数据更新SQL
	 * desc: TODO<br>
	 * date: 2014年8月15日 下午1:57:49<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param sql
	 * @param params
	 */
	public void execSQLUpdate(String sql, Object... params);
	
	/**
	 * desc: 批量更新SQL语句<br>
	 * date: 2014年9月4日 下午4:02:24<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param sql
	 * @param params
	 * @param keys
	 * @return
	 */
	public int[] batchUpdate(String sql, List<Map> params, String[] keys);
	
}

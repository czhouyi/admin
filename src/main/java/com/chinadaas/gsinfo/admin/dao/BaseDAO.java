package com.chinadaas.gsinfo.admin.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.chinadaas.gsinfo.admin.vo.BaseEntity;


/**
 * 持久层公共模板实现类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: 持久层公共模板实现类，实现对子类的所有公共方法<br>
 * date: 2014年7月8日 下午2:59:34<br>
 * @author 开发者真实姓名[裔传洲]
 * @see com.chinadaas.gsinfo.admin.dao.IDAO
 * @param <T>
 */
@Repository
public abstract class BaseDAO<T extends BaseEntity> extends HibernateDaoSupport implements IDAO<T>{
	
	private static final Logger logger = LoggerFactory.getLogger(BaseDAO.class);
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	/**
	 * desc: 通过注入设置jdbc模板<br>
	 * date: 2014年9月1日 下午3:01:16<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param jdbcTemplate
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 新增保存方法
	 */
	public String save(T instance) throws DataAccessException {
		logger.debug("start to save object:" + this.getGenericClassName());
		try {
			return (String) this.getHibernateTemplate().save(instance);
		} catch (DataAccessException e) {
			try {
				logger.error("failed to save object(" + this.getGenericClassName()
						+ "):"
						+ PropertyUtils.getProperty(instance, "id").toString()
						+ "[" + e.toString() + "]");
			} catch (Exception e1) {
				logger.error(e1.getMessage());
			}
			throw e;
		}
	}
	
	/**
	 * 对象更新方法
	 */
	public void update(T instance) throws DataAccessException {
		logger.debug("start to update object:" + this.getGenericClassName());
		try {
			this.getHibernateTemplate().merge(instance);
		} catch (DataAccessException e) {
			try {
				logger.error("failed to update object(" + this.getGenericClassName()
						+ "):"
						+ PropertyUtils.getProperty(instance, "id").toString()
						+ "[" + e.toString() + "]");
			} catch (Exception e1) {
				logger.error(e1.getMessage());
			}
			throw e;
		}
	}
	
	/**
	 * 保存或更新方法
	 */
	public void saveOrUpdate(T instance) throws DataAccessException {
		logger.debug("start to save or update object:" + this.getGenericClassName());
		try {
			this.getHibernateTemplate().saveOrUpdate(instance);
		} catch (DataAccessException e) {
			try {
				logger.error("failed to save or update object(" + this.getGenericClassName()
						+ "):"
						+ PropertyUtils.getProperty(instance, "id").toString()
						+ "[" + e.toString() + "]");
			} catch (Exception e1) {
				logger.error(e1.getMessage());
			}
			throw e;
		}
	}
	
	/**
	 * 删除对象方法
	 */
	public void delete(T instance) throws DataAccessException {
		logger.debug("start to delete object:" + this.getGenericClassName());
		try {
			this.getHibernateTemplate().delete(instance);
		} catch (DataAccessException e) {
			try {
				logger.error("failed to delete object(" + this.getGenericClassName()
						+ "):"
						+ PropertyUtils.getProperty(instance, "id").toString());
			} catch (Exception e1) {
				logger.error(e1.getMessage());
			}
			throw e;
		}
	}

	/**
	 * 删除主键方法
	 */
	public void delete(String id) throws DataAccessException {
		delete(getObjectById(id));
	}
	
	/**
	 * 查询所有对象列表
	 */
	public List<?> all() throws DataAccessException {
		String hql = "from "+ this.getGenericClassName();
		return this.filter(hql);
	}
	
	/**
	 * 根据hql过滤条件查询对象列表
	 */
	public List<?> filter(String hql, Object... values) throws DataAccessException {
		return this.getHibernateTemplate().find(hql, values);
	}
	
	/**
	 * 分页查询对象列表
	 */
	public List<?> all(int start, int limit) {
		String hql = "from "+ this.getGenericClassName();
		return this.filter(hql, start, limit);
	}
	
	/**
	 * 根据hql过滤条件查询分页对象列表
	 */
	public List<?> filter(String hql, int start, int limit) {
		Assert.hasText(hql);
		int startIndex = start <= 0 ? 1 : start;
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		return query.setFirstResult(startIndex - 1).setMaxResults(limit).list();
	}
	
	/**
	 * 执行SQL查询语句
	 */
	public List execSQLQuery(String sql) {
		logger.info("Execute query SQL: " +sql);
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 执行SQL查询语句
	 */
	public List execSQLQuery(String sql, Object... params) {
		logger.info("Execute query SQL: " +sql);
		return jdbcTemplate.queryForList(sql, params);
	}
	
	/**
	 * 执行SQL分页查询
	 */
	public List execSQLQuery(String sql, int start, int limit) {
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql)
				.setFirstResult(start).setMaxResults(limit).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		logger.info("Execute query SQL: " +sql);
		return query.list();
	}
	
	/**
	 * 执行SQL更新语句
	 */
	public void execSQLUpdate(String sql) {
		try {
			int count = jdbcTemplate.update(sql);
			logger.info(String.format("Execute update SQL, %d records effected: %s", count, sql));
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * 执行SQL更新语句
	 */
	public void execSQLUpdate(String sql, Object... params) {
		try {
			int count = jdbcTemplate.update(sql, params);
			logger.info(String.format("Execute update SQL, %d records effected: %s", count, sql));
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * 执行批量更新SQL语句
	 */
	public int[] batchUpdate(String sql, List<Map> params, String[] keys) {
		int[] updateCounts = jdbcTemplate.batchUpdate(sql,
				new BatchPreparedStatementMapSetter(params, keys));
		int sum = 0;
		for(int count : updateCounts) {
			sum += count;
		}
		logger.info(String.format("Execute batch update SQL, %d records effected: %s", sum, sql));
		return updateCounts;
	}
	
	/**
	 * 获取SQL查询结果数目
	 */
	public int getCount(String sql) {
		Query query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(String.format("SELECT COUNT(1) CNT FROM (%s) A", sql))
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map rs = (Map) query.list().get(0);
		return Integer.valueOf(rs.get("CNT").toString());
	}
	
	/**
	 * 根据主键查找对象
	 */
	@SuppressWarnings("unchecked")
	public T getObjectById(String id) throws DataAccessException {
		return (T) this.getHibernateTemplate().get(this.getGenericClassName(), id);
	}
	
	/**
	 * 根据hql查找对象
	 */
	@SuppressWarnings("unchecked")
	public T getObjectByHQL(String hql, Object... values) throws DataAccessException {
		List<?> rs = filter(hql, values);
		if (rs != null && rs.size() > 0) {
			return (T) rs.get(0);
		}
		return null;
	}
	
	/**
	 * hql条件对应对象是否存在
	 */
	public boolean exist(String hql, Object... values) throws DataAccessException {
		return getTotalCount(hql, values) > 0l;
	}
	
	/**
	 * hql条件对应对象数目
	 */
	public long getTotalCount(String hql, Object... values) throws DataAccessException {
		String countQueryString = " select count (*) "
				+ removeHQLSelect(removeHQLOrder(hql));
		List<?> countlist = this.filter(countQueryString, values);
		return (Long) countlist.get(0);
	}
	
	/**
	 * 查询当前对象所有数目
	 */
	public long getTotalCount() {
		return getTotalCount("from "+ this.getGenericClassName(), null);
	}
	
	/**
	 * desc: 获取具体泛型类型<br>
	 * date: 2014年9月1日 下午3:06:26<br>
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
	 * desc: 去掉HQL的select部分<br>
	 * date: 2014年9月1日 下午3:07:04<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param hql
	 * @return
	 */
	private String removeHQLSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql
				+ " must has a keyword 'from'");
		return hql.substring(beginPos);
	}
	
	/**
	 * desc: 去掉HQL的order by部分<br>
	 * date: 2014年9月1日 下午3:07:47<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param hql
	 * @return
	 */
	private String removeHQLOrder(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
}

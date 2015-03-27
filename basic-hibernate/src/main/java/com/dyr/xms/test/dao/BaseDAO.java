/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */
/**
 * Author XuMaoSen
 */
package com.dyr.xms.test.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dyr.xms.test.model.PageBean;
import com.dyr.xms.test.model.SystemContext;

/**
 * Project:basic-hibernate
 * Package:com.dyr.xms.test.dao
 * FileName:BaseDAO.java
 * Comments:
 * JDK Version:
 * Author XuMaoSen
 * Create Date:2015-3-24 下午4:00:17
 * Modified By:XuMaoSen
 * Modified Time:
 * What is Modified:
 * Description:
 * Version:
 */
@SuppressWarnings("unchecked")
public class BaseDAO<T> implements IBaseDAO<T>{

	@Inject
	private SessionFactory sessionFactory;
	
	protected Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 定义一个泛型类
	 */
	private Class<?> clz;

	/**
	 * Author XuMaoSen
	 * @return the clz
	 */
	private Class<?> getClz() {
		if(clz==null){
			// 获取泛型的Class对象
			clz = (Class<?>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return clz;
	}
	
	/**
	 * t为临时对象
	 */
	@Override
	public T insert(T t) {
		getCurrentSession().save(t);
		return t;
	}

	/**
	 * t为持久态对象
	 */
	@Override
	public void delete(T t) {
		getCurrentSession().delete(t);
	}

	/**
	 * id为要查询的数据的主键
	 */
	@Override
	public T select(Integer id) {
		return (T) getCurrentSession().load(getClz(), id);
	}

	/**
	 * t为游离态的对象(有id的临时态对象) 
	 */
	@Override
	public void update(T t) {
		getCurrentSession().update(t);
	}
	
	/**
	 * 初始化排序的字段和排序的方式
	 * 
	 * @param hql 输入的hql语句("from User u")
	 * @return 返回增加了排序规则的hql语句
	 */
	private String initSort(String hq1){
		//排序方式
		String order = SystemContext.getOrderKey();
		//排序方式
		String sort = SystemContext.getSortWays();
		if(sort != null&& !"".equals(sort.trim())){
			hq1 += " order by " + sort;
			if("desc".equals(order)){
				hq1 += "desc";
			}else {
				hq1 += "asc";
			}
		}
		return hq1;
	}
	
	/**
	 * 设置别名参数
	 * 
	 * @param query ：Hibernate的查询对象
	 * @param alias	：使用到的别名参数
	 */
	@SuppressWarnings("rawtypes")
	private void setAliasParameter(Query query, Map<String, Object>alias){
		if(alias != null){
			
			Set<String> keys = alias.keySet();
			for (String key : keys) {
				Object obj = alias.get(key);
				if(obj instanceof Collection){
					query.setParameterList(key, (Collection) obj);
				}else {
					query.setParameter(key, obj);
				}
			}
		}
	}
	
	/**
	 * 设置带参数的查询
	 * 
	 * @param query：Hibernate的查询的对象
	 * @param args	：设置的参数(占位符方式)
	 */
	private void setParameter(Query query,Object[] args){
		if(args != null && args.length>0){
			int index = 0;
			for (Object obj : args) {
				query.setParameter(index++, obj);
			}
		}
	}
	
	/**
	 * 根据hql的查询，使用参数对象，别名查询
	 * 
	 * @param hql
	 * @param args
	 * @param alias
	 * @return
	 */
	public List<T> list(String hql, Object[] args, Map<String, Object>alias){
		hql = initSort(hql);
		Query query = getCurrentSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.list();
	}
	
	/**
	 * 使用参数(占位符)查询
	 * @param hql
	 * @param args
	 * @return
	 */
	public List<T> listByParameter(String hql, Object[] args){
		return list(hql, args, null);
	}
	
	/**
	 * 基于别名的查询
	 * @param hql
	 * @param alias
	 * @return
	 */
	public List<T> listByAlias(String hql, Map<String, Object>alias){
		return list(hql, null, alias);
	}
	
	/**
	 * 基于单个参数对象的查询
	 * @param hql
	 * @param obj
	 * @return
	 */
	public List<T> listByOneParameter(String hql, Object obj){
		return list(hql, (Object[]) obj, null);
	}
	
	/**
	 * 设置分页参数
	 * 
	 * @param query：Hibernate的查询对象
	 * @param pager	：分页参数
	 */
	private void setPageBean(Query query, PageBean pageBean){
		Integer pageRows = SystemContext.getPageRows();
		Integer pageOffset = SystemContext.getPageOffset();
		if(pageOffset == null || pageOffset <0){
			pageOffset = 0;
		}
		if(pageRows == null || pageRows <0){
			pageRows = 0;
		}
		pageBean.setPageOffset(pageOffset);
		pageBean.setPageRows(pageRows);
//		System.out.println(pageBean.getClass().getName());
		query.setFirstResult(pageOffset).setMaxResults(pageRows);
	}
	
	/**
	 * 注意这个fetch的功能，我们这里只是查询每条hql的总数，fetch的作用就是在不影响Hibernate的性能，但是又能加载关联类的实体类；我们在针对Hibernate缓存是最好默认关闭 lazy=false，影响性能；
	 * @param hql：查询的对象
	 * @param isHql	：是不是hql语句，针对后面的分类有基于hql和sql的查询
	 * @return 返回统计查询的数量
	 */
	private String getCountHql(String hql, boolean isHql){
		String e = hql.substring(hql.indexOf("from"));
		String c = "select count(*) "+e;
		if(isHql){
			c = c.replaceAll("fetch", "");
		}
		return c;
	}
	
	/**
	 * 实现分页查找的方法 
	 * @param hql
	 * @param args
	 * @param alias
	 * @return
	 */
	public PageBean find(String hql, Object[] args, Map<String, Object>alias){
		hql = initSort(hql);
		String cq = getCountHql(hql, true);
		//查询总数
		Query cQuery = getCurrentSession().createQuery(cq);
		//一般查询
		Query query = getCurrentSession().createQuery(hql);
		//设置参数
		setAliasParameter(cQuery, alias);
		setAliasParameter(query, alias);
		setParameter(cQuery, args);
		setParameter(query, args);
		//设置分页查询
		PageBean pageBean = new PageBean();
		setPageBean(query, pageBean);
		List<T> datas = query.list();
		pageBean.setDataList(datas);
		//设置总数查询
		Long total = (Long) cQuery.uniqueResult();
		pageBean.setCountRows(total);
		return pageBean;
	}
	
	/**
	 * 查询单个对象
	 */
	public Object queryObject(String hql, Object[] args, Map<String, Object>alias ){
		Query query = getCurrentSession().createQuery(hql);
		//设置参数
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.uniqueResult();
	}
	
	public void updateByHQL(String hql, Object[] args){
		Query query = getCurrentSession().createQuery(hql);
		setParameter(query, args);
		query.executeUpdate();
	}
	
	public void updateByHQL(String hql, Object arg){
		this.updateByHQL(hql, (Object[])arg);
	}
	
	public void updateByHQL(String hql){
		this.updateByHQL(hql, null);
	}

}

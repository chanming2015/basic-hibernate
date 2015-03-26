/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */
/**
 * Author XuMaoSen
 */
package com.dyr.xms.test.dao;

import java.lang.reflect.ParameterizedType;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
	public Class<?> getClz() {
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
	@SuppressWarnings("unchecked")
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

}

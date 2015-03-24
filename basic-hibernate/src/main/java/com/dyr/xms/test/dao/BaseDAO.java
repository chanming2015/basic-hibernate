/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */
/**
 * Author XuMaoSen
 */
package com.dyr.xms.test.dao;

import javax.inject.Inject;

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
	/**
	 * Author XuMaoSen
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Author XuMaoSen
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public T insert(T t) {
		
		return null;
	}

	@Override
	public void delete(T t) {
	}

	@Override
	public T select(Integer id) {
		
		return null;
	}

	@Override
	public void update(T t) {
	}

}

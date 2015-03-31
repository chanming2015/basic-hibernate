/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */
/**
 * Author XuMaoSen
 */
package com.dyr.xms.test.dao;

import java.util.List;
import java.util.Map;

import com.dyr.xms.test.model.PageBean;
import com.dyr.xms.test.model.User;

/**
 * Project:basic-hibernate
 * Package:com.dyr.xms.test.dao
 * FileName:IUserDAO.java
 * Comments:
 * JDK Version:
 * Author XuMaoSen
 * Create Date:2015-3-24 下午5:30:00
 * Modified By:XuMaoSen
 * Modified Time:
 * What is Modified:
 * Description:
 * Version:
 */
public interface IUserDAO extends IBaseDAO<User> {
	
	/**
	 * 
	 * @author XuMaoSen
	 * Create Time:2015-3-26 下午5:51:18
	 * Description 根据hql的查询，使用参数对象，别名查询
	 * @param hql
	 * @param args
	 * @param alias
	 * @return
	 */
	public List<User> list(String hql, Object[] args, Map<String, Object>alias);
	
	/**
	 * 
	 * @author XuMaoSen
	 * Create Time:2015-3-26 下午5:53:20
	 * Description 使用参数(占位符)查询
	 * @param hql
	 * @param args
	 * @return
	 */
	public List<User> listByParameter(String hql, Object[] args);
	
	/**
	 * 
	 * @author XuMaoSen
	 * Create Time:2015-3-26 下午5:54:22
	 * Description 基于别名的查询
	 * @param hql
	 * @param alias
	 * @return
	 */
	public List<User> listByAlias(String hql, Map<String, Object>alias);
	
	/**
	 * 
	 * @author XuMaoSen
	 * Create Time:2015-3-26 下午5:54:43
	 * Description 基于单个参数对象的查询
	 * @param hql
	 * @param obj
	 * @return
	 */
	public List<User> listByOneParameter(String hql, Object obj);
	
	/**
	 * 
	 * @author XuMaoSen
	 * Create Time:2015-3-26 下午5:55:06
	 * Description 实现分页查找的方法
	 * @param hql
	 * @param args
	 * @param alias
	 * @return
	 */
	public PageBean find(String hql, Object[] args, Map<String, Object>alias);

}

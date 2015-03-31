/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */
/**
 * Author XuMaoSen
 */
package com.dyr.xms.test.dao;

/**
 * Project:basic-hibernate
 * Package:com.dyr.xms.test.dao
 * FileName:BaseDAO.java
 * Comments:
 * JDK Version:
 * Author XuMaoSen
 * Create Date:2015-3-23 下午5:57:25
 * Modified By:XuMaoSen
 * Modified Time:
 * What is Modified:
 * Description:
 * Version:
 * @param <T>
 */
public interface IBaseDAO<T> {
	
	/**
	 * 
	 * @author XuMaoSen
	 * Create Time:2015-3-24 下午3:56:06
	 * Description 增加
	 * @param t为临时对象
	 * @return
	 */
	public T insert(T t);
	/**
	 * 
	 * @author XuMaoSen
	 * Create Time:2015-3-24 下午3:56:06
	 * Description 删除
	 * @param t为持久态对象
	 * @return
	 */
	public void delete(T t);
	/**
	 * 
	 * @author XuMaoSen
	 * Create Time:2015-3-24 下午3:56:06
	 * Description 查询
	 * @param id为要查询的数据的主键
	 * @return
	 */
	public T select(Integer id);
	/**
	 * 
	 * @author XuMaoSen
	 * Create Time:2015-3-24 下午3:56:06
	 * Description 修改
	 * @param t为游离态的对象或持久态对象 
	 * @return
	 */
	public void update(T t);
}

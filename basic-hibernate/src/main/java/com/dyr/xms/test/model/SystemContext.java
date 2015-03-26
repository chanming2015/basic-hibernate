/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */
/**
 * Author XuMaoSen
 */
package com.dyr.xms.test.model;

/**
 * Project:basic-hibernate
 * Package:com.dyr.xms.test.model
 * FileName:SystemContext.java
 * Comments:
 * JDK Version:
 * Author XuMaoSen
 * Create Date:2015-3-26 下午3:51:41
 * Modified By:XuMaoSen
 * Modified Time:
 * What is Modified:
 * Description:
 * Version:
 */
public class SystemContext {
	
	/**
	 * 每页条数
	 */
	private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();
	/**
	 * 当前页码
	 */
	private static ThreadLocal<Integer> pageOffset = new ThreadLocal<Integer>();
	/**
	 * 排序方式
	 */
	private static ThreadLocal<String> sort  = new ThreadLocal<String>();
	/**
	 * 排序的字段
	 */
	private static ThreadLocal<String> order  = new ThreadLocal<String>();
	/**
	 * 真实路径
	 */
	private static ThreadLocal<String> realPath  = new ThreadLocal<String>();
	
	/**
	 * 生成get（）、set（）并修改
	 */
	/**
	 * Author XuMaoSen
	 * @return the pageSize
	 */
	public static Integer getPageSize() {
		return pageSize.get();
	}
	/**
	 * Author XuMaoSen
	 * @param pageSize the pageSize to set
	 */
	public static void setPageSize(Integer pageSize) {
		SystemContext.pageSize.set(pageSize);
	}
	/**
	 * Author XuMaoSen
	 * @return the pageOffset
	 */
	public static Integer getPageOffset() {
		return pageOffset.get();
	}
	/**
	 * Author XuMaoSen
	 * @param pageOffset the pageOffset to set
	 */
	public static void setPageOffset(Integer pageOffset) {
		SystemContext.pageOffset.set(pageOffset);
	}
	/**
	 * Author XuMaoSen
	 * @return 得到排序方式
	 */
	public static String getSort() {
		return sort.get();
	}
	/**
	 * Author XuMaoSen
	 * @param sort the sort to set
	 */
	public static void setSort(String sort) {
		SystemContext.sort.set(sort);
	}
	/**
	 * Author XuMaoSen
	 * @return 得到排序的字段
	 */
	public static String getOrder() {
		return order.get();
	}
	/**
	 * Author XuMaoSen
	 * @param order the order to set
	 */
	public static void setOrder(String order) {
		SystemContext.order.set(order);
	}
	/**
	 * Author XuMaoSen
	 * @return the realPath
	 */
	public static String getRealPath() {
		return realPath.get();
	}
	/**
	 * Author XuMaoSen
	 * @param realPath the realPath to set
	 */
	public static void setRealPath(String realPath) {
		SystemContext.realPath.set(realPath);
	}
	
	/**
	 * 定义删除系统变量
	 */
	public static void removePageSize(){
		pageSize.remove();
	}
	public static void removePageOffset(){
		pageOffset.remove();
	}
	public static void removeOrder(){
		order.remove();
	}
	public static void removeRealPath(){
		realPath.remove();
	}

}

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
	private static ThreadLocal<Integer> pageRows = new ThreadLocal<Integer>();
	/**
	 * 显示开始位置(从..开始显示)
	 */
	private static ThreadLocal<Integer> pageOffset = new ThreadLocal<Integer>();
	/**
	 * 排序方式
	 */
	private static ThreadLocal<String> sortWays  = new ThreadLocal<String>();
	/**
	 * 排序的字段
	 */
	private static ThreadLocal<String> orderKey  = new ThreadLocal<String>();
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
	public static Integer getPageRows() {
		return pageRows.get();
	}
	/**
	 * Author XuMaoSen
	 * @param pageSize the pageSize to set
	 */
	public static void setPageRows(Integer pageRows) {
		SystemContext.pageRows.set(pageRows);
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
	 * 显示开始位置(从..之后开始显示)
	 */
	public static void setPageOffset(Integer pageOffset) {
		SystemContext.pageOffset.set(pageOffset);
	}
	/**
	 * Author XuMaoSen
	 * @return 得到排序方式
	 */
	public static String getSortWays() {
		return sortWays.get();
	}
	/**
	 * Author XuMaoSen
	 * @param sort the sort to set
	 */
	public static void setSortWays(String sortWays) {
		SystemContext.sortWays.set(sortWays);
	}
	/**
	 * Author XuMaoSen
	 * @return 得到排序的字段
	 */
	public static String getOrderKey() {
		return orderKey.get();
	}
	/**
	 * Author XuMaoSen
	 * @param orderKey the order to set
	 */
	public static void setOrderKey(String orderKey) {
		SystemContext.orderKey.set(orderKey);
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
	public static void removePageRows(){
		pageRows.remove();
	}
	public static void removePageOffset(){
		pageOffset.remove();
	}
	public static void removeOrderKey(){
		orderKey.remove();
	}
	public static void removeRealPath(){
		realPath.remove();
	}

}

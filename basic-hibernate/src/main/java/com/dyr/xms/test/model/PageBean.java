/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */
/**
 * Author XuMaoSen
 */
package com.dyr.xms.test.model;

import java.util.List;

/**
 * Project:basic-hibernate
 * Package:com.dyr.xms.test.model
 * FileName:PageBean.java
 * Comments:
 * JDK Version:
 * Author XuMaoSen
 * Create Date:2015-3-23 下午5:49:24
 * Modified By:XuMaoSen
 * Modified Time:
 * What is Modified:
 * Description:
 * Version:
 */
public class PageBean {
	
	private int currentPage;
	private int pageRows;
	private int countPage;
	private int countRows;
	private List<?> dataList;
	/**
	 * Author XuMaoSen
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	/**
	 * Author XuMaoSen
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * Author XuMaoSen
	 * @return the pageRows
	 */
	public int getPageRows() {
		return pageRows;
	}
	/**
	 * Author XuMaoSen
	 * @param pageRows the pageRows to set
	 */
	public void setPageRows(int pageRows) {
		this.pageRows = pageRows;
	}
	/**
	 * Author XuMaoSen
	 * @return the countPage
	 */
	public int getCountPage() {
		return countPage;
	}
	/**
	 * Author XuMaoSen
	 * @param countPage the countPage to set
	 */
	public void setCountPage() {
		this.countPage = countRows % pageRows == 0 ? countRows / pageRows : countRows / pageRows + 1;
	}
	/**
	 * Author XuMaoSen
	 * @return the countRows
	 */
	public int getCountRows() {
		return countRows;
	}
	/**
	 * Author XuMaoSen
	 * @param countRows the countRows to set
	 */
	public void setCountRows(int countRows) {
		this.countRows = countRows;
	}
	/**
	 * Author XuMaoSen
	 * @return the dataList
	 */
	public List<?> getDataList() {
		return dataList;
	}
	/**
	 * Author XuMaoSen
	 * @param dataList the dataList to set
	 */
	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

}

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
 * Description: 适用于hibernate的分页对象
 * Version:
 */
public class PageBean {
	
	/**
	 * 显示开始位置(从..之后开始显示)
	 */
	private Integer pageOffset;
	/**
	 * 每页条数
	 */
	private Integer pageRows;
	/**
	 * 总页数
	 */
	private Long countPage;
	/**
	 * 总记录条数
	 */
	private Long countRows;
	/**
	 * 分页数据
	 */
	private List<?> dataList;
	/**
	 * Author XuMaoSen
	 * @return the currentPage
	 */
	public Integer getPageOffset() {
		return pageOffset;
	}
	/**
	 * Author XuMaoSen
	 * @param currentPage the currentPage to set
	 */
	public void setPageOffset(Integer pageOffset) {
		this.pageOffset = pageOffset;
	}
	/**
	 * Author XuMaoSen
	 * @return the pageRows
	 */
	public Integer getPageRows() {
		return pageRows;
	}
	/**
	 * Author XuMaoSen
	 * @param pageRows the pageRows to set
	 */
	public void setPageRows(Integer pageRows) {
		this.pageRows = pageRows;
	}
	/**
	 * Author XuMaoSen
	 * @return the countPage
	 */
	public Long getCountPage() {
		return countPage;
	}
	/**
	 * Author XuMaoSen
	 * @param countPage the countPage to set
	 */
	public void setCountPage(Long countPage) {
		this.countPage = countPage;
	}
	/**
	 * Author XuMaoSen
	 * @return the countRows
	 */
	public Long getCountRows() {
		return countRows;
	}
	/**
	 * Author XuMaoSen
	 * @param countRows the countRows to set
	 */
	public void setCountRows(Long countRows) {
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

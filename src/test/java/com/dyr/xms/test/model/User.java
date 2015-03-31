/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */
/**
 * Author XuMaoSen
 */
package com.dyr.xms.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Project:basic-hibernate
 * Package:com.dyr.xms.test.model
 * FileName:User.java
 * Comments:
 * JDK Version:
 * Author XuMaoSen
 * Create Date:2015-3-24 下午5:23:54
 * Modified By:XuMaoSen
 * Modified Time:
 * What is Modified:
 * Description:
 * Version:
 */
@Entity
@Table(name="tb_user")
public class User {

	private Integer id;
	private String username;
	private String pass;
	/**
	 * Author XuMaoSen
	 * @return the id
	 */
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	/**
	 * Author XuMaoSen
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Author XuMaoSen
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Author XuMaoSen
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * Author XuMaoSen
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}
	/**
	 * Author XuMaoSen
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	/**
	 * Project:basic-hibernate
	 * Package:com.dyr.xms.test.model
	 * FileName:User.java
	 * Comments:
	 * JDK Version:
	 * Author XuMaoSen
	 * Create Date:2015-3-24 下午5:25:47
	 * Modified By:XuMaoSen
	 * Modified Time:
	 * What is Modified:
	 * Version:
	 */
	public User(Integer id, String username) {
		this.id = id;
		this.username = username;
	}
	public User(){
		
	}
}

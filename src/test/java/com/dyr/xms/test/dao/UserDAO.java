/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */
/**
 * Author XuMaoSen
 */
package com.dyr.xms.test.dao;

import org.springframework.stereotype.Repository;
import com.dyr.xms.test.model.User;

/**
 * Project:basic-hibernate
 * Package:com.dyr.xms.test.dao
 * FileName:UserDAO.java
 * Comments:
 * JDK Version:
 * Author XuMaoSen
 * Create Date:2015-3-24 下午5:30:22
 * Modified By:XuMaoSen
 * Modified Time:
 * What is Modified:
 * Description:
 * Version:
 */
//注入spring
@Repository("userDAO")
public class UserDAO extends BaseDAO<User> implements IUserDAO{

}

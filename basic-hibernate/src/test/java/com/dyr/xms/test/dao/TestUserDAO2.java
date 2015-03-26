/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */
/**
 * Author XuMaoSen
 */
package com.dyr.xms.test.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import com.dyr.xms.test.model.PageBean;
import com.dyr.xms.test.model.User;
import com.dyr.xms.test.util.AbstractDbUnitTestCase;
import com.github.springtestdbunit.DbUnitTestExecutionListener;

/**
 * Project:basic-hibernate
 * Package:com.dyr.xms.test.dao
 * FileName:TestUserDAO.java
 * Comments:
 * JDK Version:
 * Author XuMaoSen
 * Create Date:2015-3-24 下午6:23:27
 * Modified By:XuMaoSen
 * Modified Time:
 * What is Modified:
 * Description:
 * Version:
 */
//指定测试类
@RunWith(SpringJUnit4ClassRunner.class)
//指定配置文件
@ContextConfiguration("/applicationContext.xml")
//指定测试类之前要执行的操作：
@TestExecutionListeners({DbUnitTestExecutionListener.class,DependencyInjectionTestExecutionListener.class})
public class TestUserDAO2 extends AbstractDbUnitTestCase{

	@Inject
	private SessionFactory sessionFactory;
	@Inject
	private IUserDAO userDAO;
	
	@Before
	public void setUp() throws DataSetException, IOException, SQLException{
		//获取当前session
		Session session = sessionFactory.openSession();
		//将线程与实务绑定
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
		//备份表
		bakcupOneTable("tb_user");
	}
	
	@Test
	public void testList() throws IOException, DatabaseUnitException, SQLException {
		IDataSet ds = createDateSet("tb_user");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
		String hql = "from User u where u.id=? and u.username = :name";
		Object[] args = new Object[]{1};
		Map<String , Object> alias = new HashMap<String, Object>();
		alias.put("name", "admin1");
		List<User> list = userDAO.list(hql, args, alias);
		Assert.assertEquals("123", list.get(0).getPass());
	}
	
	@Test
	public void testFind() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDateSet("tb_user");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
		String hql = "from User";
		PageBean pageBean = userDAO.find(hql, null, null);
		Assert.assertEquals(10, pageBean.getDataList().size());
	}
	
	@After
	public void tearDown() throws DatabaseUnitException, SQLException, IOException{
		SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
		Session session = holder.getSession();
		session.flush();
		//取消绑定
		TransactionSynchronizationManager.unbindResource(sessionFactory);
		//回复表
		resumesTable();
	}

}

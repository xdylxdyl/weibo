package com.gemantic.gemantic.weibo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.gemantic.weibo.model.CompanyExtendnews;
import com.gemantic.gemantic.weibo.service.CompanyExtendnewsService;

public class CompanyExtendnewsServiceTest {

	private static final Log log = LogFactory
			.getLog(CompanyExtendnewsServiceTest.class);

	private CompanyExtendnewsService companyExtendnewsService;

	@Before
	public void setUp() throws Exception {

		// dao
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext*.xml");
		companyExtendnewsService = (CompanyExtendnewsService) context
				.getBean("companyExtendnewsService");
		// local server
		/**
		 * companyExtendnewsService = (CompanyExtendnewsService)
		 * Naming.lookup("//localhost:8801/CompanyExtendnewsRMIService");
		 **/

		/**
		 * test client ApplicationContext context = new
		 * ClassPathXmlApplicationContext
		 * ("classpath:META-INF/spring/applicationContext-sca.xml");
		 * companyExtendnewsService = (CompanyExtendnewsService)
		 * context.getBean("companyExtendnewsService");
		 **/

	}

	@Test
	public void testCRUD() throws ServiceException, ServiceDaoException {

		// 1.增加

		CompanyExtendnews companyExtendnews = new CompanyExtendnews();

		companyExtendnews.setCompanyUri("xxxx");

		companyExtendnews.setEid("4");

		companyExtendnews.setSource("weibo");

		companyExtendnews.setType(1);

		Long id = this.companyExtendnewsService.insert(companyExtendnews);

		companyExtendnews = this.companyExtendnewsService.getObjectById(id);

		CompanyExtendnews companyExtendnews2 = this.companyExtendnewsService
				.getObjectById(id);
		Assert.assertNotNull(companyExtendnews2);

		// 2. 更改
		companyExtendnews.setCompanyUri("yyyy");
		companyExtendnews.setEid("3");
		companyExtendnews.setSource("qq");
		companyExtendnews.setType(2);
		boolean success = this.companyExtendnewsService
				.update(companyExtendnews);
		Assert.assertEquals(true, success);
		CompanyExtendnews companyExtendnews3 = this.companyExtendnewsService
				.getObjectById(id);
		// 3.删除
		boolean successDelete = this.companyExtendnewsService.delete(id);
		Assert.assertEquals(true, success);
		CompanyExtendnews companyExtendnews4 = this.companyExtendnewsService
				.getObjectById(id);
		Assert.assertNull(companyExtendnews4);

		// 4.batchInsert
		List<CompanyExtendnews> list = new ArrayList<CompanyExtendnews>();
		CompanyExtendnews companyExtendnews5 = new CompanyExtendnews();

		companyExtendnews5.setCompanyUri("xxxx");

		companyExtendnews5.setEid("4");

		companyExtendnews5.setSource("weibo");

		companyExtendnews5.setType(1);

		list.add(companyExtendnews5);
		CompanyExtendnews companyExtendnews6 = new CompanyExtendnews();

		companyExtendnews6.setCompanyUri("yyyy");

		companyExtendnews6.setEid("3");

		companyExtendnews6.setSource("qq");

		companyExtendnews6.setType(2);

		list.add(companyExtendnews6);
		List<CompanyExtendnews> insertResults = this.companyExtendnewsService
				.insertList(list);
		Assert.assertEquals(2, insertResults.size());
		// 5.batchGet
		List<Long> ids = new ArrayList<Long>();
		for (CompanyExtendnews o : insertResults) {
			ids.add(o.getId());
		}

		List<CompanyExtendnews> getResults = this.companyExtendnewsService
				.getObjectsByIds(ids);
		Assert.assertEquals(2, getResults.size());

		for (CompanyExtendnews o : insertResults) {
			this.companyExtendnewsService.delete(o.getId());
		}

		// 6.batchUpdate

	}

	@Test
	public void testNULL() throws ServiceException, ServiceDaoException {

	};
}

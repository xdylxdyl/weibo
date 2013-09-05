package com.gemantic.gemantic.weibo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.gemantic.weibo.model.Weibo;
import com.gemantic.gemantic.weibo.service.WeiboService;
@Ignore
public class WeiboServiceTest {

	private static final Log log = LogFactory.getLog(WeiboServiceTest.class);

	private WeiboService weiboService;

	@Before
	public void setUp() throws Exception {

		// dao
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/semantic-weibo-service/applicationContext-server.xml");
		weiboService = (WeiboService) context.getBean("weiboService");
		// local server
		/**
		 * weiboService = (WeiboService)
		 * Naming.lookup("//localhost:8801/WeiboRMIService");
		 **/

		/**
		 * test client ApplicationContext context = new
		 * ClassPathXmlApplicationContext
		 * ("classpath:META-INF/spring/applicationContext-sca.xml");
		 * weiboService = (WeiboService) context.getBean("weiboService");
		 **/

	}

	@Test
	public void testCRUD() throws ServiceException, ServiceDaoException {

		// 1.增加

		Weibo weibo = new Weibo();

		weibo.setWid("947A7F3AE74B05B529B66B5011A8695X");

		weibo.setContent("12306事件");

		weibo.setCommentCount(222);

		weibo.setForwardCount(111111);

		weibo.setAuthorID(3297540145L);

		weibo.setFromText("官方微博");

		weibo.setLink("http://weibo.com/ptteng");

		weibo.setPublishAt(3L);

		weibo.setStatus(0);

		weibo.setForwardID("947A7F3AE74B05B529B66B5011A8695F");

		Long id = this.weiboService.insert(weibo);

		weibo = this.weiboService.getObjectById(id);

		Weibo weibo2 = this.weiboService.getObjectById(id);
		Assert.assertNotNull(weibo2);

		// 2. 更改
		weibo.setWid("947A7F3AE74B05B529B66B5011A8695Z");
		weibo.setContent("刘老太事件");
		weibo.setCommentCount(333333);
		weibo.setForwardCount(2223);
		weibo.setAuthorID(3297540142L);
		weibo.setFromText("官方微博");
		weibo.setLink("http://weibo.com/ptteng");
		weibo.setPublishAt(4L);
		weibo.setStatus(0);
		weibo.setForwardID("947A7F3AE74B05B529B66B5011A8695F");
		boolean success = this.weiboService.update(weibo);
		Assert.assertEquals(true, success);
		Weibo weibo3 = this.weiboService.getObjectById(id);
		// 3.删除
		boolean successDelete = this.weiboService.delete(id);
		Assert.assertEquals(true, success);
		Weibo weibo4 = this.weiboService.getObjectById(id);
		Assert.assertNull(weibo4);

		// 4.batchInsert
		List<Weibo> list = new ArrayList<Weibo>();
		Weibo weibo5 = new Weibo();

		weibo5.setWid("947A7F3AE74B05B529B66B5011A8695R");

		weibo5.setContent("12306事件");

		weibo5.setCommentCount(222);

		weibo5.setForwardCount(111111);

		weibo5.setAuthorID(3297540145L);

		weibo5.setFromText("官方微博");

		weibo5.setLink("http://weibo.com/ptteng");

		weibo5.setPublishAt(3L);

		weibo5.setStatus(0);

		weibo5.setForwardID("947A7F3AE74B05B529B66B5011A8695F");

		list.add(weibo5);
		Weibo weibo6 = new Weibo();

		weibo6.setWid("947A7F3AE74B05B529B66B5011A8695S");

		weibo6.setContent("刘老太事件");

		weibo6.setCommentCount(333333);

		weibo6.setForwardCount(2223);

		weibo6.setAuthorID(3297540142L);

		weibo6.setFromText("官方微博");

		weibo6.setLink("http://weibo.com/ptteng");

		weibo6.setPublishAt(4L);

		weibo6.setStatus(0);

		weibo6.setForwardID("947A7F3AE74B05B529B66B5011A8695F");

		list.add(weibo6);
		List<Weibo> insertResults = this.weiboService.insertList(list);
		Assert.assertEquals(2, insertResults.size());
		// 5.batchGet
		List<Long> ids = new ArrayList<Long>();
		for (Weibo o : insertResults) {
			ids.add(o.getId());
		}

		List<Weibo> getResults = this.weiboService.getObjectsByIds(ids);
		Assert.assertEquals(2, getResults.size());

		for (Weibo o : insertResults) {
			this.weiboService.delete(o.getId());
		}

		// 6.batchUpdate

	}

	// @Test
	public void getWeiboIdsByStatus() throws ServiceException,
			ServiceDaoException {
		List<Weibo> list = new ArrayList<Weibo>();
		Weibo weibo1 = new Weibo();

		weibo1.setWid("947A7F3AE74B05B529B66B5011A8695F");

		weibo1.setContent("12306事件");

		weibo1.setCommentCount(222);

		weibo1.setForwardCount(111111);

		weibo1.setAuthorID(3297540145L);

		weibo1.setFromText("官方微博");

		weibo1.setLink("http://weibo.com/ptteng");

		weibo1.setPublishAt(3L);

		weibo1.setStatus(0);

		weibo1.setForwardID("947A7F3AE74B05B529B66B5011A8695F");

		list.add(weibo1);
		Weibo weibo2 = new Weibo();

		weibo2.setWid("947A7F3AE74B05B529B66B5011A8695E");

		weibo2.setContent("刘老太事件");

		weibo2.setCommentCount(333333);

		weibo2.setForwardCount(2223);

		weibo2.setAuthorID(3297540142L);

		weibo2.setFromText("官方微博");

		weibo2.setLink("http://weibo.com/ptteng");

		weibo2.setPublishAt(4L);

		weibo2.setStatus(0);

		weibo2.setForwardID("947A7F3AE74B05B529B66B5011A8695F");

		list.add(weibo2);
		List<Weibo> insertResults = this.weiboService.insertList(list);

		List<Long> lists = weiboService.getWeiboIdsByStatus(0, 0,
				Integer.MAX_VALUE);
		// TODO 增加自己的验证逻辑
		Assert.assertNotNull(lists);

		for (Weibo o : insertResults) {
			this.weiboService.delete(o.getId());
		}

	};

	@Test
	public void testNULL() throws ServiceException, ServiceDaoException {

	};
}

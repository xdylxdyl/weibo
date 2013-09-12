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
import com.gemantic.gemantic.weibo.model.News;
import com.gemantic.gemantic.weibo.service.NewsService;
@Ignore
public class NewsServiceTest {

	private static final Log log = LogFactory.getLog(NewsServiceTest.class);

	private NewsService newsService;

	@Before
	public void setUp() throws Exception {

		// dao
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext-server.xml");
		newsService = (NewsService) context.getBean("newsService");
		// local server
		/**
		 * newsService = (NewsService)
		 * Naming.lookup("//localhost:8801/NewsRMIService");
		 **/

		/**
		 * test client ApplicationContext context = new
		 * ClassPathXmlApplicationContext
		 * ("classpath:META-INF/spring/applicationContext-sca.xml"); newsService
		 * = (NewsService) context.getBean("newsService");
		 **/

	}

	 @Test
	public void testCRUD() throws ServiceException, ServiceDaoException {

		// 1.增加

		News news = new News();

		news.setNid("947A7F3AE74B05B529B66B5011A8695F");

		news.setTitle("12306事件");

		news.setLink("http://weibo.com/ptteng");

		news.setStatus(0);

		Long id = this.newsService.insert(news);

		news = this.newsService.getObjectById(id);

		News news2 = this.newsService.getObjectById(id);
		Assert.assertNotNull(news2);

		// 2. 更改
		news.setNid("947A7F3AE74B05B529B66B5011A8695E");
		news.setTitle("刘老太事件");
		news.setLink("http://weibo.com/ptteng");
		news.setStatus(0);
		boolean success = this.newsService.update(news);
		Assert.assertEquals(true, success);
		News news3 = this.newsService.getObjectById(id);
		// 3.删除
		boolean successDelete = this.newsService.delete(id);
		Assert.assertEquals(true, success);
		News news4 = this.newsService.getObjectById(id);
		Assert.assertNull(news4);

		// 4.batchInsert
		List<News> list = new ArrayList<News>();
		News news5 = new News();

		news5.setNid("947A7F3AE74B05B529B66B5011A8695F");

		news5.setTitle("12306事件");

		news5.setLink("http://weibo.com/ptteng");

		news5.setStatus(0);

		list.add(news5);
		News news6 = new News();

		news6.setNid("947A7F3AE74B05B529B66B5011A8695E");

		news6.setTitle("刘老太事件");

		news6.setLink("http://weibo.com/ptteng");

		news6.setStatus(0);

		list.add(news6);
		List<News> insertResults = this.newsService.insertList(list);
		Assert.assertEquals(2, insertResults.size());
		// 5.batchGet
		List<Long> ids = new ArrayList<Long>();
		for (News o : insertResults) {
			ids.add(o.getId());
		}

		List<News> getResults = this.newsService.getObjectsByIds(ids);
		Assert.assertEquals(2, getResults.size());

		for (News o : insertResults) {
			this.newsService.delete(o.getId());
		}

		// 6.batchUpdate

	}

	// @Test
	public void getNewsIdsByStatus() throws ServiceException,
			ServiceDaoException {
		List<News> list = new ArrayList<News>();
		News news1 = new News();

		news1.setNid("947A7F3AE74B05B529B66B5011A8695F");

		news1.setTitle("12306事件");

		news1.setLink("http://weibo.com/ptteng");

		news1.setStatus(0);

		list.add(news1);
		News news2 = new News();

		news2.setNid("947A7F3AE74B05B529B66B5011A8695E");

		news2.setTitle("刘老太事件");

		news2.setLink("http://weibo.com/ptteng");

		news2.setStatus(0);

		list.add(news2);
		List<News> insertResults = this.newsService.insertList(list);

		List<Long> lists = newsService.getNewsIdsByStatus(0, 0,
				Integer.MAX_VALUE);
		// TODO 增加自己的验证逻辑
		Assert.assertNotNull(lists);

		for (News o : insertResults) {
			this.newsService.delete(o.getId());
		}

	};

	@Test
	public void testNULL() throws ServiceException, ServiceDaoException {

	};
}

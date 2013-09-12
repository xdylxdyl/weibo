package com.gemantic.gemantic.weibo.service.impl;

import java.util.List;
import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Before;
import java.rmi.Naming;

import com.gemantic.gemantic.weibo.model.Event;
import com.gemantic.gemantic.weibo.service.EventService;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;

public class EventServiceTest {

	private static final Log log = LogFactory.getLog(EventServiceTest.class);

	private EventService eventService;

	 @Before
	public void setUp() throws Exception {

		// dao
		/*ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext-server.xml");
		eventService = (EventService) context.getBean("eventService");*/
		// local server
		
		  eventService = (EventService)
		  Naming.lookup("//112.124.47.234:8801/EventRMIService");
		

		/**
		 * test client ApplicationContext context = new
		 * ClassPathXmlApplicationContext
		 * ("classpath:META-INF/spring/applicationContext-sca.xml"); eventService
		 * = (EventService) context.getBean("eventService");
		 **/

	}

	 @Test
	public void testCRUD() throws ServiceException, ServiceDaoException {

		// 1.增加

		Event event = new Event();

		event.setTitle("12306事件");

		event.setSummary("12306带来了成大的影响");

		event.setSource("weibo");

		event.setStart_at(1L);

		event.setEnd_at(1L);

		event.setEventKeyword("weibo");

		event.setEventQuery("weibo");

		Long id = this.eventService.insert(event);

		event = this.eventService.getObjectById(id);

		Event event2 = this.eventService.getObjectById(id);
		Assert.assertNotNull(event2);

		// 2. 更改
		event.setTitle("刘老太事件");
		event.setSummary("刘老太事件,带来了巨大的影响");
		event.setSource("news");
		event.setStart_at(1L);
		event.setEnd_at(1L);
		event.setEventKeyword("weibo");
		event.setEventQuery("weibo");
		boolean success = this.eventService.update(event);
		Assert.assertEquals(true, success);
		Event event3 = this.eventService.getObjectById(id);
		// 3.删除
		boolean successDelete = this.eventService.delete(id);
		Assert.assertEquals(true, success);
		Event event4 = this.eventService.getObjectById(id);
		Assert.assertNull(event4);

		// 4.batchInsert
		List<Event> list = new ArrayList<Event>();
		Event event5 = new Event();

		event5.setTitle("12306事件");

		event5.setSummary("12306带来了成大的影响");

		event5.setSource("weibo");

		event5.setStart_at(1L);

		event5.setEnd_at(1L);

		event5.setEventKeyword("weibo");

		event5.setEventQuery("weibo");

		list.add(event5);
		Event event6 = new Event();

		event6.setTitle("刘老太事件");

		event6.setSummary("刘老太事件,带来了巨大的影响");

		event6.setSource("news");

		event6.setStart_at(1L);

		event6.setEnd_at(1L);

		event6.setEventKeyword("weibo");

		event6.setEventQuery("weibo");

		list.add(event6);
		List<Event> insertResults = this.eventService.insertList(list);
		Assert.assertEquals(2, insertResults.size());
		// 5.batchGet
		List<Long> ids = new ArrayList<Long>();
		for (Event o : insertResults) {
			ids.add(o.getId());
		}

		List<Event> getResults = this.eventService.getObjectsByIds(ids);
		Assert.assertEquals(2, getResults.size());

		for (Event o : insertResults) {
			//this.eventService.delete(o.getId());
		}

		// 6.batchUpdate

	}

	@Test
	public void testNULL() throws ServiceException, ServiceDaoException {

	};
	@Test
	public void testList() throws ServiceException, ServiceDaoException{
		List<Long> events=this.eventService.getAllEvent(0, 10);
		List<Event> results=this.eventService.getObjectsByIds(events);
		log.info(results);
	}
	
	
	
}

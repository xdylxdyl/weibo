package com.gemantic.gemantic.weibo.service.impl;

import java.net.URISyntaxException;
import java.rmi.Naming;
import java.util.List;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gemantic.brand.company.model.MicroBlogDoc;
import com.gemantic.brand.company.service.BrandCompanyService;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.gemantic.weibo.etl.NewsEtl;
import com.gemantic.gemantic.weibo.etl.WeiboEtl;
import com.gemantic.gemantic.weibo.model.CompanyEvent;
import com.gemantic.gemantic.weibo.model.CompanyExtendevent;
import com.gemantic.gemantic.weibo.model.CompanyNews;
import com.gemantic.gemantic.weibo.model.Weibo;
import com.gemantic.gemantic.weibo.service.NewsService;
import com.gemantic.gemantic.weibo.service.WeiboService;
import com.gemantic.gemantic.weibo.util.HttpClientUtil;
import com.gemantic.gemantic.weibo.util.WeiboUtil;
import com.gemantic.parser.model.Article;
import com.gemantic.parser.model.HtmlContent;

@Ignore
public class NewsETLTest {

	private static final Log log = LogFactory.getLog(NewsETLTest.class);

	private NewsEtl newsEtl;
	private WeiboEtl weiboEtl;
	private BrandCompanyService brandCompanyService;
	private NewsService newsService;
	private WeiboService weiboService;

	@Before
	public void setUp() throws Exception {

		// dao
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext*.xml");
		newsEtl = (NewsEtl) context.getBean("newsEtl");
		weiboEtl = (WeiboEtl) context.getBean("weiboEtl");
		// local server
		/*
		 * newsService = (NewsService) Naming
		 * .lookup("//112.124.47.234:8801/NewsRMIService");
		 */
		newsService = (NewsService) context.getBean("newsService");

		brandCompanyService = (BrandCompanyService) Naming
				.lookup("//112.124.32.78:8454/BrandCompanyRMIService");
		newsEtl.setBrandCompanyService(brandCompanyService);
		weiboEtl.setBrandCompanyService(brandCompanyService);
		weiboService = (WeiboService) context.getBean("weiboService");
		/*
		 * weiboService = (WeiboService) Naming
		 * .lookup("//112.124.47.234:8801/WeiboRMIService");
		 */

		/**
		 * test client ApplicationContext context = new
		 * ClassPathXmlApplicationContext
		 * ("classpath:META-INF/spring/applicationContext-sca.xml");
		 * eventService = (EventService) context.getBean("eventService");
		 **/

	}

	@Test
	public void testProcess() throws ServiceException, ServiceDaoException {

		Weibo weibo = this.weiboService.getObjectById(34L);
		log.info(weibo);
		MicroBlogDoc mbd = new MicroBlogDoc();
		mbd.setText(weibo.getContent());
		mbd.setAuther(String.valueOf(weibo.getAuthorID()));
		mbd.setGuid(weibo.getWid());
		mbd.setDate(weibo.getPublishAt());
		mbd.setCount(weibo.getForwardCount());
		MicroBlogDoc result = this.brandCompanyService
				.getMicroBlogCategory(mbd);
		if (result == null) {
			log.info(weibo.getWid() + " not get any result ");

		} else {
			log.info(weibo.getWid() + "get category " + result);
		}

		WeiboUtil.doc2Events(result, null);
		log.info("process " + result);

		List<CompanyEvent> companyEvents = WeiboUtil.doc2CompanyEvents(result);
		log.info(weibo.getWid() + " get company events " + companyEvents.size());

		List<CompanyExtendevent> companyExtendevents = WeiboUtil
				.doc2CompanyExtendEvents(result);

		log.info(weibo.getWid() + " get companyExtendevents  "
				+ companyExtendevents.size());

		List<CompanyNews> companyNews = WeiboUtil.doc2CompanyNews(result,
				"weibo");
		log.info(weibo.getWid() + " get companyNews  " + companyNews.size());

	}

	@Test
	public void testNewsEtl() throws ServiceException, ServiceDaoException,
			InterruptedException {

		newsEtl.process();

	}

	@Test
	public void testWeiboEtl() throws ServiceException, ServiceDaoException,
			InterruptedException {

		weiboEtl.process();

	}

	@Test
	public void testHtmlParse() throws URIException, URISyntaxException {

		String link = "http://news.hexun.com/2013-09-09/157857509.html";
		log.info(link);
		String pageContent = HttpClientUtil.get(link);
		HtmlContent tc = new HtmlContent();
		tc.setContent(pageContent);
		tc.setUrl(link);
		tc.setAnchor(link);
		tc.setFetchTime(System.currentTimeMillis());
		Article article = newsEtl.getNewsParser().parseContent(tc);

		log.info(article.getContent());

	}
}

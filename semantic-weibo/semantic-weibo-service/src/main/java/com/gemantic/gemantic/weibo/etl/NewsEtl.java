package com.gemantic.gemantic.weibo.etl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import com.gemantic.brand.company.model.NewsDoc;
import com.gemantic.brand.company.service.BrandCompanyService;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.common.util.FileUtil;
import com.gemantic.common.util.MyTimeUtil;
import com.gemantic.gemantic.weibo.model.CompanyEvent;
import com.gemantic.gemantic.weibo.model.CompanyExtendevent;
import com.gemantic.gemantic.weibo.model.CompanyExtendnews;
import com.gemantic.gemantic.weibo.model.CompanyNews;
import com.gemantic.gemantic.weibo.model.News;
import com.gemantic.gemantic.weibo.service.CompanyEventService;
import com.gemantic.gemantic.weibo.service.CompanyExtendeventService;
import com.gemantic.gemantic.weibo.service.CompanyExtendnewsService;
import com.gemantic.gemantic.weibo.service.CompanyNewsService;
import com.gemantic.gemantic.weibo.service.EventService;
import com.gemantic.gemantic.weibo.service.NewsMongoDBService;
import com.gemantic.gemantic.weibo.service.NewsService;
import com.gemantic.gemantic.weibo.util.HttpClientUtil;
import com.gemantic.gemantic.weibo.util.NewsUtil;
import com.gemantic.gemantic.weibo.util.WeiboUtil;
import com.gemantic.parser.Parser;
import com.gemantic.parser.model.Article;
import com.gemantic.parser.model.HtmlContent;

public class NewsEtl {

	private static final Log log = LogFactory.getLog(NewsEtl.class);

	private NewsService newsService;

	private NewsMongoDBService newsMongoDBService;

	private EventService eventService;

	private CompanyEventService companyEventService;

	private CompanyExtendeventService companyExtendeventService;

	private CompanyExtendnewsService companyExtendnewsService;

	private CompanyNewsService companyNewsService;

	private BrandCompanyService brandCompanyService;

	private Parser newsParser;

	public NewsEtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void process() throws InterruptedException {

		// 1.get weib of news

		while (true) {

			try {
				processNews();
			} catch (Throwable t) {
				t.printStackTrace();
				log.error("process error ,sleep 5 min " + t.getMessage());
				Thread.sleep(5 * 60 * 1000L);
			}

		}

	}

	private void processNews() throws ServiceException, ServiceDaoException,
			InterruptedException, URIException, URISyntaxException {
		List<News> newss = getWeiBo();
		if (CollectionUtils.isEmpty(newss)) {
			log.info("news not get data sleep 5");
			Thread.sleep(5000);
		} else {
			log.info("news get data is " + newss.size());
			// 2.判断是否为原始微博
			List<News> processNewss = new ArrayList();
			for (News news : newss) {
				log.info(news.getNid()+"start process "+news.getLink());
				news.setStatus(1);
				if(StringUtils.isBlank(news.getLink())){
					continue;
				}
				String pageContent;
				try {
					pageContent = HttpClientUtil.get(news.getLink());
					if(StringUtils.isBlank(pageContent)){
						continue;
					}

					HtmlContent tc = new HtmlContent();
					tc.setContent(pageContent);
					tc.setUrl(news.getLink());
					tc.setAnchor(news.getLink());
					tc.setFetchTime(news.getCreateAt());
					Article article = this.parseContent(tc);

					if (article != null) {

						news.setAuthor(article.getAuthor());
						news.setPublish_at(article.getTimeLong());
						news.setSource(article.getSource());
						news.setTitle(article.getHtmlTitle());						
						news.setContent(article.getContent());
						log.info(news);
					    NewsUtil.fixParserError(news,pageContent);
						log.info(news.getLink()+" ================== "+(MyTimeUtil.convertLong2String(news.getPublish_at(), "yyyy-MM-dd HH:mm:ss")));
				
					}
					// 原创
					// 2.1 插入MongoDB
					this.newsMongoDBService.insert(news);

					NewsDoc newsDoc = new NewsDoc();
					newsDoc.setAuther(news.getAuthor());
					newsDoc.setDate(news.getPublish_at());
					newsDoc.setSource(news.getLink());
					newsDoc.setText(news.getContent());
					newsDoc.setTitle(news.getTitle());
					newsDoc.setGuid(news.getNid());
					NewsDoc result = this.brandCompanyService
							.getNewsCategory(newsDoc);
					
					if (result == null) {
						log.info(news.getLink() + " news  not get any result "
								+ news.getNid());
						continue;
					} else {
						// log.info(news.getWid()+"get category "+result);
					}
					List<String> keywords = NewsUtil.doc2Keywords(result);
					news.setKeywords(keywords);
					this.newsMongoDBService.update(news);
					log.info("news update keywords " + news.getKeywords());

					NewsUtil.doc2Events(result, eventService);
					log.info("news  process " + result);

					List<CompanyEvent> companyEvents = NewsUtil
							.doc2CompanyEvents(result);
					this.companyEventService.insertList(companyEvents);
					log.info(news.getLink() + " news  get company events "
							+ companyEvents.size() + " md5 is " + news.getNid());

					List<CompanyExtendevent> companyExtendevents = NewsUtil
							.doc2CompanyExtendEvents(result);
					this.companyExtendeventService
							.insertList(companyExtendevents);
					log.info(news.getLink()
							+ " news  get companyExtendevents  "
							+ companyExtendevents.size() + " md5 is "
							+ news.getNid());

					List<CompanyNews> companyNews = NewsUtil.doc2CompanyNews(
							result, "news");
					this.companyNewsService.insertList(companyNews);
					log.info(news.getLink() + " news  get companyNews  "
							+ companyNews.size() + " md5 is " + news.getNid());

					List<CompanyExtendnews> companyExtendnews = NewsUtil
							.doc2CompanyExtendnews(result);
					this.companyExtendnewsService.insertList(companyExtendnews);

					log.info(news.getLink() + " news  get companyExtendnews  "
							+ companyExtendnews.size());
				} catch (Throwable t) {
					t.printStackTrace();
					log.error(news.getLink() +" , id is "+news.getNid()+ " process error ,skip "+t.getMessage());
					continue;
				}

			}
			log.info("news  will process newss " + processNewss.size());
			// 3.调用NLP模块

			log.info("news  start update status");
			this.newsService.updateList(newss);

			log.info("news  process over ");

		}
	}

	private Article parseContent(HtmlContent tc) {
		try {
			return newsParser.parseContent(tc);
		} catch (Throwable t) {
			t.printStackTrace();
			log.error(tc + " parse error ");
			return null;
		}
	}

	/**
	 * 获取最新的微博
	 * 
	 * @return
	 * @throws ServiceDaoException
	 * @throws ServiceException
	 */

	private List<News> getWeiBo() throws ServiceException, ServiceDaoException {
		List<Long> ids = this.newsService.getNewsIdsByStatus(0, 0, 100);
		log.info("get ids size " + ids);
		List<News> newss = this.newsService.getObjectsByIds(ids);
		return newss;
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public NewsMongoDBService getNewsMongoDBService() {
		return newsMongoDBService;
	}

	public void setNewsMongoDBService(NewsMongoDBService newsMongoDBService) {
		this.newsMongoDBService = newsMongoDBService;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public CompanyEventService getCompanyEventService() {
		return companyEventService;
	}

	public void setCompanyEventService(CompanyEventService companyEventService) {
		this.companyEventService = companyEventService;
	}

	public CompanyExtendeventService getCompanyExtendeventService() {
		return companyExtendeventService;
	}

	public void setCompanyExtendeventService(
			CompanyExtendeventService companyExtendeventService) {
		this.companyExtendeventService = companyExtendeventService;
	}

	public CompanyNewsService getCompanyNewsService() {
		return companyNewsService;
	}

	public void setCompanyNewsService(CompanyNewsService companyNewsService) {
		this.companyNewsService = companyNewsService;
	}

	public BrandCompanyService getBrandCompanyService() {
		return brandCompanyService;
	}

	public void setBrandCompanyService(BrandCompanyService brandCompanyService) {
		this.brandCompanyService = brandCompanyService;
	}

	public CompanyExtendnewsService getCompanyExtendnewsService() {
		return companyExtendnewsService;
	}

	public void setCompanyExtendnewsService(
			CompanyExtendnewsService companyExtendnewsService) {
		this.companyExtendnewsService = companyExtendnewsService;
	}

	public Parser getNewsParser() {
		return newsParser;
	}

	public void setNewsParser(Parser newsParser) {
		this.newsParser = newsParser;
	}

	public static void main(String[] args) throws IOException {

		List<String> keywords = FileUtil.readFileAsList("d:/data/keywords.txt");
		int i = 0;
		for (String keyword : keywords) {
			i++;
			String sql = "INSERT INTO `search_word` VALUES ('" + i + "', '"
					+ keyword + "', '1364309002676', '1364309002676');";
			FileUtil.writeFile("d:/data/keywords.sql", true, sql);
		}

	}
}

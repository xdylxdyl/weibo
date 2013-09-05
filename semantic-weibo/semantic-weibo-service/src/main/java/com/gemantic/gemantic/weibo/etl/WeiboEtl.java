package com.gemantic.gemantic.weibo.etl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import com.gemantic.brand.company.model.MicroBlogDoc;
import com.gemantic.brand.company.service.BrandCompanyService;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.gemantic.weibo.model.CompanyEvent;
import com.gemantic.gemantic.weibo.model.CompanyExtendevent;
import com.gemantic.gemantic.weibo.model.CompanyNews;
import com.gemantic.gemantic.weibo.model.Event;
import com.gemantic.gemantic.weibo.model.Weibo;
import com.gemantic.gemantic.weibo.service.CompanyEventService;
import com.gemantic.gemantic.weibo.service.CompanyExtendeventService;
import com.gemantic.gemantic.weibo.service.CompanyNewsService;
import com.gemantic.gemantic.weibo.service.EventService;
import com.gemantic.gemantic.weibo.service.WeiboMongoDBService;
import com.gemantic.gemantic.weibo.service.WeiboService;
import com.gemantic.gemantic.weibo.util.WeiboUtil;

public class WeiboEtl {

	private static final Log log = LogFactory.getLog(WeiboEtl.class);

	private WeiboService weiboService;

	private WeiboMongoDBService weiboMongoDBService;

	private EventService eventService;

	private CompanyEventService companyEventService;

	private CompanyExtendeventService companyExtendeventService;

	private CompanyNewsService companyNewsService;

	private BrandCompanyService brandCompanyService;

	public WeiboEtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void process() throws ServiceException, ServiceDaoException,
			InterruptedException {

		// 1.get weib of news

		while (true) {
			List<Weibo> weibos = getWeiBo();
			if (CollectionUtils.isEmpty(weibos)) {
				log.info("not get data sleep 5");
				Thread.sleep(5000);
			} else {
				log.info("get data is " + weibos.size());
				// 2.判断是否为原始微博
				List<Weibo> processWeibos = new ArrayList();
				for (Weibo weibo : weibos) {
					weibo.setStatus(1);
					if (StringUtils.isBlank(weibo.getForwardID())) {
						// 原创
						// 2.1 插入MongoDB
						this.weiboMongoDBService.insert(weibo);

					} else {
						// 2.2 是否需要更新转发次数

						Weibo old = this.weiboMongoDBService.get(weibo.getWid());
						if (old == null) {
							// 原有的微博不存在.跳过
							continue;
						} else {
							if (old.getForwardCount() >= weibo
									.getForwardCount()) {
								// 转发数无更新
								continue;
							} else {

								old.setForwardCount(weibo.getForwardCount());
								this.weiboMongoDBService.update(old);

							}

						}

					}
					processWeibos.add(weibo);

				}
				log.info("will process weibos " + processWeibos.size());
				// 3.调用NLP模块

				for (Weibo weibo : processWeibos) {
					MicroBlogDoc mbd = new MicroBlogDoc();
					mbd.setText(weibo.getContent());
					mbd.setAuther(String.valueOf(weibo.getAuthorID()));
					mbd.setGuid(weibo.getWid());
					MicroBlogDoc result = this.brandCompanyService
							.getMicroBlogCategory(mbd);

					List<Event> events = WeiboUtil.doc2Events(result);
					this.eventService.insertList(events);

					List<CompanyEvent> companyEvents = WeiboUtil
							.doc2CompanyEvents(result);
					this.companyEventService.insertList(companyEvents);

					List<CompanyExtendevent> companyExtendevents = WeiboUtil
							.doc2CompanyExtendEvents(result);
					this.companyExtendeventService
							.insertList(companyExtendevents);

					List<CompanyNews> companyNews = WeiboUtil.doc2CompanyNews(
							result, "weibo");
					this.companyNewsService.insertList(companyNews);

				}
				
				log.info("start update status");
				this.weiboService.updateList(weibos);
				
				
				log.info("process over ");

			}

		}

	}

	/**
	 * 获取最新的微博
	 * 
	 * @return
	 * @throws ServiceDaoException 
	 * @throws ServiceException 
	 */

	private List<Weibo> getWeiBo() throws ServiceException, ServiceDaoException {
		List<Long> ids=this.weiboService.getWeiboIdsByStatus(0, 0, 100);
		log.info("get ids size "+ids);
		List<Weibo> weibos=this.weiboService.getObjectsByIds(ids);
		return weibos;
	}

	public WeiboService getWeiboService() {
		return weiboService;
	}

	public void setWeiboService(WeiboService weiboService) {
		this.weiboService = weiboService;
	}

	public WeiboMongoDBService getWeiboMongoDBService() {
		return weiboMongoDBService;
	}

	public void setWeiboMongoDBService(WeiboMongoDBService weiboMongoDBService) {
		this.weiboMongoDBService = weiboMongoDBService;
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

}

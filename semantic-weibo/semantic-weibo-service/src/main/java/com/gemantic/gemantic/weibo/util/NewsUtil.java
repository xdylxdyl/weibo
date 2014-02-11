package com.gemantic.gemantic.weibo.util;



import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gemantic.brand.company.model.MicroBlogDoc;
import com.gemantic.brand.company.model.NewsDoc;
import com.gemantic.brand.company.model.RelatedCompany;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.common.util.MyTimeUtil;
import com.gemantic.gemantic.weibo.model.CompanyEvent;
import com.gemantic.gemantic.weibo.model.CompanyExtendevent;
import com.gemantic.gemantic.weibo.model.CompanyExtendnews;
import com.gemantic.gemantic.weibo.model.CompanyNews;
import com.gemantic.gemantic.weibo.model.Event;
import com.gemantic.gemantic.weibo.model.News;
import com.gemantic.gemantic.weibo.service.EventService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class NewsUtil {
	
	private static final Log log = LogFactory.getLog(NewsUtil.class);
	private static final String Split = " ";

	public static String news2Json(News news) {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(news);
	}

	public static News json2News(String json) {
		Gson gson = new GsonBuilder().create();
		News news = gson.fromJson(json, News.class);
		return news;

	}

	public static List<CompanyEvent> doc2CompanyEvents(NewsDoc result) {

		List<CompanyEvent> cevents = new ArrayList();
		if(result==null||result.getDocCategory()==null||result.getDocCategory().getEvent()==null){
			return new ArrayList();
		}
		for (String companyUri : result.getDocCategory().getEvent().getCompanies()) {
			CompanyEvent cevent = new CompanyEvent();
			cevent.setCompanyUri(companyUri);
			cevent.setEid(result.getDocCategory().getEvent().getId());
			cevent.setSource(NewsUtil.getSource(result.getSource()));
			cevent.setPublishAt(result.getDate());
			cevents.add(cevent);

		}
		return cevents;
	}

	private static String getSource(String source) {
		if(source.contains("guba.eastmoney.com")){
			return Event.Source_Forum;
		}else{
			return Event.Source_News;
		}
	
	}

	public static List<CompanyExtendevent> doc2CompanyExtendEvents(
			NewsDoc result) {
		if(result==null||result.getDocCategory()==null||result.getDocCategory().getEvent()==null){
			return new ArrayList();
		}
		List<CompanyExtendevent> cevents = new ArrayList();
		for (RelatedCompany rcompany : result.getDocCategory().getEvent()
				.getRelatedCompanies()) {
			CompanyExtendevent cevent = new CompanyExtendevent();
			cevent.setCompanyUri(rcompany.getCompany());
			cevent.setEid(result.getDocCategory().getEvent().getId());
			cevent.setSource(NewsUtil.getSource(result.getSource()));
			cevent.setEntityUri(rcompany.getUri());
		    cevent.setType(rcompany.getType());
		    cevent.setPublishAt(result.getDate());
			cevents.add(cevent);

		}
		return cevents;
	}

	public static List<CompanyNews> doc2CompanyNews(NewsDoc result,
			String string) {
		if(result==null||result.getDocCategory()==null){
			return new ArrayList();
		}
		List<CompanyNews> cevents = new ArrayList();
		for (String companyUri : result.getDocCategory().getCompanies()) {
			CompanyNews cevent = new CompanyNews();
			cevent.setCompanyUri(companyUri);
			cevent.setAnalyse(result.getDocCategory().getEmotion());
			cevent.setSource(NewsUtil.getSource(result.getSource()));
			cevent.setCount(1);
			cevent.setNid(result.getGuid());
			cevent.setPublishAt(result.getDate());
		
			cevents.add(cevent);

		}
		return cevents;
	}

	public static void doc2Events(NewsDoc result, EventService eventService)
			throws ServiceException, ServiceDaoException {
		if(result==null||result.getDocCategory()==null||result.getDocCategory().getEvent()==null){
			return ;
		}
		com.gemantic.brand.company.model.Event e = result.getDocCategory()
				.getEvent();

		Event event = new Event();

		event.setEventKeyword(e.getKeywords().toString());

		event.setSource(NewsUtil.getSource(result.getSource()));
		event.setStart_at(e.getStart());
		event.setSummary(e.getSummary());
		event.setTitle(NewsUtil.listToString(e.getKeywords()));
		Long id = eventService.insert(event);
		e.setId(id);

	}

	private static String listToString(List<String> keywords) {
		StringBuffer sb = new StringBuffer();
		int index = 0;
		for (String str : keywords) {
			sb.append(str);
			if (index >= keywords.size() - 1) {
				break;
			} else {
				sb.append(NewsUtil.Split);
			}
			;

		}
		return sb.toString();
	}
	
	public static List<CompanyExtendnews> doc2CompanyExtendnews(
			NewsDoc result) {
		if(result==null||result.getDocCategory()==null){
			return new ArrayList();
		}
		List<CompanyExtendnews> cevents = new ArrayList();
		for (RelatedCompany relatedCompany : result.getDocCategory().getRelatedCompanies()) {
			CompanyExtendnews cnews = new CompanyExtendnews();
			cnews.setCompanyUri(relatedCompany.getCompany());
			cnews.setEid(result.getGuid());
			cnews.setEntityUri(relatedCompany.getUri());
			cnews.setSource(NewsUtil.getSource(result.getSource()));
			cnews.setType(relatedCompany.getType());
			cnews.setPublishAt(result.getDate());
		
			cevents.add(cnews);
			log.info(result.getGuid()+" has company news "+relatedCompany);

		}
		return cevents;
	}
	
	public static List<String> doc2Keywords(NewsDoc result) {
		if(result==null||result.getDocCategory()==null||result.getDocCategory().getCharacters()==null){
			return new ArrayList();
		}
		return result.getDocCategory().getCharacters();
	}

	public static void fixParserError(News news,String pageContent) {
		
		String link = news.getLink();
		if(link.contains("news.10jqka.com.cn/comment")){
			log.info("fix parse "+link);		

			Document doc =Jsoup.parse(pageContent);
			String p = doc.select(".m_cont > div:eq(1)").text();
			log.info("get publisht is "+p);
			p=p.replace("文章时间：", "").trim();
			log.info("p "+p);
			Long publishAt;
			try {
				publishAt = MyTimeUtil.convertString2Long(p, "yyyy-MM-dd HH:mm:ss");
				news.setPublish_at(publishAt);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(e.getMessage());
				log.error(link+" parse publish at error");
			}
			
		}else{
			
		};
	
		

		
	}

}

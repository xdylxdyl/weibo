package com.gemantic.gemantic.weibo.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gemantic.brand.company.model.MicroBlogDoc;
import com.gemantic.brand.company.model.RelatedCompany;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.gemantic.weibo.model.CompanyEvent;
import com.gemantic.gemantic.weibo.model.CompanyExtendevent;
import com.gemantic.gemantic.weibo.model.CompanyNews;
import com.gemantic.gemantic.weibo.model.Event;
import com.gemantic.gemantic.weibo.model.Weibo;
import com.gemantic.gemantic.weibo.service.EventService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WeiboUtil {
	private static final Log log = LogFactory.getLog(WeiboUtil.class);
	private static final String Split = " ";

	public static String weibo2Json(Weibo weibo) {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(weibo);
	}

	public static Weibo json2Weibo(String json) {
		Gson gson = new GsonBuilder().create();
		Weibo weibo = gson.fromJson(json, Weibo.class);
		return weibo;

	}

	public static List<CompanyEvent> doc2CompanyEvents(MicroBlogDoc result) {

		List<CompanyEvent> cevents = new ArrayList();
		if(result==null||result.getDocCategory()==null||result.getDocCategory().getEvent()==null){
			return new ArrayList();
		}
		for (String companyUri : result.getDocCategory().getEvent().getCompanies()) {
			CompanyEvent cevent = new CompanyEvent();
			cevent.setCompanyUri(companyUri);
			cevent.setEid(result.getDocCategory().getEvent().getId());
			cevent.setSource(Event.Source_Weibo);
			cevents.add(cevent);

		}
		return cevents;
	}

	public static List<CompanyExtendevent> doc2CompanyExtendEvents(
			MicroBlogDoc result) {
		if(result==null||result.getDocCategory()==null||result.getDocCategory().getEvent()==null){
			return new ArrayList();
		}
		List<CompanyExtendevent> cevents = new ArrayList();
		for (RelatedCompany rcompany : result.getDocCategory().getEvent()
				.getRelatedCompanies()) {
			CompanyExtendevent cevent = new CompanyExtendevent();
			cevent.setCompanyUri(rcompany.getCompany());
			cevent.setEid(result.getDocCategory().getEvent().getId());
			cevent.setSource(Event.Source_Weibo);
		    cevent.setType(rcompany.getType());
			cevents.add(cevent);

		}
		return cevents;
	}

	public static List<CompanyNews> doc2CompanyNews(MicroBlogDoc result,
			String string) {
		if(result==null||result.getDocCategory()==null||result.getDocCategory().getEvent()==null){
			return new ArrayList();
		}
		List<CompanyNews> cevents = new ArrayList();
		for (String companyUri : result.getDocCategory().getCompanies()) {
			CompanyNews cevent = new CompanyNews();
			cevent.setCompanyUri(companyUri);
			cevent.setAnalyse(result.getDocCategory().getEmotion());
			cevent.setSource(Event.Source_Weibo);
			cevent.setCount(result.getCount());
			cevent.setNid(result.getGuid());
			
			cevents.add(cevent);

		}
		return cevents;
	}

	public static void doc2Events(MicroBlogDoc result, EventService eventService)
			throws ServiceException, ServiceDaoException {
		if(result==null||result.getDocCategory()==null||result.getDocCategory().getEvent()==null){
			return ;
		}
		com.gemantic.brand.company.model.Event e = result.getDocCategory()
				.getEvent();

		Event event = new Event();

		event.setEventKeyword(e.getKeywords().toString());

		event.setSource(Event.Source_Weibo);
		event.setStart_at(e.getStart());
		event.setSummary(e.getSummary());
		event.setTitle(WeiboUtil.listToString(e.getKeywords()));
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
				sb.append(WeiboUtil.Split);
			}
			;

		}
		return sb.toString();
	}

}

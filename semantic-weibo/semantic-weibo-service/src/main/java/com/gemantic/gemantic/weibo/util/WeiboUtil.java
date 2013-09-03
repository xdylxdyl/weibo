package com.gemantic.gemantic.weibo.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gemantic.brand.company.model.MicroBlogDoc;
import com.gemantic.gemantic.weibo.model.CompanyEvent;
import com.gemantic.gemantic.weibo.model.CompanyExtendevent;
import com.gemantic.gemantic.weibo.model.CompanyNews;
import com.gemantic.gemantic.weibo.model.Event;
import com.gemantic.gemantic.weibo.model.Weibo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WeiboUtil {
	private static final Log log = LogFactory.getLog(WeiboUtil.class);


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
		// TODO Auto-generated method stub
		return new ArrayList();
	}

	public static List<CompanyExtendevent> doc2CompanyExtendEvents(
			MicroBlogDoc result) {
		// TODO Auto-generated method stub
		return new ArrayList();
	}

	public static List<CompanyNews> doc2CompanyNews(MicroBlogDoc result,
			String string) {
		// TODO Auto-generated method stub
		return new ArrayList();
	}

	public static List<Event> doc2Events(MicroBlogDoc result) {
		// TODO Auto-generated method stub
		return new ArrayList();
	}
	
	

}

package com.gemantic.gemantic.weibo.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	
	

}

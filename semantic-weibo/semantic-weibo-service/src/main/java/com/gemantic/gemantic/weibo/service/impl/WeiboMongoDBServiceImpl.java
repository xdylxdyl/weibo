package com.gemantic.gemantic.weibo.service.impl;

import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.gemantic.weibo.model.Weibo;
import com.gemantic.gemantic.weibo.service.WeiboMongoDBService;
import com.gemantic.gemantic.weibo.util.WeiboUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

public class WeiboMongoDBServiceImpl implements WeiboMongoDBService {

	private static final Log log = LogFactory
			.getLog(WeiboMongoDBServiceImpl.class);

	private DBCollection collection;
	private String ip = "112.124.47.234";
	private int port = 27017;

	public WeiboMongoDBServiceImpl() {
		super();

		Mongo mongo;
		try {
			mongo = new Mongo(this.ip, this.port);
			// 连接名为yourdb的数据库，假如数据库不存在的话，mongodb会自动建立
			DB db = mongo.getDB("semantic");
			// Get collection from MongoDB, database named "yourDB"
			// 从Mongodb中获得名为yourColleection的数据集合，如果该数据集合不存在，Mongodb会为其新建立

			this.collection = db.getCollection("weibo");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean insert(Weibo weibo) throws ServiceException,
			ServiceDaoException {
		Weibo old = this.get(weibo.getWid());
		if (old == null) {
			DBObject dbObject = (DBObject) JSON.parse(WeiboUtil
					.weibo2Json(weibo));
			collection.insert(dbObject);
			return true;
		} else {
			log.error("already exist " + weibo);
			return false;
		}

	}

	@Override
	public boolean delete(String id) throws ServiceException,
			ServiceDaoException {
		Weibo old = this.get(id);
		if (old == null) {
			log.warn("delete cant complete.not exist " + id);
			return true;
		} else {
			collection.remove(new BasicDBObject().append("id", id));
			return true;
		}

	}

	@Override
	public boolean update(Weibo weibo) throws ServiceException,
			ServiceDaoException {
		Weibo old = this.get(weibo.getWid());
		if (old == null) {
			log.error("not exist " + weibo);
			return false;

		} else {

			DBObject dbObject2 = (DBObject) JSON.parse(WeiboUtil
					.weibo2Json(weibo));
			collection.update(new BasicDBObject().append("id", weibo.getId()),
					dbObject2);
			return true;
		}

	}

	@Override
	public Weibo get(String id) throws ServiceException, ServiceDaoException {
		BasicDBObject query = new BasicDBObject();
		query.put("id", id);
		DBCursor cursor = collection.find(query);
		if (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			String json = JSON.serialize(dbObject);
			return WeiboUtil.json2Weibo(json);

		} else {
			return null;
		}

	}

	public DBCollection getCollection() {
		return collection;
	}

	public void setCollection(DBCollection collection) {
		this.collection = collection;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}

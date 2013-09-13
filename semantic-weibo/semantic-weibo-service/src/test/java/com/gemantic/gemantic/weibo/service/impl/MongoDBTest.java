package com.gemantic.gemantic.weibo.service.impl;

import java.net.UnknownHostException;
import java.rmi.Naming;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.gemantic.weibo.model.Weibo;
import com.gemantic.gemantic.weibo.service.WeiboMongoDBService;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

//@Ignore
public class MongoDBTest {
	private static final Log log = LogFactory.getLog(MongoDBTest.class);

	private WeiboMongoDBService weiboMongoDBService;

	@Before
	public void setUp() throws Exception {

		// dao
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext*.xml");
		weiboMongoDBService = (WeiboMongoDBService) context
				.getBean("weiboMongoDBService");
		// local server
/*
		weiboMongoDBService = (WeiboMongoDBService) Naming
				.lookup("//localhost:8801/WeiboRMIService");*/

		/**
		 * test client ApplicationContext context = new
		 * ClassPathXmlApplicationContext
		 * ("classpath:META-INF/spring/applicationContext-sca.xml");
		 * companyEventService = (CompanyEventService)
		 * context.getBean("companyEventService");
		 **/

	}

	@Test
	public void testCRUD() throws ServiceException, ServiceDaoException {
		Weibo weibo = new Weibo();		
		weibo.setWid("2xdylxdylxdylxdyl");
		weibo.setContent("12306事件");

		weibo.setCommentCount(222);

		weibo.setForwardCount(111111);

		weibo.setAuthorID(1658990180L);

	

		weibo.setFromText("官方微博");

		weibo.setLink("http://weibo.com/ptteng");

		weibo.setPublishAt(3L);

		boolean insertResult = this.weiboMongoDBService.insert(weibo);
		log.info("insert result " + insertResult);
		Weibo weibo2 = this.weiboMongoDBService.get(weibo.getWid());
		log.info("get weibo is " + weibo2);
		weibo2.setAuthorID(1658990180L);
		this.weiboMongoDBService.update(weibo2);
		Weibo weibo3 = this.weiboMongoDBService.get(weibo.getWid());
		log.info("get weibo update " + weibo3);
		this.weiboMongoDBService.delete(weibo.getWid());

		Weibo weibo4 = this.weiboMongoDBService.get(weibo.getWid());
		log.info("remove weib result is  " + weibo4);
	}

	public static void main(String[] args) throws UnknownHostException {

		Mongo mongo = new Mongo("112.124.47.234", 27017);
		// 连接名为yourdb的数据库，假如数据库不存在的话，mongodb会自动建立
		DB db = mongo.getDB("yourdb");
		// Get collection from MongoDB, database named "yourDB"
		// 从Mongodb中获得名为yourColleection的数据集合，如果该数据集合不存在，Mongodb会为其新建立
		DBCollection collection = db.getCollection("yourCollection");
		// 使用BasicDBObject对象创建一个mongodb的document,并给予赋值。
		/*
		 * BasicDBObject document = new BasicDBObject(); document.put("id",
		 * 1001); document.put("msg", "hello world mongoDB in Java"); //
		 * 将新建立的document保存到collection中去 collection.insert(document); //
		 * 创建要查询的document BasicDBObject searchQuery = new BasicDBObject();
		 * searchQuery.put("id", 1001); // 使用collection的find方法查找document
		 * 
		 * 
		 * log.info("success");
		 */

		
		  String json
		  ="{'id':4,'database' : 'mkyongDB','tableupdate' : 'hosting',"+
		  "'detail' : {'records' : 99, 'index' : 'vps_index1', 'active' : 'true'}}}"
		  ; DBObject dbObject =(DBObject)JSON.parse(json);
		  collection.insert(dbObject);
		 
		String json2 = "{'id':4,'database' : 'mkyongDB','tableupdate' : 'xdylxdyl','detail' : {'records' : 99, 'index' : 'vps_index1', 'active' : 'true'}}}";
		String json3 = "{'tableupdate':'xdylxdyl'}";
		DBObject dbObject2 = (DBObject) JSON.parse(json2);
		collection.update(new BasicDBObject().append("id", 4), dbObject2);

		log.info("insert json success");

		BasicDBObject query = new BasicDBObject();
		query.put("id", 4);
		DBCursor cursor = collection.find(query);
		while (cursor.hasNext()) {
			log.info(cursor.next());
		}
		// collection.remove(document);

	}
}

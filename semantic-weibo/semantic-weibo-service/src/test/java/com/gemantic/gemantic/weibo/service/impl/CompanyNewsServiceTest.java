package com.gemantic.gemantic.weibo.service.impl;

import java.util.List;
import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Before;
import java.rmi.Naming;

import com.gemantic.gemantic.weibo.model.CompanyNews;
import com.gemantic.gemantic.weibo.service.CompanyNewsService;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;

public class CompanyNewsServiceTest {

	private static final Log log = LogFactory.getLog(CompanyNewsServiceTest.class);
	
	private CompanyNewsService companyNewsService;
	
	
	//@Before
	    public void setUp() throws Exception{
		
		
		    //dao
	        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/semantic-weibo-service/applicationContext-server.xml");
	        companyNewsService = (CompanyNewsService) context.getBean("companyNewsService");
			//local server
			/**
			companyNewsService = (CompanyNewsService)  Naming.lookup("//localhost:8801/CompanyNewsRMIService");
			**/
			
			/** test client
			ApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/applicationContext-sca.xml");
	    	 companyNewsService = (CompanyNewsService) context.getBean("companyNewsService");
			
			**/
			
			
			
	    }
	
	
		
		
		
	

		
	//@Test
	public void testCRUD() throws ServiceException, ServiceDaoException{
	
	 // 1.增加
	 
	  	  CompanyNews companyNews  = new CompanyNews();	   
	   					 
			   					                companyNews.setCompanyUri("xxxx");
            
			   					                companyNews.setNid(333L);
            
			   					                companyNews.setSource("weibo");
            
			   					                companyNews.setAnalyse("none");
            
			   					                companyNews.setCount(300);
            
			   					 
			   					 
			   	 
	 
	  Long id= this.companyNewsService.insert(companyNews);

      companyNews = this.companyNewsService.getObjectById(id);

	  CompanyNews companyNews2=this.companyNewsService.getObjectById(id);
	    Assert.assertNotNull(companyNews2);
	  
		// 2. 更改 
				 		 				 				   companyNews.setCompanyUri("yyyy");
		    		 				 				   companyNews.setNid(444L);
		    		 				 				   companyNews.setSource("qq");
		    		 				 				   companyNews.setAnalyse("negative");
		    		 				 				   companyNews.setCount(400);
		    		 				 		 				 		 				boolean success=this.companyNewsService.update(companyNews);		
		Assert.assertEquals(true, success);
		 CompanyNews companyNews3=this.companyNewsService.getObjectById(id);
				 		 				             		 				             		 				             		 				             		 				             		 				 		 				 		 				//3.删除
		boolean successDelete=this.companyNewsService.delete(id);	
		Assert.assertEquals(true, success);
		CompanyNews companyNews4=this.companyNewsService.getObjectById(id);
		Assert.assertNull(companyNews4);
		
		//4.batchInsert
		 List<CompanyNews> list=new ArrayList<CompanyNews>();
	  	  CompanyNews companyNews5  = new CompanyNews();	   
	   					 
			   					                companyNews5.setCompanyUri("xxxx");
            
			   					                companyNews5.setNid(333L);
            
			   					                companyNews5.setSource("weibo");
            
			   					                companyNews5.setAnalyse("none");
            
			   					                companyNews5.setCount(300);
            
			   					 
			   					 
			   	    list.add(companyNews5);	   
	  	  CompanyNews companyNews6  = new CompanyNews();	   
	   					 
			   					                companyNews6.setCompanyUri("yyyy");
            
			   					                companyNews6.setNid(444L);
            
			   					                companyNews6.setSource("qq");
            
			   					                companyNews6.setAnalyse("negative");
            
			   					                companyNews6.setCount(400);
            
			   					 
			   					 
			   	   list.add(companyNews6);
	   List<CompanyNews> insertResults= this.companyNewsService.insertList(list);
	   Assert.assertEquals(2,insertResults.size());	
	   //5.batchGet
	   List<Long> ids=new ArrayList<Long>();
		for(CompanyNews o: insertResults){
		   ids.add(o.getId());
		}
				
	    List<CompanyNews> getResults= this.companyNewsService.getObjectsByIds(ids);
		Assert.assertEquals(2,getResults.size());
		
		
		 for(CompanyNews o :insertResults){
			this.companyNewsService.delete(o.getId());
}
			
		//6.batchUpdate
	   
		}
	
	
	
		
		@Test
	public void  testNULL()throws ServiceException, ServiceDaoException{
		
		
		
		};
}


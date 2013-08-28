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

import com.gemantic.gemantic.weibo.model.CompanyExtendevent;
import com.gemantic.gemantic.weibo.service.CompanyExtendeventService;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;

public class CompanyExtendeventServiceTest {

	private static final Log log = LogFactory.getLog(CompanyExtendeventServiceTest.class);
	
	private CompanyExtendeventService companyExtendeventService;
	
	
	//@Before
	    public void setUp() throws Exception{
		
		
		    //dao
	        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/semantic-weibo-service/applicationContext-server.xml");
	        companyExtendeventService = (CompanyExtendeventService) context.getBean("companyExtendeventService");
			//local server
			/**
			companyExtendeventService = (CompanyExtendeventService)  Naming.lookup("//localhost:8801/CompanyExtendeventRMIService");
			**/
			
			/** test client
			ApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/applicationContext-sca.xml");
	    	 companyExtendeventService = (CompanyExtendeventService) context.getBean("companyExtendeventService");
			
			**/
			
			
			
	    }
	
	
		
		
		
	

		
	//@Test
	public void testCRUD() throws ServiceException, ServiceDaoException{
	
	 // 1.增加
	 
	  	  CompanyExtendevent companyExtendevent  = new CompanyExtendevent();	   
	   					 
			   					                companyExtendevent.setCompanyUri("xxxx");
            
			   					                companyExtendevent.setEid(4L);
            
			   					                companyExtendevent.setSource("weibo");
            
			   					 
			   					 
			   	 
	 
	  Long id= this.companyExtendeventService.insert(companyExtendevent);

      companyExtendevent = this.companyExtendeventService.getObjectById(id);

	  CompanyExtendevent companyExtendevent2=this.companyExtendeventService.getObjectById(id);
	    Assert.assertNotNull(companyExtendevent2);
	  
		// 2. 更改 
				 		 				 				   companyExtendevent.setCompanyUri("yyyy");
		    		 				 				   companyExtendevent.setEid(3L);
		    		 				 				   companyExtendevent.setSource("qq");
		    		 				 		 				 		 				boolean success=this.companyExtendeventService.update(companyExtendevent);		
		Assert.assertEquals(true, success);
		 CompanyExtendevent companyExtendevent3=this.companyExtendeventService.getObjectById(id);
				 		 				             		 				             		 				             		 				 		 				 		 				//3.删除
		boolean successDelete=this.companyExtendeventService.delete(id);	
		Assert.assertEquals(true, success);
		CompanyExtendevent companyExtendevent4=this.companyExtendeventService.getObjectById(id);
		Assert.assertNull(companyExtendevent4);
		
		//4.batchInsert
		 List<CompanyExtendevent> list=new ArrayList<CompanyExtendevent>();
	  	  CompanyExtendevent companyExtendevent5  = new CompanyExtendevent();	   
	   					 
			   					                companyExtendevent5.setCompanyUri("xxxx");
            
			   					                companyExtendevent5.setEid(4L);
            
			   					                companyExtendevent5.setSource("weibo");
            
			   					 
			   					 
			   	    list.add(companyExtendevent5);	   
	  	  CompanyExtendevent companyExtendevent6  = new CompanyExtendevent();	   
	   					 
			   					                companyExtendevent6.setCompanyUri("yyyy");
            
			   					                companyExtendevent6.setEid(3L);
            
			   					                companyExtendevent6.setSource("qq");
            
			   					 
			   					 
			   	   list.add(companyExtendevent6);
	   List<CompanyExtendevent> insertResults= this.companyExtendeventService.insertList(list);
	   Assert.assertEquals(2,insertResults.size());	
	   //5.batchGet
	   List<Long> ids=new ArrayList<Long>();
		for(CompanyExtendevent o: insertResults){
		   ids.add(o.getId());
		}
				
	    List<CompanyExtendevent> getResults= this.companyExtendeventService.getObjectsByIds(ids);
		Assert.assertEquals(2,getResults.size());
		
		
		 for(CompanyExtendevent o :insertResults){
			this.companyExtendeventService.delete(o.getId());
}
			
		//6.batchUpdate
	   
		}
	
	
	
		
		@Test
	public void  testNULL()throws ServiceException, ServiceDaoException{
		
		
		
		};
}


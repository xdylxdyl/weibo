package com.gemantic.gemantic.weibo.service;

import org.osoa.sca.annotations.Remotable;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.gemantic.weibo.model.Weibo;

@Remotable
public interface WeiboMongoDBService {

	



   		public Weibo get(String id) throws ServiceException, ServiceDaoException;
		
		public boolean insert(Weibo weibo)throws ServiceException, ServiceDaoException;
		  
    	   
		
		
		public boolean delete(String id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean update(Weibo weibo)throws ServiceException, ServiceDaoException;
		  
    	   
		
		
		  
    	
	

		
	

}


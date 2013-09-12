package com.gemantic.gemantic.weibo.service;

import org.osoa.sca.annotations.Remotable;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.gemantic.weibo.model.News;

@Remotable
public interface NewsMongoDBService {

	



   		public News get(String id) throws ServiceException, ServiceDaoException;
   		
		
		public boolean insert(News weibo)throws ServiceException, ServiceDaoException;
		  
    	   
		
		
		public boolean delete(String id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean update(News weibo)throws ServiceException, ServiceDaoException;
		  
    	   
		
		
		  
    	
	

		
	

}


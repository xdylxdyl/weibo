package com.gemantic.gemantic.weibo.service;

import java.util.List;
import java.util.Map;

import org.osoa.sca.annotations.Remotable;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.gemantic.weibo.model.Weibo;

@Remotable
public interface WeiboService {

	



   		   
		
		public Long insert(Weibo weibo)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<Weibo> insertList(List<Weibo> weiboList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean delete(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean update(Weibo weibo)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean updateList(List<Weibo> weiboList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public Weibo getObjectById(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<Weibo> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException;
		
		
		
		  
    	
	

		
	

}


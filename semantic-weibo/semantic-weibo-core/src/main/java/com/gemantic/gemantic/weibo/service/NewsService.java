package com.gemantic.gemantic.weibo.service;

import java.util.List;

import org.osoa.sca.annotations.Remotable;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.gemantic.weibo.model.News;

@Remotable
public interface NewsService {

	



   		   
		
		public Long insert(News news)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<News> insertList(List<News> newsList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean delete(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean update(News news)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean updateList(List<News> newsList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public News getObjectById(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<News> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException;
		  
    	
	

			
			
	/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public List<Long>  getNewsIdsByStatus(int status,Integer start,Integer limit)throws ServiceException, ServiceDaoException;
		
	

}


package com.gemantic.gemantic.weibo.service;

import java.util.List;
import java.util.Map;

import org.osoa.sca.annotations.Remotable;

import com.gemantic.gemantic.weibo.model.Event;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;

@Remotable
public interface EventService {

	



   		   
		
		public Long insert(Event event)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<Event> insertList(List<Event> eventList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean delete(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean update(Event event)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean updateList(List<Event> eventList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public Event getObjectById(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<Event> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException;
		
		
		
		public List<Long> getAllEvent(Integer start, Integer limit)throws ServiceException, ServiceDaoException;
		
		
		public List<Long> getEventLikeKeyword(String keyword,Integer start,Integer limit)throws ServiceException, ServiceDaoException;
		
		  
    	
	

		
	

}


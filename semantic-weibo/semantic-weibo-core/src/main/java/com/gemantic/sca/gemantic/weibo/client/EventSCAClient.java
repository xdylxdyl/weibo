/**
 * 
 */
package com.gemantic.sca.gemantic.weibo.client;

import java.util.List;

import com.gemantic.gemantic.weibo.model.Event;
import com.gemantic.gemantic.weibo.service.EventService;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;

public class EventSCAClient implements EventService {

    private EventService eventService;

	public EventService getEventService() {
		return eventService;
	}
	
	
	public void setEventService(EventService eventService) {
		this.eventService =eventService;
	}
	
	
			   
		@Override
		public Long insert(Event event)throws ServiceException, ServiceDaoException{
		
		return eventService.insert(event);
		          
		
		}	
		  
    	   
		@Override
		public List<Event> insertList(List<Event> eventList)throws ServiceException, ServiceDaoException{
		
		return eventService.insertList(eventList);
		          
		
		}	
		  
    	   
		@Override
		public boolean delete(Long id)throws ServiceException, ServiceDaoException{
		
		return eventService.delete(id);
		          
		
		}	
		  
    	   
		@Override
		public boolean update(Event event)throws ServiceException, ServiceDaoException{
		
		return eventService.update(event);
		          
		
		}	
		  
    	   
		@Override
		public boolean updateList(List<Event> eventList)throws ServiceException, ServiceDaoException{
		
		return eventService.updateList(eventList);
		          
		
		}	
		  
    	   
		@Override
		public Event getObjectById(Long id)throws ServiceException, ServiceDaoException{
		
		return eventService.getObjectById(id);
		          
		
		}	
		  
    	   
		@Override
		public List<Event> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException{
		
		return eventService.getObjectsByIds(ids);
		          
		
		}	
		  
    	
	
	
	
	
		
	
	
    
	


 
}


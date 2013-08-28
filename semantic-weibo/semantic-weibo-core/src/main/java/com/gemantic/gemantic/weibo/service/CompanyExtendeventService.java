package com.gemantic.gemantic.weibo.service;

import java.util.List;
import java.util.Map;

import org.osoa.sca.annotations.Remotable;

import com.gemantic.gemantic.weibo.model.CompanyExtendevent;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;

@Remotable
public interface CompanyExtendeventService {

	



   		   
		
		public Long insert(CompanyExtendevent companyExtendevent)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<CompanyExtendevent> insertList(List<CompanyExtendevent> companyExtendeventList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean delete(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean update(CompanyExtendevent companyExtendevent)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean updateList(List<CompanyExtendevent> companyExtendeventList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public CompanyExtendevent getObjectById(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<CompanyExtendevent> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException;
		  
    	
	

		
	

}


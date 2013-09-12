package com.gemantic.gemantic.weibo.service;

import java.util.List;

import org.osoa.sca.annotations.Remotable;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.gemantic.weibo.model.CompanyExtendnews;

@Remotable
public interface CompanyExtendnewsService {

	



   		   
		
		public Long insert(CompanyExtendnews companyExtendnews)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<CompanyExtendnews> insertList(List<CompanyExtendnews> companyExtendnewsList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean delete(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean update(CompanyExtendnews companyExtendnews)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean updateList(List<CompanyExtendnews> companyExtendnewsList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public CompanyExtendnews getObjectById(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<CompanyExtendnews> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException;
		  
    	
	

		
	

}


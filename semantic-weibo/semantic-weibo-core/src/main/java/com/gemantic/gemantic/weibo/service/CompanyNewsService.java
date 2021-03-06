package com.gemantic.gemantic.weibo.service;

import java.util.List;
import java.util.Map;

import org.osoa.sca.annotations.Remotable;

import com.gemantic.gemantic.weibo.model.CompanyNews;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;

@Remotable
public interface CompanyNewsService {

	



   		   
		
		public Long insert(CompanyNews companyNews)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<CompanyNews> insertList(List<CompanyNews> companyNewsList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean delete(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean update(CompanyNews companyNews)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean updateList(List<CompanyNews> companyNewsList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public CompanyNews getObjectById(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<CompanyNews> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException;
		  
    	
	

		
	

}


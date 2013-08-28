/**
 * 
 */
package com.gemantic.sca.gemantic.weibo.client;

import java.util.List;

import com.gemantic.gemantic.weibo.model.CompanyNews;
import com.gemantic.gemantic.weibo.service.CompanyNewsService;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;

public class CompanyNewsSCAClient implements CompanyNewsService {

    private CompanyNewsService companyNewsService;

	public CompanyNewsService getCompanyNewsService() {
		return companyNewsService;
	}
	
	
	public void setCompanyNewsService(CompanyNewsService companyNewsService) {
		this.companyNewsService =companyNewsService;
	}
	
	
			   
		@Override
		public Long insert(CompanyNews companyNews)throws ServiceException, ServiceDaoException{
		
		return companyNewsService.insert(companyNews);
		          
		
		}	
		  
    	   
		@Override
		public List<CompanyNews> insertList(List<CompanyNews> companyNewsList)throws ServiceException, ServiceDaoException{
		
		return companyNewsService.insertList(companyNewsList);
		          
		
		}	
		  
    	   
		@Override
		public boolean delete(Long id)throws ServiceException, ServiceDaoException{
		
		return companyNewsService.delete(id);
		          
		
		}	
		  
    	   
		@Override
		public boolean update(CompanyNews companyNews)throws ServiceException, ServiceDaoException{
		
		return companyNewsService.update(companyNews);
		          
		
		}	
		  
    	   
		@Override
		public boolean updateList(List<CompanyNews> companyNewsList)throws ServiceException, ServiceDaoException{
		
		return companyNewsService.updateList(companyNewsList);
		          
		
		}	
		  
    	   
		@Override
		public CompanyNews getObjectById(Long id)throws ServiceException, ServiceDaoException{
		
		return companyNewsService.getObjectById(id);
		          
		
		}	
		  
    	   
		@Override
		public List<CompanyNews> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException{
		
		return companyNewsService.getObjectsByIds(ids);
		          
		
		}	
		  
    	
	
	
	
	
		
	
	
    
	


 
}


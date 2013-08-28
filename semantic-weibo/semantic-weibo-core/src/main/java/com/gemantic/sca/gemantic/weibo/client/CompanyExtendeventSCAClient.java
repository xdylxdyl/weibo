/**
 * 
 */
package com.gemantic.sca.gemantic.weibo.client;

import java.util.List;

import com.gemantic.gemantic.weibo.model.CompanyExtendevent;
import com.gemantic.gemantic.weibo.service.CompanyExtendeventService;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;

public class CompanyExtendeventSCAClient implements CompanyExtendeventService {

    private CompanyExtendeventService companyExtendeventService;

	public CompanyExtendeventService getCompanyExtendeventService() {
		return companyExtendeventService;
	}
	
	
	public void setCompanyExtendeventService(CompanyExtendeventService companyExtendeventService) {
		this.companyExtendeventService =companyExtendeventService;
	}
	
	
			   
		@Override
		public Long insert(CompanyExtendevent companyExtendevent)throws ServiceException, ServiceDaoException{
		
		return companyExtendeventService.insert(companyExtendevent);
		          
		
		}	
		  
    	   
		@Override
		public List<CompanyExtendevent> insertList(List<CompanyExtendevent> companyExtendeventList)throws ServiceException, ServiceDaoException{
		
		return companyExtendeventService.insertList(companyExtendeventList);
		          
		
		}	
		  
    	   
		@Override
		public boolean delete(Long id)throws ServiceException, ServiceDaoException{
		
		return companyExtendeventService.delete(id);
		          
		
		}	
		  
    	   
		@Override
		public boolean update(CompanyExtendevent companyExtendevent)throws ServiceException, ServiceDaoException{
		
		return companyExtendeventService.update(companyExtendevent);
		          
		
		}	
		  
    	   
		@Override
		public boolean updateList(List<CompanyExtendevent> companyExtendeventList)throws ServiceException, ServiceDaoException{
		
		return companyExtendeventService.updateList(companyExtendeventList);
		          
		
		}	
		  
    	   
		@Override
		public CompanyExtendevent getObjectById(Long id)throws ServiceException, ServiceDaoException{
		
		return companyExtendeventService.getObjectById(id);
		          
		
		}	
		  
    	   
		@Override
		public List<CompanyExtendevent> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException{
		
		return companyExtendeventService.getObjectsByIds(ids);
		          
		
		}	
		  
    	
	
	
	
	
		
	
	
    
	


 
}


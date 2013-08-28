package com.gemantic.gemantic.weibo.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.osoa.sca.annotations.Remotable;

import com.gemantic.gemantic.weibo.model.CompanyExtendevent;
import com.gemantic.gemantic.weibo.service.CompanyExtendeventService;



import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.dal.dao.Dao;
import com.gemantic.dal.dao.exception.DaoException;



public class CompanyExtendeventServiceImpl implements CompanyExtendeventService {

    private Dao dao;

	private static final Log log = LogFactory.getLog(CompanyExtendeventServiceImpl.class);

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}


		   
		@Override
		public Long insert(CompanyExtendevent companyExtendevent)throws ServiceException, ServiceDaoException{
		
	
		           if(log.isInfoEnabled()){	
    log.info(" insert data : " + companyExtendevent);
 }
		if (companyExtendevent == null) {
			return null;
		}

		long currentTimeMillis = System.currentTimeMillis();
		companyExtendevent.setCreateAt(currentTimeMillis);
		companyExtendevent.setUpdateAt(currentTimeMillis);

		Long result = null;
		try {
			result = (Long) dao.save(companyExtendevent);
		} catch (DaoException e) {
			log.error(" insert wrong : " + companyExtendevent);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
      if(log.isInfoEnabled()){
		log.info(" insert data success : " + result);
      }
return result;	
		}	
		  
    	   
		@Override
		public List<CompanyExtendevent> insertList(List<CompanyExtendevent> companyExtendeventList)throws ServiceException, ServiceDaoException{
		
	
		          	 if(log.isInfoEnabled()){
        log.info(" insert lists : " + (companyExtendeventList == null ? "null" : companyExtendeventList.size()));
      }
		List<CompanyExtendevent> resultList = null;

		if (CollectionUtils.isEmpty(companyExtendeventList)) {
			return new ArrayList<CompanyExtendevent>();
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (CompanyExtendevent companyExtendevent : companyExtendeventList) {
			companyExtendevent.setCreateAt(currentTimeMillis);
			companyExtendevent.setUpdateAt(currentTimeMillis);
		}

		try {
			resultList = (List<CompanyExtendevent>) dao.batchSave(companyExtendeventList);
		} catch (DaoException e) {
			log.error(" insert list wrong : " + companyExtendeventList);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
     if(log.isInfoEnabled()){
		log.info(" insert lists  success : " + (resultList == null ? "null" : resultList.size()));
      }
		return resultList;
		
		
			
		}	
		  
    	   
		@Override
		public boolean delete(Long id)throws ServiceException, ServiceDaoException{
		
	
		             if(log.isInfoEnabled()){
	    log.info(" delete data : " + id);
    }
		boolean result = false;

		if (id == null) {
			return true;
		}

		try {
			result = dao.delete(CompanyExtendevent.class, id);
		} catch (DaoException e) {
			log.error(" delete wrong : " + id);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
   if(log.isInfoEnabled()){
		log.info(" delete data success : " + id);
    }
		return result;
		
		}	
		  
    	   
		@Override
		public boolean update(CompanyExtendevent companyExtendevent)throws ServiceException, ServiceDaoException{
		
	
		          
	log.info(" update data : " + (companyExtendevent == null ? "null" : companyExtendevent.getId()));

		boolean result = false;

		if (companyExtendevent == null) {
			return true;
		}

		companyExtendevent.setUpdateAt(System.currentTimeMillis());

		try {
			result = dao.update(companyExtendevent);
		} catch (DaoException e) {
			log.error(" update wrong : " + companyExtendevent);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
       if(log.isInfoEnabled()){
		log.info(" update data success : " + companyExtendevent);
       }
		return result;	
		}	
		  
    	   
		@Override
		public boolean updateList(List<CompanyExtendevent> companyExtendeventList)throws ServiceException, ServiceDaoException{
		
	
		          log.info(" update lists : " + (companyExtendeventList == null ? "null" : companyExtendeventList.size()));

		boolean result = false;

		if (CollectionUtils.isEmpty(companyExtendeventList)) {
			return true;
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (CompanyExtendevent companyExtendevent : companyExtendeventList) {
			companyExtendevent.setUpdateAt(currentTimeMillis);
		}

		try {
			result = dao.batchUpdate(companyExtendeventList);
		} catch (DaoException e) {
			log.error(" update list wrong : " + companyExtendeventList);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
         if(log.isInfoEnabled()){
		log.info(" update lists success : " + companyExtendeventList.size());
         }
		return result;	
		}	
		  
    	   
		@Override
		public CompanyExtendevent getObjectById(Long id)throws ServiceException, ServiceDaoException{
		
	
		                 if(log.isInfoEnabled()){
        log.info(" get data : " + id);
       }
		CompanyExtendevent companyExtendevent = null;

		if (id == null) {
			return companyExtendevent;
		}

		try {
			companyExtendevent = (CompanyExtendevent) dao.get(CompanyExtendevent.class, id);
		} catch (DaoException e) {
			log.error(" get wrong : " + id);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
       if(log.isInfoEnabled()){
		log.info(" get data success : " + id);
        }
		return companyExtendevent;		
		}	
		  
    	   
		@Override
		public List<CompanyExtendevent> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException{
		
	
		          	  if(log.isInfoEnabled()){
	    log.info(" get lists : " + (ids == null ? "null" : ids));
      }
		List<CompanyExtendevent> companyExtendevent = null;

		if (CollectionUtils.isEmpty(ids)) {
			return new ArrayList<CompanyExtendevent>();
		}

		try {
			companyExtendevent = (List<CompanyExtendevent>) dao.getList(CompanyExtendevent.class, ids);
		} catch (DaoException e) {
			log.error(" get wrong : " + ids);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
     if(log.isInfoEnabled()){
		log.info(" get data success : " + (companyExtendevent == null ? "null" : companyExtendevent.size()));
     }
		return companyExtendevent;	
		}	
		  
    	
		
	
	
		
	

}


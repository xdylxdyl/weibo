package com.gemantic.gemantic.weibo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.dal.dao.Dao;
import com.gemantic.dal.dao.exception.DaoException;
import com.gemantic.gemantic.weibo.model.Weibo;
import com.gemantic.gemantic.weibo.service.WeiboService;



public class WeiboServiceImpl implements WeiboService {

    private Dao dao;

	private static final Log log = LogFactory.getLog(WeiboServiceImpl.class);

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}


		   
		@Override
		public Long insert(Weibo weibo)throws ServiceException, ServiceDaoException{
		
	
		           if(log.isInfoEnabled()){	
    log.info(" insert data : " + weibo);
 }
		if (weibo == null) {
			return null;
		}

		long currentTimeMillis = System.currentTimeMillis();
		weibo.setCreateAt(currentTimeMillis);
		weibo.setUpdateAt(currentTimeMillis);

		Long result = null;
		try {
			result = (Long) dao.save(weibo);
		} catch (DaoException e) {
			log.error(" insert wrong : " + weibo);
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
		public List<Weibo> insertList(List<Weibo> weiboList)throws ServiceException, ServiceDaoException{
		
	
		          	 if(log.isInfoEnabled()){
        log.info(" insert lists : " + (weiboList == null ? "null" : weiboList.size()));
      }
		List<Weibo> resultList = null;

		if (CollectionUtils.isEmpty(weiboList)) {
			return new ArrayList<Weibo>();
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (Weibo weibo : weiboList) {
			weibo.setCreateAt(currentTimeMillis);
			weibo.setUpdateAt(currentTimeMillis);
		}

		try {
			resultList = (List<Weibo>) dao.batchSave(weiboList);
		} catch (DaoException e) {
			log.error(" insert list wrong : " + weiboList);
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
			result = dao.delete(Weibo.class, id);
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
		public boolean update(Weibo weibo)throws ServiceException, ServiceDaoException{
		
	
		          
	log.info(" update data : " + (weibo == null ? "null" : weibo.getId()));

		boolean result = false;

		if (weibo == null) {
			return true;
		}

		weibo.setUpdateAt(System.currentTimeMillis());

		try {
			result = dao.update(weibo);
		} catch (DaoException e) {
			log.error(" update wrong : " + weibo);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
       if(log.isInfoEnabled()){
		log.info(" update data success : " + weibo);
       }
		return result;	
		}	
		  
    	   
		@Override
		public boolean updateList(List<Weibo> weiboList)throws ServiceException, ServiceDaoException{
		
	
		          log.info(" update lists : " + (weiboList == null ? "null" : weiboList.size()));

		boolean result = false;

		if (CollectionUtils.isEmpty(weiboList)) {
			return true;
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (Weibo weibo : weiboList) {
			weibo.setUpdateAt(currentTimeMillis);
		}

		try {
			result = dao.batchUpdate(weiboList);
		} catch (DaoException e) {
			log.error(" update list wrong : " + weiboList);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
         if(log.isInfoEnabled()){
		log.info(" update lists success : " + weiboList.size());
         }
		return result;	
		}	
		  
    	   
		@Override
		public Weibo getObjectById(Long id)throws ServiceException, ServiceDaoException{
		
	
		                 if(log.isInfoEnabled()){
        log.info(" get data : " + id);
       }
		Weibo weibo = null;

		if (id == null) {
			return weibo;
		}

		try {
			weibo = (Weibo) dao.get(Weibo.class, id);
		} catch (DaoException e) {
			log.error(" get wrong : " + id);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
       if(log.isInfoEnabled()){
		log.info(" get data success : " + id);
        }
		return weibo;		
		}	
		  
    	   
		@Override
		public List<Weibo> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException{
		
	
		          	  if(log.isInfoEnabled()){
	    log.info(" get lists : " + (ids == null ? "null" : ids));
      }
		List<Weibo> weibo = null;

		if (CollectionUtils.isEmpty(ids)) {
			return new ArrayList<Weibo>();
		}

		try {
			weibo = (List<Weibo>) dao.getList(Weibo.class, ids);
		} catch (DaoException e) {
			log.error(" get wrong : " + ids);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
     if(log.isInfoEnabled()){
		log.info(" get data success : " + (weibo == null ? "null" : weibo.size()));
     }
		return weibo;	
		}	
		  
    	
		
	
	
			
			
		/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	 @Override
	public List<Long>  getWeiboIdsByStatus(int status,Integer start,Integer limit)throws ServiceException, ServiceDaoException{
		
		       if(log.isInfoEnabled()){
      log.info(" get ids by status,start,limit  : " + status+" , "+start+" , "+limit );
	  }
	 	List<Long> idList = null;
        
        // TODO 参数检查!

        if (start == null) {
            start = 0;
        }

        if (limit == null) {
            limit = Integer.MAX_VALUE;
        }

	try {
		idList = dao.getIdList("getWeiboIdsByStatus", new Object[] { status},start,limit, false);

   
   } catch (DaoException e) {
			log.error(" get ids  wrong by status,start,limit)  : " + status+" , "+start+" , "+limit );
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
  if(log.isInfoEnabled()){
   log.info(" get ids success : " + (idList == null ? "null" : idList.size()));
  }
		return idList;
		
	
	
	}
	
		
	

}


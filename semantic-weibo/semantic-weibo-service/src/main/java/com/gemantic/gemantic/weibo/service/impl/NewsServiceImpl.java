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
import com.gemantic.gemantic.weibo.model.News;
import com.gemantic.gemantic.weibo.service.NewsService;



public class NewsServiceImpl implements NewsService {

    private Dao dao;

	private static final Log log = LogFactory.getLog(NewsServiceImpl.class);

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}


		   
		@Override
		public Long insert(News news)throws ServiceException, ServiceDaoException{
		
	
		           if(log.isInfoEnabled()){	
    log.info(" insert data : " + news);
 }
		if (news == null) {
			return null;
		}

		long currentTimeMillis = System.currentTimeMillis();
		news.setCreateAt(currentTimeMillis);
		news.setUpdateAt(currentTimeMillis);

		Long result = null;
		try {
			result = (Long) dao.save(news);
		} catch (DaoException e) {
			log.error(" insert wrong : " + news);
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
		public List<News> insertList(List<News> newsList)throws ServiceException, ServiceDaoException{
		
	
		          	 if(log.isInfoEnabled()){
        log.info(" insert lists : " + (newsList == null ? "null" : newsList.size()));
      }
		List<News> resultList = null;

		if (CollectionUtils.isEmpty(newsList)) {
			return new ArrayList<News>();
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (News news : newsList) {
			news.setCreateAt(currentTimeMillis);
			news.setUpdateAt(currentTimeMillis);
		}

		try {
			resultList = (List<News>) dao.batchSave(newsList);
		} catch (DaoException e) {
			log.error(" insert list wrong : " + newsList);
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
			result = dao.delete(News.class, id);
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
		public boolean update(News news)throws ServiceException, ServiceDaoException{
		
	
		          
	log.info(" update data : " + (news == null ? "null" : news.getId()));

		boolean result = false;

		if (news == null) {
			return true;
		}

		news.setUpdateAt(System.currentTimeMillis());

		try {
			result = dao.update(news);
		} catch (DaoException e) {
			log.error(" update wrong : " + news);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
       if(log.isInfoEnabled()){
		log.info(" update data success : " + news);
       }
		return result;	
		}	
		  
    	   
		@Override
		public boolean updateList(List<News> newsList)throws ServiceException, ServiceDaoException{
		
	
		          log.info(" update lists : " + (newsList == null ? "null" : newsList.size()));

		boolean result = false;

		if (CollectionUtils.isEmpty(newsList)) {
			return true;
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (News news : newsList) {
			news.setUpdateAt(currentTimeMillis);
		}

		try {
			result = dao.batchUpdate(newsList);
		} catch (DaoException e) {
			log.error(" update list wrong : " + newsList);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
         if(log.isInfoEnabled()){
		log.info(" update lists success : " + newsList.size());
         }
		return result;	
		}	
		  
    	   
		@Override
		public News getObjectById(Long id)throws ServiceException, ServiceDaoException{
		
	
		                 if(log.isInfoEnabled()){
        log.info(" get data : " + id);
       }
		News news = null;

		if (id == null) {
			return news;
		}

		try {
			news = (News) dao.get(News.class, id);
		} catch (DaoException e) {
			log.error(" get wrong : " + id);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
       if(log.isInfoEnabled()){
		log.info(" get data success : " + id);
        }
		return news;		
		}	
		  
    	   
		@Override
		public List<News> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException{
		
	
		          	  if(log.isInfoEnabled()){
	    log.info(" get lists : " + (ids == null ? "null" : ids));
      }
		List<News> news = null;

		if (CollectionUtils.isEmpty(ids)) {
			return new ArrayList<News>();
		}

		try {
			news = (List<News>) dao.getList(News.class, ids);
		} catch (DaoException e) {
			log.error(" get wrong : " + ids);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
     if(log.isInfoEnabled()){
		log.info(" get data success : " + (news == null ? "null" : news.size()));
     }
		return news;	
		}	
		  
    	
		
	
	
			
			
		/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	 @Override
	public List<Long>  getNewsIdsByStatus(int status,Integer start,Integer limit)throws ServiceException, ServiceDaoException{
		
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
		idList = dao.getIdList("getNewsIdsByStatus", new Object[] { status},start,limit, false);

   
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


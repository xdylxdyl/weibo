package com.gemantic.gemantic.weibo.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.osoa.sca.annotations.Remotable;

import com.gemantic.gemantic.weibo.model.CompanyNews;
import com.gemantic.gemantic.weibo.service.CompanyNewsService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.dal.dao.Dao;
import com.gemantic.dal.dao.exception.DaoException;

public class CompanyNewsServiceImpl implements CompanyNewsService {

	private Dao dao;

	private static final Log log = LogFactory
			.getLog(CompanyNewsServiceImpl.class);

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	@Override
	public Long insert(CompanyNews companyNews) throws ServiceException,
			ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" insert data : " + companyNews);
		}
		if (companyNews == null) {
			return null;
		}

		long currentTimeMillis = System.currentTimeMillis();
		companyNews.setCreateAt(currentTimeMillis);
		companyNews.setUpdateAt(currentTimeMillis);

		Long result = null;
		try {
			result = (Long) dao.save(companyNews);
		} catch (DaoException e) {
			log.error(" insert wrong : " + companyNews);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" insert data success : " + result);
		}
		return result;
	}

	@Override
	public List<CompanyNews> insertList(List<CompanyNews> companyNewsList)
			throws ServiceException, ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" insert lists : "
					+ (companyNewsList == null ? "null" : companyNewsList
							.size()));
		}
		List<CompanyNews> resultList = null;

		if (CollectionUtils.isEmpty(companyNewsList)) {
			return new ArrayList<CompanyNews>();
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (CompanyNews companyNews : companyNewsList) {
			companyNews.setCreateAt(currentTimeMillis);
			companyNews.setUpdateAt(currentTimeMillis);
		}

		try {
			resultList = (List<CompanyNews>) dao.batchSave(companyNewsList);
		} catch (DaoException e) {
			log.error(" insert list wrong : " + companyNewsList);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" insert lists  success : "
					+ (resultList == null ? "null" : resultList.size()));
		}
		return resultList;

	}

	@Override
	public boolean delete(Long id) throws ServiceException, ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" delete data : " + id);
		}
		boolean result = false;

		if (id == null) {
			return true;
		}

		try {
			result = dao.delete(CompanyNews.class, id);
		} catch (DaoException e) {
			log.error(" delete wrong : " + id);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" delete data success : " + id);
		}
		return result;

	}

	@Override
	public boolean update(CompanyNews companyNews) throws ServiceException,
			ServiceDaoException {

		log.info(" update data : "
				+ (companyNews == null ? "null" : companyNews.getId()));

		boolean result = false;

		if (companyNews == null) {
			return true;
		}

		companyNews.setUpdateAt(System.currentTimeMillis());

		try {
			result = dao.update(companyNews);
		} catch (DaoException e) {
			log.error(" update wrong : " + companyNews);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" update data success : " + companyNews);
		}
		return result;
	}

	@Override
	public boolean updateList(List<CompanyNews> companyNewsList)
			throws ServiceException, ServiceDaoException {

		log.info(" update lists : "
				+ (companyNewsList == null ? "null" : companyNewsList.size()));

		boolean result = false;

		if (CollectionUtils.isEmpty(companyNewsList)) {
			return true;
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (CompanyNews companyNews : companyNewsList) {
			companyNews.setUpdateAt(currentTimeMillis);
		}

		try {
			result = dao.batchUpdate(companyNewsList);
		} catch (DaoException e) {
			log.error(" update list wrong : " + companyNewsList);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" update lists success : " + companyNewsList.size());
		}
		return result;
	}

	@Override
	public CompanyNews getObjectById(Long id) throws ServiceException,
			ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" get data : " + id);
		}
		CompanyNews companyNews = null;

		if (id == null) {
			return companyNews;
		}

		try {
			companyNews = (CompanyNews) dao.get(CompanyNews.class, id);
		} catch (DaoException e) {
			log.error(" get wrong : " + id);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" get data success : " + id);
		}
		return companyNews;
	}

	@Override
	public List<CompanyNews> getObjectsByIds(List<Long> ids)
			throws ServiceException, ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" get lists : " + (ids == null ? "null" : ids));
		}
		List<CompanyNews> companyNews = null;

		if (CollectionUtils.isEmpty(ids)) {
			return new ArrayList<CompanyNews>();
		}

		try {
			companyNews = (List<CompanyNews>) dao.getList(CompanyNews.class,
					ids);
		} catch (DaoException e) {
			log.error(" get wrong : " + ids);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" get data success : "
					+ (companyNews == null ? "null" : companyNews.size()));
		}
		return companyNews;
	}

}

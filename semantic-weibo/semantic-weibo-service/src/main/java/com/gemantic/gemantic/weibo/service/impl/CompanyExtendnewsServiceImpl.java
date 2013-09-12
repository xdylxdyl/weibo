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
import com.gemantic.gemantic.weibo.model.CompanyExtendnews;
import com.gemantic.gemantic.weibo.service.CompanyExtendnewsService;

public class CompanyExtendnewsServiceImpl implements CompanyExtendnewsService {

	private Dao dao;

	private static final Log log = LogFactory
			.getLog(CompanyExtendnewsServiceImpl.class);

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	@Override
	public Long insert(CompanyExtendnews companyExtendnews)
			throws ServiceException, ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" insert data : " + companyExtendnews);
		}
		if (companyExtendnews == null) {
			return null;
		}

		long currentTimeMillis = System.currentTimeMillis();
		companyExtendnews.setCreateAt(currentTimeMillis);
		companyExtendnews.setUpdateAt(currentTimeMillis);

		Long result = null;
		try {
			result = (Long) dao.save(companyExtendnews);
		} catch (DaoException e) {
			log.error(" insert wrong : " + companyExtendnews);
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
	public List<CompanyExtendnews> insertList(
			List<CompanyExtendnews> companyExtendnewsList)
			throws ServiceException, ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" insert lists : "
					+ (companyExtendnewsList == null ? "null"
							: companyExtendnewsList.size()));
		}
		List<CompanyExtendnews> resultList = null;

		if (CollectionUtils.isEmpty(companyExtendnewsList)) {
			return new ArrayList<CompanyExtendnews>();
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (CompanyExtendnews companyExtendnews : companyExtendnewsList) {
			companyExtendnews.setCreateAt(currentTimeMillis);
			companyExtendnews.setUpdateAt(currentTimeMillis);
		}

		try {
			resultList = (List<CompanyExtendnews>) dao
					.batchSave(companyExtendnewsList);
		} catch (DaoException e) {
			log.error(" insert list wrong : " + companyExtendnewsList);
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
			result = dao.delete(CompanyExtendnews.class, id);
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
	public boolean update(CompanyExtendnews companyExtendnews)
			throws ServiceException, ServiceDaoException {

		log.info(" update data : "
				+ (companyExtendnews == null ? "null" : companyExtendnews
						.getId()));

		boolean result = false;

		if (companyExtendnews == null) {
			return true;
		}

		companyExtendnews.setUpdateAt(System.currentTimeMillis());

		try {
			result = dao.update(companyExtendnews);
		} catch (DaoException e) {
			log.error(" update wrong : " + companyExtendnews);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" update data success : " + companyExtendnews);
		}
		return result;
	}

	@Override
	public boolean updateList(List<CompanyExtendnews> companyExtendnewsList)
			throws ServiceException, ServiceDaoException {

		log.info(" update lists : "
				+ (companyExtendnewsList == null ? "null"
						: companyExtendnewsList.size()));

		boolean result = false;

		if (CollectionUtils.isEmpty(companyExtendnewsList)) {
			return true;
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (CompanyExtendnews companyExtendnews : companyExtendnewsList) {
			companyExtendnews.setUpdateAt(currentTimeMillis);
		}

		try {
			result = dao.batchUpdate(companyExtendnewsList);
		} catch (DaoException e) {
			log.error(" update list wrong : " + companyExtendnewsList);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" update lists success : " + companyExtendnewsList.size());
		}
		return result;
	}

	@Override
	public CompanyExtendnews getObjectById(Long id) throws ServiceException,
			ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" get data : " + id);
		}
		CompanyExtendnews companyExtendnews = null;

		if (id == null) {
			return companyExtendnews;
		}

		try {
			companyExtendnews = (CompanyExtendnews) dao.get(
					CompanyExtendnews.class, id);
		} catch (DaoException e) {
			log.error(" get wrong : " + id);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" get data success : " + id);
		}
		return companyExtendnews;
	}

	@Override
	public List<CompanyExtendnews> getObjectsByIds(List<Long> ids)
			throws ServiceException, ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" get lists : " + (ids == null ? "null" : ids));
		}
		List<CompanyExtendnews> companyExtendnews = null;

		if (CollectionUtils.isEmpty(ids)) {
			return new ArrayList<CompanyExtendnews>();
		}

		try {
			companyExtendnews = (List<CompanyExtendnews>) dao.getList(
					CompanyExtendnews.class, ids);
		} catch (DaoException e) {
			log.error(" get wrong : " + ids);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" get data success : "
					+ (companyExtendnews == null ? "null" : companyExtendnews
							.size()));
		}
		return companyExtendnews;
	}

}

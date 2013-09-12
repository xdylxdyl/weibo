package com.gemantic.gemantic.weibo.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.osoa.sca.annotations.Remotable;

import com.gemantic.gemantic.weibo.model.CompanyEvent;
import com.gemantic.gemantic.weibo.service.CompanyEventService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.dal.dao.Dao;
import com.gemantic.dal.dao.exception.DaoException;

public class CompanyEventServiceImpl implements CompanyEventService {

	private Dao dao;

	private static final Log log = LogFactory
			.getLog(CompanyEventServiceImpl.class);

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	@Override
	public Long insert(CompanyEvent companyEvent) throws ServiceException,
			ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" insert data : " + companyEvent);
		}
		if (companyEvent == null) {
			return null;
		}

		long currentTimeMillis = System.currentTimeMillis();
		companyEvent.setCreateAt(currentTimeMillis);
		companyEvent.setUpdateAt(currentTimeMillis);

		Long result = null;
		try {
			result = (Long) dao.save(companyEvent);
		} catch (DaoException e) {
			log.error(" insert wrong : " + companyEvent);
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
	public List<CompanyEvent> insertList(List<CompanyEvent> companyEventList)
			throws ServiceException, ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" insert lists : "
					+ (companyEventList == null ? "null" : companyEventList
							.size()));
		}
		List<CompanyEvent> resultList = null;

		if (CollectionUtils.isEmpty(companyEventList)) {
			return new ArrayList<CompanyEvent>();
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (CompanyEvent companyEvent : companyEventList) {
			companyEvent.setCreateAt(currentTimeMillis);
			companyEvent.setUpdateAt(currentTimeMillis);
		}

		try {
			resultList = (List<CompanyEvent>) dao.batchSave(companyEventList);
		} catch (DaoException e) {
			log.error(" insert list wrong : " + companyEventList);
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
			result = dao.delete(CompanyEvent.class, id);
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
	public boolean update(CompanyEvent companyEvent) throws ServiceException,
			ServiceDaoException {

		log.info(" update data : "
				+ (companyEvent == null ? "null" : companyEvent.getId()));

		boolean result = false;

		if (companyEvent == null) {
			return true;
		}

		companyEvent.setUpdateAt(System.currentTimeMillis());

		try {
			result = dao.update(companyEvent);
		} catch (DaoException e) {
			log.error(" update wrong : " + companyEvent);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" update data success : " + companyEvent);
		}
		return result;
	}

	@Override
	public boolean updateList(List<CompanyEvent> companyEventList)
			throws ServiceException, ServiceDaoException {

		log.info(" update lists : "
				+ (companyEventList == null ? "null" : companyEventList.size()));

		boolean result = false;

		if (CollectionUtils.isEmpty(companyEventList)) {
			return true;
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (CompanyEvent companyEvent : companyEventList) {
			companyEvent.setUpdateAt(currentTimeMillis);
		}

		try {
			result = dao.batchUpdate(companyEventList);
		} catch (DaoException e) {
			log.error(" update list wrong : " + companyEventList);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" update lists success : " + companyEventList.size());
		}
		return result;
	}

	@Override
	public CompanyEvent getObjectById(Long id) throws ServiceException,
			ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" get data : " + id);
		}
		CompanyEvent companyEvent = null;

		if (id == null) {
			return companyEvent;
		}

		try {
			companyEvent = (CompanyEvent) dao.get(CompanyEvent.class, id);
		} catch (DaoException e) {
			log.error(" get wrong : " + id);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" get data success : " + id);
		}
		return companyEvent;
	}

	@Override
	public List<CompanyEvent> getObjectsByIds(List<Long> ids)
			throws ServiceException, ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" get lists : " + (ids == null ? "null" : ids));
		}
		List<CompanyEvent> companyEvent = null;

		if (CollectionUtils.isEmpty(ids)) {
			return new ArrayList<CompanyEvent>();
		}

		try {
			companyEvent = (List<CompanyEvent>) dao.getList(CompanyEvent.class,
					ids);
		} catch (DaoException e) {
			log.error(" get wrong : " + ids);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" get data success : "
					+ (companyEvent == null ? "null" : companyEvent.size()));
		}
		return companyEvent;
	}

	/**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	@Override
	public List<Long> getEidsByCompanyUri(String companyUri, Integer start,
			Integer limit) throws ServiceException, ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" get eids by companyUri,start,limit  : " + companyUri
					+ " , " + start + " , " + limit);
		}
		List<Long> eidList = null;

		// TODO 参数检查!

		if (start == null) {
			start = 0;
		}

		if (limit == null) {
			limit = Integer.MAX_VALUE;
		}

		try {
			eidList = dao.getIdList("getEidsByCompanyUri",
					new Object[] { companyUri }, start, limit, false);

		} catch (DaoException e) {
			log.error(" get eids  wrong by companyUri,start,limit)  : "
					+ companyUri + " , " + start + " , " + limit);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" get eids success : "
					+ (eidList == null ? "null" : eidList.size()));
		}
		return eidList;

	}

}

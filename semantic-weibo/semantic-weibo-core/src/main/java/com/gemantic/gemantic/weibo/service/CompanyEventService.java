package com.gemantic.gemantic.weibo.service;

import java.util.List;
import java.util.Map;

import org.osoa.sca.annotations.Remotable;

import com.gemantic.gemantic.weibo.model.CompanyEvent;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;

@Remotable
public interface CompanyEventService {

	public Long insert(CompanyEvent companyEvent) throws ServiceException,
			ServiceDaoException;

	public List<CompanyEvent> insertList(List<CompanyEvent> companyEventList)
			throws ServiceException, ServiceDaoException;

	public boolean delete(Long id) throws ServiceException, ServiceDaoException;

	public boolean update(CompanyEvent companyEvent) throws ServiceException,
			ServiceDaoException;

	public boolean updateList(List<CompanyEvent> companyEventList)
			throws ServiceException, ServiceDaoException;

	public CompanyEvent getObjectById(Long id) throws ServiceException,
			ServiceDaoException;

	public List<CompanyEvent> getObjectsByIds(List<Long> ids)
			throws ServiceException, ServiceDaoException;

	/**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public List<Long> getEidsByCompanyUri(String companyUri, Integer start,
			Integer limit) throws ServiceException, ServiceDaoException;

}

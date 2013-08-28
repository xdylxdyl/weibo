/**
 * 
 */
package com.gemantic.sca.gemantic.weibo.client;

import java.util.List;

import com.gemantic.gemantic.weibo.model.CompanyEvent;
import com.gemantic.gemantic.weibo.service.CompanyEventService;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;

public class CompanyEventSCAClient implements CompanyEventService {

	private CompanyEventService companyEventService;

	public CompanyEventService getCompanyEventService() {
		return companyEventService;
	}

	public void setCompanyEventService(CompanyEventService companyEventService) {
		this.companyEventService = companyEventService;
	}

	@Override
	public Long insert(CompanyEvent companyEvent) throws ServiceException,
			ServiceDaoException {

		return companyEventService.insert(companyEvent);

	}

	@Override
	public List<CompanyEvent> insertList(List<CompanyEvent> companyEventList)
			throws ServiceException, ServiceDaoException {

		return companyEventService.insertList(companyEventList);

	}

	@Override
	public boolean delete(Long id) throws ServiceException, ServiceDaoException {

		return companyEventService.delete(id);

	}

	@Override
	public boolean update(CompanyEvent companyEvent) throws ServiceException,
			ServiceDaoException {

		return companyEventService.update(companyEvent);

	}

	@Override
	public boolean updateList(List<CompanyEvent> companyEventList)
			throws ServiceException, ServiceDaoException {

		return companyEventService.updateList(companyEventList);

	}

	@Override
	public CompanyEvent getObjectById(Long id) throws ServiceException,
			ServiceDaoException {

		return companyEventService.getObjectById(id);

	}

	@Override
	public List<CompanyEvent> getObjectsByIds(List<Long> ids)
			throws ServiceException, ServiceDaoException {

		return companyEventService.getObjectsByIds(ids);

	}

}

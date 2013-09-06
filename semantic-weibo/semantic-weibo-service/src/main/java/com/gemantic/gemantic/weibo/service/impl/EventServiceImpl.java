package com.gemantic.gemantic.weibo.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.osoa.sca.annotations.Remotable;

import com.gemantic.gemantic.weibo.model.Event;
import com.gemantic.gemantic.weibo.service.EventService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.dal.dao.Dao;
import com.gemantic.dal.dao.exception.DaoException;

public class EventServiceImpl implements EventService {

	private Dao dao;

	private static final Log log = LogFactory.getLog(EventServiceImpl.class);

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	@Override
	public Long insert(Event event) throws ServiceException,
			ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" insert event data : " + event);
		}
		if (event == null) {
			return null;
		}

		long currentTimeMillis = System.currentTimeMillis();
		event.setCreateAt(currentTimeMillis);
		event.setUpdateAt(currentTimeMillis);

		Long result = null;
		try {
			result = (Long) dao.save(event);
		} catch (DaoException e) {
			log.error(" insert wrong : " + event);
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
	public List<Event> insertList(List<Event> eventList)
			throws ServiceException, ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" insert lists : "
					+ (eventList == null ? "null" : eventList.size()));
		}
		List<Event> resultList = null;

		if (CollectionUtils.isEmpty(eventList)) {
			return new ArrayList<Event>();
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (Event event : eventList) {
			event.setCreateAt(currentTimeMillis);
			event.setUpdateAt(currentTimeMillis);
		}

		try {
			resultList = (List<Event>) dao.batchSave(eventList);
		} catch (DaoException e) {
			log.error(" insert list wrong : " + eventList);
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
			result = dao.delete(Event.class, id);
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
	public boolean update(Event event) throws ServiceException,
			ServiceDaoException {

		log.info(" update data : " + (event == null ? "null" : event.getId()));

		boolean result = false;

		if (event == null) {
			return true;
		}

		event.setUpdateAt(System.currentTimeMillis());

		try {
			result = dao.update(event);
		} catch (DaoException e) {
			log.error(" update wrong : " + event);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" update data success : " + event);
		}
		return result;
	}

	@Override
	public boolean updateList(List<Event> eventList) throws ServiceException,
			ServiceDaoException {

		log.info(" update lists : "
				+ (eventList == null ? "null" : eventList.size()));

		boolean result = false;

		if (CollectionUtils.isEmpty(eventList)) {
			return true;
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (Event event : eventList) {
			event.setUpdateAt(currentTimeMillis);
		}

		try {
			result = dao.batchUpdate(eventList);
		} catch (DaoException e) {
			log.error(" update list wrong : " + eventList);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" update lists success : " + eventList.size());
		}
		return result;
	}

	@Override
	public Event getObjectById(Long id) throws ServiceException,
			ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" get data : " + id);
		}
		Event event = null;

		if (id == null) {
			return event;
		}

		try {
			event = (Event) dao.get(Event.class, id);
		} catch (DaoException e) {
			log.error(" get wrong : " + id);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" get data success : " + id);
		}
		return event;
	}

	@Override
	public List<Event> getObjectsByIds(List<Long> ids) throws ServiceException,
			ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" get lists : " + (ids == null ? "null" : ids));
		}
		List<Event> event = null;

		if (CollectionUtils.isEmpty(ids)) {
			return new ArrayList<Event>();
		}

		try {
			event = (List<Event>) dao.getList(Event.class, ids);
		} catch (DaoException e) {
			log.error(" get wrong : " + ids);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" get data success : "
					+ (event == null ? "null" : event.size()));
		}
		return event;
	}

	@Override
	public List<Long> getAllEvent(Integer start, Integer limit) throws ServiceException,
			ServiceDaoException {


		if (log.isInfoEnabled()) {
			log.info(" get all ids ,start,limit  :  " + start + " , " + limit);
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
			// idList = (List<Long>)
			// dao.excuteSimpleSql("select id from records where version = "+version,
			// Records.class);
			idList = dao.getIdList("getEventAll", new Object[] {}, start, limit,
					false);

		} catch (DaoException e) {
			log.error(" get ids  wrong by version,start,limit)  , " + start
					+ " , " + limit);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" get ids success : "
					+ (idList == null ? "null" : idList.size()));
		}
		return idList;

	
	}

}

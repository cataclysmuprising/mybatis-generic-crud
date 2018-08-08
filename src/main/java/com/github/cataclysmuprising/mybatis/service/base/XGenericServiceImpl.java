/*
 *
 *   This source file is free software, available under the following license: MIT license.
 *   Copyright (c) 2018, Than Htike Aung(https://github.com/cataclysmuprising) All Rights Reserved.
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 *
 *  	mybatis-generic-crud - XGenericServiceImpl.java
 *  	Using Java(TM) SE Runtime Environment (build 1.8.0_151-b12)
 * 	    Last Modified - 8/8/18 2:28 PM
 *  	@author Than Htike Aung {@literal <rage.cataclysm@gmail.com>}
 *  	@Since 2018
 */
package com.github.cataclysmuprising.mybatis.service.base;

import com.github.cataclysmuprising.mybatis.dao.api.XGenericDao;
import com.github.cataclysmuprising.mybatis.exception.BusinessException;
import com.github.cataclysmuprising.mybatis.exception.ConsistencyViolationException;
import com.github.cataclysmuprising.mybatis.exception.DAOException;
import com.github.cataclysmuprising.mybatis.exception.DuplicatedEntryException;
import com.github.cataclysmuprising.mybatis.service.base.api.XGenericService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class XGenericServiceImpl<T, C> extends BaseServiceImpl implements XGenericService<T, C> {
	private static final Logger serviceLogger = LogManager.getLogger("serviceLogs." + XGenericServiceImpl.class.getName());
	private XGenericDao<T, C> dao;

	public XGenericServiceImpl(XGenericDao<T, C> dao) {
		this.dao = dao;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insert(T record, long recordRegId) throws DuplicatedEntryException, BusinessException {
		serviceLogger.info(LOG_PREFIX + "This transaction was initiated by User ID # " + recordRegId + LOG_SUFFIX);
		serviceLogger.info(LOG_PREFIX + "Transaction start for inserting" + getObjectName(record) + "informations." + LOG_SUFFIX);
		try {
			dao.insert(record, recordRegId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info(LOG_PREFIX + "Transaction finished successfully." + LOG_SUFFIX);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insert(List<T> records, long recordRegId) throws DuplicatedEntryException, BusinessException {
		serviceLogger.info(LOG_PREFIX + "This transaction was initiated by User ID # " + recordRegId + LOG_SUFFIX);
		serviceLogger.info(LOG_PREFIX + "Transaction start for inserting" + getObjectName(records) + "informations." + LOG_SUFFIX);
		try {
			dao.insert(records, recordRegId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info(LOG_PREFIX + "Transaction finished successfully." + LOG_SUFFIX);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insert(long key1, long key2, long recordRegId) throws DuplicatedEntryException, BusinessException {
		serviceLogger.info(LOG_PREFIX + "This transaction was initiated by User ID # " + recordRegId + LOG_SUFFIX);
		serviceLogger.info(LOG_PREFIX + "Transaction start for inserting with keys {key1=" + key1 + ",key2=" + key2 + "}" + LOG_SUFFIX);
		try {
			dao.insert(key1, key2, recordRegId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info(LOG_PREFIX + "Transaction finished successfully." + LOG_SUFFIX);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void merge(long mainKey, List<Long> relatedKeys, long recordUpdId) throws DuplicatedEntryException, ConsistencyViolationException, BusinessException {
		serviceLogger.info(LOG_PREFIX + "This transaction was initiated by User ID # " + recordUpdId + LOG_SUFFIX);
		serviceLogger.info(LOG_PREFIX + "Transaction start for merging (Main Key = " + mainKey + ") with related keys ==> " + relatedKeys + LOG_SUFFIX);
		try {
			dao.merge(mainKey, relatedKeys, recordUpdId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info(LOG_PREFIX + "Transaction finished successfully." + LOG_SUFFIX);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void merge(List<Long> relatedKeys, long joinKey, long recordUpdId) throws DuplicatedEntryException, ConsistencyViolationException, BusinessException {
		serviceLogger.info(LOG_PREFIX + "This transaction was initiated by User ID # " + recordUpdId + LOG_SUFFIX);
		serviceLogger.info(LOG_PREFIX + "Transaction start for merging (Join Key = " + joinKey + ") with related keys ==> " + relatedKeys + LOG_SUFFIX);
		try {
			dao.merge(relatedKeys, joinKey, recordUpdId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info(LOG_PREFIX + "Transaction finished successfully." + LOG_SUFFIX);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public long delete(long key1, long key2, long recordUpdId) throws ConsistencyViolationException, BusinessException {
		serviceLogger.info(LOG_PREFIX + "This transaction was initiated by User ID # " + recordUpdId + LOG_SUFFIX);
		serviceLogger.info(LOG_PREFIX + "Transaction start for delete by Keys ==> {key1=" + key1 + ",key2=" + key2 + "}" + LOG_SUFFIX);
		long totalEffectedRows;
		try {
			totalEffectedRows = dao.delete(key1, key2, recordUpdId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info(LOG_PREFIX + "Transaction finished successfully.");
		return totalEffectedRows;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public long delete(C criteria, long recordUpdId) throws ConsistencyViolationException, BusinessException {
		serviceLogger.info(LOG_PREFIX + "This transaction was initiated by User ID # " + recordUpdId + LOG_SUFFIX);
		serviceLogger.info(LOG_PREFIX + "Transaction start for delete by criteria ==> " + criteria + LOG_SUFFIX);
		long totalEffectedRows;
		try {
			totalEffectedRows = dao.delete(criteria, recordUpdId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info(LOG_PREFIX + "Transaction finished successfully." + LOG_SUFFIX);
		return totalEffectedRows;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Long> selectByKey1(long key1) throws BusinessException {
		serviceLogger.info(LOG_PREFIX + "Transaction start for fetching related keys by key1# " + key1 + LOG_SUFFIX);
		List<Long> relatedKeys;
		try {
			relatedKeys = dao.selectByKey1(key1);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info(LOG_PREFIX + "Transaction finished successfully." + LOG_SUFFIX);
		return relatedKeys;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Long> selectByKey2(long key2) throws BusinessException {
		serviceLogger.info(LOG_PREFIX + "Transaction start for fetching related keys by key2# " + key2 + LOG_SUFFIX);
		List<Long> relatedKeys;
		try {
			relatedKeys = dao.selectByKey2(key2);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info(LOG_PREFIX + "Transaction finished successfully." + LOG_SUFFIX);
		return relatedKeys;
	}

	@Override
	@Transactional(readOnly = true)
	public T select(long key1, long key2) throws BusinessException {
		serviceLogger.info(LOG_PREFIX + "Transaction start for fetching single record by Keys ==> {key1=" + key1 + ",key2=" + key2 + "}" + LOG_SUFFIX);
		T record;
		try {
			record = dao.select(key1, key2);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info(LOG_PREFIX + "Transaction finished successfully." + LOG_SUFFIX);
		return record;
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> selectList(C criteria) throws BusinessException {
		serviceLogger.info(LOG_PREFIX + "Transaction start for fetching multi records by criteria ==> " + criteria + LOG_SUFFIX);
		List<T> records;
		try {
			records = dao.selectList(criteria);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info(LOG_PREFIX + "Transaction finished successfully." + LOG_SUFFIX);
		return records;
	}

	@Override
	@Transactional(readOnly = true)
	public long selectCounts(C criteria) throws BusinessException {
		serviceLogger.info(LOG_PREFIX + "Transaction start for fetching record counts by criteria ==> " + criteria + LOG_SUFFIX);
		long count;
		try {
			count = dao.selectCounts(criteria);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info(LOG_PREFIX + "Transaction finished successfully." + LOG_SUFFIX);
		return count;
	}
}

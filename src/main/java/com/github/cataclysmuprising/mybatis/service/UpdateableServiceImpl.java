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
 *  	mybatis-generic-crud - UpdateableServiceImpl.java
 *  	Using Java(TM) SE Runtime Environment (build 1.8.0_151-b12)
 * 	    Last Modified - 8/8/18 1:58 PM
 *  	@author Than Htike Aung {@literal <rage.cataclysm@gmail.com>}
 *  	@Since 2018
 */
package com.github.cataclysmuprising.mybatis.service;

import com.github.cataclysmuprising.mybatis.exception.BusinessException;
import com.github.cataclysmuprising.mybatis.exception.DAOException;
import com.github.cataclysmuprising.mybatis.exception.DuplicatedEntryException;
import com.github.cataclysmuprising.mybatis.repository.api.UpdateableDao;
import com.github.cataclysmuprising.mybatis.service.api.root.UpdateableService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

public class UpdateableServiceImpl<T, C> extends BaseServiceImpl implements UpdateableService<T, C> {
	private static final Logger serviceLogger = LogManager.getLogger("serviceLogs." + UpdateableServiceImpl.class.getName());
	private UpdateableDao<T, C> dao;

	public UpdateableServiceImpl(UpdateableDao<T, C> dao) {
		this.dao = dao;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public long update(T record, long recordUpdId) throws DuplicatedEntryException, BusinessException {
		serviceLogger.info(LOG_PREFIX + "This transaction was initiated by User ID # " + recordUpdId + LOG_SUFFIX);
		serviceLogger.info(LOG_PREFIX + "Transaction start for updating" + getObjectName(record) + "informations." + LOG_SUFFIX);
		long totalEffectedRows;
		try {
			totalEffectedRows = dao.update(record, recordUpdId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info(LOG_PREFIX + "Transaction finished successfully for updating" + getObjectName(record) + "informations." + LOG_SUFFIX);
		return totalEffectedRows;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(List<T> records, long recordUpdId) throws DuplicatedEntryException, BusinessException {
		serviceLogger.info(LOG_PREFIX + "This transaction was initiated by User ID # " + recordUpdId + LOG_SUFFIX);
		serviceLogger.info(LOG_PREFIX + "Transaction start for updating multi" + getObjectName(records) + "informations." + LOG_SUFFIX);
		try {
			dao.update(records, recordUpdId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info(LOG_PREFIX + "Transaction finished successfully for updating multi" + getObjectName(records) + "informations." + LOG_SUFFIX);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public long update(C criteria, HashMap<String, Object> updateItems, long recordUpdId) throws BusinessException, DuplicatedEntryException {
		serviceLogger.info(LOG_PREFIX + "This transaction was initiated by User ID # " + recordUpdId + LOG_SUFFIX);
		serviceLogger.info(LOG_PREFIX + "Transaction start for updating" + updateItems + " with criteria ==> " + criteria + LOG_SUFFIX);
		long totalEffectedRows;
		try {
			totalEffectedRows = dao.update(criteria, updateItems, recordUpdId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info(LOG_PREFIX + "Transaction finished successfully." + LOG_SUFFIX);
		return totalEffectedRows;
	}
}

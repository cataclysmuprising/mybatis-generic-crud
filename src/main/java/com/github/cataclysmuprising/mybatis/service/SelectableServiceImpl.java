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
 *  	mybatis-generic-crud - SelectableServiceImpl.java
 *  	Using Java(TM) SE Runtime Environment (build 1.8.0_151-b12)
 * 	    Last Modified - 8/8/18 1:58 PM
 *  	@author Than Htike Aung {@literal <rage.cataclysm@gmail.com>}
 *  	@Since 2018
 */
package com.github.cataclysmuprising.mybatis.service;

import com.github.cataclysmuprising.mybatis.exception.BusinessException;
import com.github.cataclysmuprising.mybatis.exception.DAOException;
import com.github.cataclysmuprising.mybatis.repository.api.SelectableRepository;
import com.github.cataclysmuprising.mybatis.service.api.root.SelectableService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class SelectableServiceImpl<T, C> extends BaseServiceImpl implements SelectableService<T, C> {
	private static final Logger serviceLogger = LogManager.getLogger("serviceLogs." + SelectableServiceImpl.class.getName());
	private SelectableRepository<T, C> dao;

	public SelectableServiceImpl(SelectableRepository<T, C> dao) {
		this.dao = dao;
	}

	@Override
	@Transactional(readOnly = true)
	public T select(long primaryKey) throws BusinessException {
		serviceLogger.info(LOG_PREFIX + "Transaction start for fetch by primary key # " + primaryKey + " ==> " + LOG_SUFFIX);
		T record;
		try {
			record = dao.select(primaryKey);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info(LOG_PREFIX + "Transaction finished successfully." + LOG_SUFFIX);
		return record;
	}

	@Override
	@Transactional(readOnly = true)
	public T select(C criteria) throws BusinessException {
		serviceLogger.info(LOG_PREFIX + "Transaction start for fetching single record by criteria ==> " + criteria + LOG_SUFFIX);
		T record;
		try {
			record = dao.select(criteria);
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

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
 *  	mybatis-generic-crud - CommonGenericServiceImpl.java
 *  	Using Java(TM) SE Runtime Environment (build 1.8.0_151-b12)
 * 	    Last Modified - 8/8/18 1:58 PM
 *  	@author Than Htike Aung {@literal <rage.cataclysm@gmail.com>}
 *  	@Since 2018
 */
package com.github.cataclysmuprising.mybatis.service;

import com.github.cataclysmuprising.mybatis.exception.BusinessException;
import com.github.cataclysmuprising.mybatis.exception.ConsistencyViolationException;
import com.github.cataclysmuprising.mybatis.exception.DuplicatedEntryException;
import com.github.cataclysmuprising.mybatis.repository.api.CommonGenericRepository;
import com.github.cataclysmuprising.mybatis.service.api.CommonGenericService;
import com.github.cataclysmuprising.mybatis.service.api.root.UpdateableService;

import java.util.HashMap;
import java.util.List;

public class CommonGenericServiceImpl<T, C> extends BaseServiceImpl implements CommonGenericService<T, C> {

	private SelectableServiceImpl<T, C> selectableService;
	private InsertableServiceImpl<T> insertableService;
	private UpdateableService<T, C> updateableService;
	private RemoveableServiceImpl<T, C> removeableService;

	public CommonGenericServiceImpl(CommonGenericRepository<T, C> dao) {
		selectableService = new SelectableServiceImpl<>(dao);
		insertableService = new InsertableServiceImpl<>(dao);
		updateableService = new UpdateableServiceImpl<>(dao);
		removeableService = new RemoveableServiceImpl<>(dao);
	}

	@Override
	public T select(long primaryKey) throws BusinessException {
		return selectableService.select(primaryKey);
	}

	@Override
	public T select(C criteria) throws BusinessException {
		return selectableService.select(criteria);
	}

	@Override
	public List<T> selectList(C criteria) throws BusinessException {
		return selectableService.selectList(criteria);
	}

	@Override
	public long selectCounts(C criteria) throws BusinessException {
		return selectableService.selectCounts(criteria);
	}

	@Override
	public long insert(T record, long recordRegId) throws DuplicatedEntryException, BusinessException {
		return insertableService.insert(record, recordRegId);
	}

	@Override
	public void insert(List<T> records, long recordRegId) throws DuplicatedEntryException, BusinessException {
		insertableService.insert(records, recordRegId);
	}

	@Override
	public long update(T record, long recordUpdId) throws DuplicatedEntryException, BusinessException {
		return updateableService.update(record, recordUpdId);
	}

	@Override
	public void update(List<T> records, long recordUpdId) throws DuplicatedEntryException, BusinessException {
		updateableService.update(records, recordUpdId);
	}

	@Override
	public long update(C criteria, HashMap<String, Object> updateItems, long recordUpdId) throws BusinessException, DuplicatedEntryException {
		return updateableService.update(criteria, updateItems, recordUpdId);
	}

	@Override
	public long delete(long id, long recordUpdId) throws ConsistencyViolationException, BusinessException {
		return removeableService.delete(id, recordUpdId);
	}

	@Override
	public long delete(C criteria, long recordUpdId) throws ConsistencyViolationException, BusinessException {
		return removeableService.delete(criteria, recordUpdId);
	}
}

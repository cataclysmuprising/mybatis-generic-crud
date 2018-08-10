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
 *  	mybatis-generic-crud - XGenericDao.java
 *  	Using Java(TM) SE Runtime Environment (build 1.8.0_151-b12)
 * 	    Last Modified - 8/8/18 1:53 PM
 *  	@author Than Htike Aung {@literal <rage.cataclysm@gmail.com>}
 *  	@Since 2018
 */
package com.github.cataclysmuprising.mybatis.repository.api;

import com.github.cataclysmuprising.mybatis.exception.ConsistencyViolationException;
import com.github.cataclysmuprising.mybatis.exception.DAOException;
import com.github.cataclysmuprising.mybatis.exception.DuplicatedEntryException;

import java.util.List;

public interface XGenericDao<T, C> {

	void insert(T record, long recordRegId) throws DuplicatedEntryException, DAOException;

	void insert(List<T> records, long recordRegId) throws DuplicatedEntryException, DAOException;

	void insert(long key1, long key2, long recordRegId) throws DuplicatedEntryException, DAOException;

	long delete(long key1, long key2, long recordUpdId) throws ConsistencyViolationException, DAOException;

	long delete(C criteria, long recordUpdId) throws ConsistencyViolationException, DAOException;

	void merge(long mainKey, List<Long> relatedKeys, long recordUpdId) throws DuplicatedEntryException, ConsistencyViolationException, DAOException;

	void merge(List<Long> relatedKeys, long joinKey, long recordUpdId) throws DuplicatedEntryException, ConsistencyViolationException, DAOException;

	List<Long> selectByKey1(long key1) throws DAOException;

	List<Long> selectByKey2(long key2) throws DAOException;

	T select(long key1, long key2) throws DAOException;

	List<T> selectList(C criteria) throws DAOException;

	long selectCounts(C criteria) throws DAOException;
}

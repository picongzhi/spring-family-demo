package com.pcz.spring.family.declarative.transaction.service;

import com.pcz.spring.family.declarative.transaction.exception.RollbackException;

/**
 * @author picongzhi
 */
public interface FooService {
    void insert();

    void insertThenRollback() throws RollbackException;

    void invokeInsertThenRollback() throws RollbackException;
}

/*
 * Copyright 2002-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sonic.spring;

import java.util.Properties;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.sonic.common.ShardUtils;

/**
 * 
 * @author shiweilu
 *
 */
@SuppressWarnings("serial")
public class ExtTransactionInterceptor extends TransactionInterceptor {

	/**
	 * Create a new TransactionInterceptor.
	 * <p>Transaction manager and transaction attributes still need to be set.
	 * @see #setTransactionManager
	 * @see #setTransactionAttributes(java.util.Properties)
	 * @see #setTransactionAttributeSource(TransactionAttributeSource)
	 */
	public ExtTransactionInterceptor() {
		super();
	}

	/**
	 * Create a new TransactionInterceptor.
	 * @param ptm the transaction manager to perform the actual transaction management
	 * @param attributes the transaction attributes in properties format
	 * @see #setTransactionManager
	 * @see #setTransactionAttributes(java.util.Properties)
	 */
	public ExtTransactionInterceptor(PlatformTransactionManager ptm, Properties attributes) {
		super(ptm, attributes);
	}

	/**
	 * Create a new TransactionInterceptor.
	 * @param ptm the transaction manager to perform the actual transaction management
	 * @param tas the attribute source to be used to find transaction attributes
	 * @see #setTransactionManager
	 * @see #setTransactionAttributeSource(TransactionAttributeSource)
	 */
	public ExtTransactionInterceptor(PlatformTransactionManager ptm, TransactionAttributeSource tas) {
		super(ptm, tas);
	}


	public Object invoke(final MethodInvocation invocation) throws Throwable {
		// Work out the target class: may be <code>null</code>.
		// The TransactionAttributeSource should be passed the target class
		// as well as the method, which may be from an interface.
		try {
			ShardUtils.compute(invocation);
			return super.invoke(invocation);
		} catch (Exception e) {
			throw e;
		}finally{
			ShardUtils.clean();
		}
		
	}

}

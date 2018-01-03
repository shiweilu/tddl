package com.sonic.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.TransactionInterceptor;

public class MultiDataSourceTransactionManager extends DataSourceTransactionManager implements BeanFactoryPostProcessor {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1699650099470012343L;

	private static final Logger logger = LoggerFactory
			.getLogger(MultiDataSourceTransactionManager.class);

	private Class<?> replaceAdviceClass = TransactionInterceptor.class;
	private Class<?> newAdviceClass = ExtTransactionInterceptor.class;
	private AtomicBoolean replaced = new AtomicBoolean();

	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		if (!replaced.compareAndSet(false, true)) {
			return;
		}

		String[] names = beanFactory.getBeanDefinitionNames();
		if (names != null) {
			for (String name : names) {
				//
				BeanDefinition beanDef = beanFactory.getBeanDefinition(name);
				if (beanDef != null) {
					if (replaceAdviceClass.getName().equals(
							beanDef.getBeanClassName())) {
						//
						logger.warn("advice " + replaceAdviceClass
								+ " is replaced by " + newAdviceClass);
						//
						beanDef.setBeanClassName(newAdviceClass.getName());
					}
				}
			}
		}
	}
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		super.doBegin(transaction, definition);
		/** epg切换数据库 **/
		/*Connection connection = DataSourceUtils.getConnection(this.getDataSource());
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("use test");
			ps.execute();
		} catch (Exception e) {
			
		}finally{
			try {
				if(ps != null){
					ps.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}*/
		/** **/
	}

}

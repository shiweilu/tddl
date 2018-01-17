package com.sonic.spring;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.util.Assert;
/**
 * 
 * @author shiweilu
 *
 */
public abstract class AbstractRoutingDataSource extends AbstractDataSource {

	private Map<String, DataSource> targetDataSources;
	private DataSource defaultDataSource;
	
	public AbstractRoutingDataSource(){
		
	}
	public void setTargetDataSources(HashMap<String, DataSource> targetDataSources) {
		this.targetDataSources = targetDataSources;
	}

	public DataSource getDefaultDataSource() {
		return defaultDataSource;
	}
	public void setDefaultDataSource(DataSource defaultDataSource) {
		this.defaultDataSource = defaultDataSource;
	}
	public Connection getConnection() throws SQLException {
		return determineTargetDataSource().getConnection();
	}

	public Connection getConnection(String username, String password) throws SQLException {
		return determineTargetDataSource().getConnection(username, password);
	}

	
	protected DataSource determineTargetDataSource() {
		Assert.notNull(this.targetDataSources, "DataSource router not initialized");
		Object lookupKey = determineCurrentLookupKey();
		DataSource dataSource = this.targetDataSources.get(lookupKey);
		if(dataSource == null && lookupKey == null){
			dataSource = this.defaultDataSource;
		}
		if (dataSource == null) {
			throw new IllegalStateException("Cannot determine target DataSource for lookup key [" + lookupKey + "]");
		}
		return dataSource;
	}

	protected abstract Object determineCurrentLookupKey();

}

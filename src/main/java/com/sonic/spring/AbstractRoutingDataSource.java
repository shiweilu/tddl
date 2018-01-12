package com.sonic.spring;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

	private Map<String, DataSource> unavailableDataSources = new ConcurrentHashMap<String, DataSource>();
	
	public AbstractRoutingDataSource(){
		
	}
	public void removeUnavailableDataSource(String key){
		DataSource dataSource = null;
		if((dataSource = this.targetDataSources.remove(key)) == null){
			throw new IllegalStateException("Cannot determine target DataSource for lookup key [" + key + "]");
		}
		unavailableDataSources.put(key, dataSource);
	}
	public void deleteDataSource(String key){
		if(this.targetDataSources.remove(key) == null){
			throw new IllegalStateException("Cannot determine target DataSource for lookup key [" + key + "]");
		}
	}
	public void addDataSource(String key,DataSource dataSource){
		this.targetDataSources.put(key, dataSource);
	}
	public void setTargetDataSources(ConcurrentHashMap<String, DataSource> targetDataSources) {
		this.targetDataSources = targetDataSources;
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
		if (dataSource == null) {
			throw new IllegalStateException("Cannot determine target DataSource for lookup key [" + lookupKey + "]");
		}
		return dataSource;
	}

	protected abstract Object determineCurrentLookupKey();

}

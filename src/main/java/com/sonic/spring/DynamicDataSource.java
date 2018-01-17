package com.sonic.spring;

import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sonic.base.DataSourceConfig;
import com.sonic.base.DataSourceFactory;
import com.sonic.base.DataSourceFactoryImpl;
import com.sonic.common.Constants;
/**
 * 
 * @author shiweilu
 *
 */
public class DynamicDataSource extends org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource {
	private static final Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);
	private List<DataSource> writeDataSources;
	private List<DataSource> readDataSources;
	private List<String> wdbkeys = new ArrayList<String>(); 
	private List<String> rdbkeys = new ArrayList<String>(); 
	private DataSourceFactory dataSourceFactory = new DataSourceFactoryImpl();
	private DataSourceConfig dataSourceConfig;
	private ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<Object, Object>();
	
	public DynamicDataSource(DataSourceConfig dataSourceConfig){
		try {
			this.dataSourceConfig = dataSourceConfig;
			initFromConfig(dataSourceConfig);
		} catch (Exception e) {
			logger.error("获取数据源配置信息出错：", e);
		}
	}
	public DynamicDataSource(){
		
	}
	public void setReadDataSources(List<DataSource> dataSources) {
		this.readDataSources = dataSources;
		map.clear();
		for(int i=0;readDataSources != null && i<readDataSources.size();i++){
			map.put(Constants.RDATASOURCEKEYPREFIX+i, readDataSources.get(i));
			rdbkeys.add(Constants.RDATASOURCEKEYPREFIX+i);
		}
		if(this.writeDataSources != null ){
			wdbkeys.clear();
			for(int i=0;writeDataSources != null && i<writeDataSources.size();i++){
				map.put(Constants.WDATASOURCEKEYPREFIX+i, writeDataSources.get(i));
				wdbkeys.add(Constants.WDATASOURCEKEYPREFIX+i);
			}
		}
		this.setDefaultTargetDataSource(readDataSources.get(0));
		this.setTargetDataSources(map);
	}

	public void setWriteDataSources(List<DataSource> dataSources) {
		this.writeDataSources = dataSources;
		map.clear();
		for(int i=0;writeDataSources != null && i<writeDataSources.size();i++){
			map.put(Constants.WDATASOURCEKEYPREFIX+i, writeDataSources.get(i));
			wdbkeys.add(Constants.WDATASOURCEKEYPREFIX+i);
		}
		if(this.readDataSources != null){
			rdbkeys.clear();
			for(int i=0;readDataSources != null && i<readDataSources.size();i++){
				map.put(Constants.RDATASOURCEKEYPREFIX+i, readDataSources.get(i));
				rdbkeys.add(Constants.RDATASOURCEKEYPREFIX+i);
			}
		}
		this.setDefaultTargetDataSource(writeDataSources.get(0));
		this.setTargetDataSources(map);
	}

	public void initFromConfig(DataSourceConfig dataSourceConfig){
		List<DataSource> dataSources = dataSourceFactory.initWriteDataSources(dataSourceConfig.getWriteDataSourceConfig());
		if(dataSources != null && dataSources.size()>0){
			this.setWriteDataSources(dataSources);
		}
		dataSources = dataSourceFactory.initReadDataSources(dataSourceConfig.getReadDataSourceConfig());
		if(dataSources != null && dataSources.size()>0){
			this.setReadDataSources(dataSources);
		}
	}
	@Override
	protected Object determineCurrentLookupKey() {
		return ContextHolder.getDataSourceKey();
	}
	
	public List<String> getReadDbKeys(){
		return this.rdbkeys;
	}
	public List<String> getWriteDbKeys(){
		return this.wdbkeys;
	}


}

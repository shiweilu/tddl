package com.sonic.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.sonic.common.Constants;

public class DataSourceFactoryImpl implements DataSourceFactory{
	public List<DataSource> initWriteDataSources(List<Map<String, String>> dataSourceConfigs){
		List<DataSource> dataSources = new ArrayList<DataSource>();
		Map<String, String> dataSourceConfig = null;
		DruidDataSource dataSource = null;
		for(int i=0;dataSourceConfigs!=null&&i<dataSourceConfigs.size();i++){
			dataSourceConfig = dataSourceConfigs.get(i);
			dataSource = new DruidDataSource();
			dataSources.add(dataSource);
			dataSource.setDriverClassName(dataSourceConfig.get(Constants.DRIVERCLASSNAME));
			dataSource.setUrl(dataSourceConfig.get(Constants.URL));
			dataSource.setUsername(dataSourceConfig.get(Constants.USERNAME));
			dataSource.setPassword(dataSourceConfig.get(Constants.PASSWORD));
			dataSource.setInitialSize(Integer.parseInt(dataSourceConfig.get(Constants.INITSIZE)));
			dataSource.setMaxActive(Integer.parseInt(dataSourceConfig.get(Constants.MAXACTIVE)));
			dataSource.setMinIdle(Integer.parseInt(dataSourceConfig.get(Constants.MINIDLE)));
			dataSource.setMaxWait(Integer.parseInt(dataSourceConfig.get(Constants.MAXWAIT)));
		}
		
		return dataSources;
	}
	public List<DataSource> initReadDataSources(List<Map<String, String>> dataSourceConfigs){
		if(dataSourceConfigs == null || dataSourceConfigs.size()==0) return null;
		List<DataSource> dataSources = new ArrayList<DataSource>();
		Map<String, String> dataSourceConfig = null;
		DruidDataSource dataSource = null;
		for(int i=0;dataSourceConfigs!=null&&i<dataSourceConfigs.size();i++){
			dataSourceConfig = dataSourceConfigs.get(i);
			dataSource = new DruidDataSource();
			dataSources.add(dataSource);
			dataSource.setDriverClassName(dataSourceConfig.get(Constants.DRIVERCLASSNAME));
			dataSource.setUrl(dataSourceConfig.get(Constants.URL));
			dataSource.setUsername(dataSourceConfig.get(Constants.USERNAME));
			dataSource.setPassword(dataSourceConfig.get(Constants.PASSWORD));
			dataSource.setInitialSize(Integer.parseInt(dataSourceConfig.get(Constants.INITSIZE)));
			dataSource.setMaxActive(Integer.parseInt(dataSourceConfig.get(Constants.MAXACTIVE)));
			dataSource.setMinIdle(Integer.parseInt(dataSourceConfig.get(Constants.MINIDLE)));
			dataSource.setMaxWait(Integer.parseInt(dataSourceConfig.get(Constants.MAXWAIT)));
		}
		
		return dataSources;
	}
}

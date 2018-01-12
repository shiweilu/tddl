package com.sonic.spring;

import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.sonic.common.Constants;
/**
 * 
 * @author shiweilu
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	private List<DataSource> writeDataSources;
	private List<DataSource> readDataSources;
	private List<String> wdbkeys = new ArrayList<String>(); 
	private List<String> rdbkeys = new ArrayList<String>(); 
	private ConcurrentHashMap<String, DataSource> map = new ConcurrentHashMap<String, DataSource>();
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
		this.setTargetDataSources(map);
	}
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
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

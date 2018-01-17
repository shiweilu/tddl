package com.sonic.base;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public interface DataSourceFactory {
	public List<DataSource> initWriteDataSources(List<Map<String, String>> dataSourceConfigs);
	public List<DataSource> initReadDataSources(List<Map<String, String>> dataSourceConfigs);
}

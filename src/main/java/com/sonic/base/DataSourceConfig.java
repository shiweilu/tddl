package com.sonic.base;

import java.util.List;
import java.util.Map;

public interface DataSourceConfig {
	public List<Map<String, String>> getWriteDataSourceConfig();
	public List<Map<String, String>> getReadDataSourceConfig();
}

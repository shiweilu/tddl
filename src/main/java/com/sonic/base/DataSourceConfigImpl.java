package com.sonic.base;

import java.util.List;
import java.util.Map;

public class DataSourceConfigImpl implements DataSourceConfig{
	private String writeKey;
	private String readKey;
	private DoveConfig doveConfig;
	public DataSourceConfigImpl(String writeKey,String readKey) {
		this.writeKey = writeKey;
		this.readKey = readKey;
		//doveConfig = new DoveConfig(this.readKey,this.writeKey);
	}
	@Override
	public List<Map<String, String>> getWriteDataSourceConfig() {
		return doveConfig.getWriteDataSourceConfigs();
	}

	@Override
	public List<Map<String, String>> getReadDataSourceConfig() {
		return doveConfig.getReadDataSourceConfigs();
	}

}

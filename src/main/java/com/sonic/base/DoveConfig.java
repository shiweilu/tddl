package com.sonic.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.sonic.common.Constants;


public class DoveConfig
{
    private static final Logger logger = LoggerFactory.getLogger(DoveConfig.class);

	public List<Map<String, String>> getReadDataSourceConfigs() {
		//return readDataSourceConfigs;
		List<Map<String, String>> configs = new ArrayList<Map<String,String>>();
		Map<String, String> config = new HashMap<String, String>();
		config.put(Constants.DRIVERCLASSNAME, "com.mysql.jdbc.Driver");
		config.put(Constants.URL, "jdbc:mysql://127.0.0.1:3306/user_test3?useUnicode=true&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull");
		config.put(Constants.USERNAME, "root");
		config.put(Constants.PASSWORD, "123456");
		config.put(Constants.INITSIZE, "2");
		config.put(Constants.MAXACTIVE, "5");
		config.put(Constants.MAXWAIT, "30000");
		config.put(Constants.MINIDLE, "2");
		configs.add(config);
		config = new HashMap<String, String>();
		config.put(Constants.DRIVERCLASSNAME, "com.mysql.jdbc.Driver");
		config.put(Constants.URL, "jdbc:mysql://127.0.0.1:3306/user_tes4?useUnicode=true&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull");
		config.put(Constants.USERNAME, "root");
		config.put(Constants.PASSWORD, "123456");
		config.put(Constants.INITSIZE, "2");
		config.put(Constants.MAXACTIVE, "5");
		config.put(Constants.MAXWAIT, "30000");
		config.put(Constants.MINIDLE, "2");
		configs.add(config);
		return configs;
	}
	public List<Map<String, String>> getWriteDataSourceConfigs() {
		//return writeDataSourceConfigs;
		List<Map<String, String>> configs = new ArrayList<Map<String,String>>();
		Map<String, String> config = new HashMap<String, String>();
		config.put(Constants.DRIVERCLASSNAME, "com.mysql.jdbc.Driver");
		config.put(Constants.URL, "jdbc:mysql://127.0.0.1:3306/user_test_1?useUnicode=true&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull");
		config.put(Constants.USERNAME, "root");
		config.put(Constants.PASSWORD, "123456");
		config.put(Constants.INITSIZE, "2");
		config.put(Constants.MAXACTIVE, "5");
		config.put(Constants.MAXWAIT, "30000");
		config.put(Constants.MINIDLE, "2");
		configs.add(config);
		config = new HashMap<String, String>();
		config.put(Constants.DRIVERCLASSNAME, "com.mysql.jdbc.Driver");
		config.put(Constants.URL, "jdbc:mysql://127.0.0.1:3306/user_test_2?useUnicode=true&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull");
		config.put(Constants.USERNAME, "root");
		config.put(Constants.PASSWORD, "123456");
		config.put(Constants.INITSIZE, "2");
		config.put(Constants.MAXACTIVE, "5");
		config.put(Constants.MAXWAIT, "30000");
		config.put(Constants.MINIDLE, "2");
		configs.add(config);
		return configs;
	}


}

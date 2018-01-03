package com.sonic.spring;

public class ContextHolder {
	private static ThreadLocal<String> dataSourceKeys = new ThreadLocal<String>();
	private static ThreadLocal<Integer> tableSuffixs = new ThreadLocal<Integer>();

	public static void setDataSourceKey(String key) {
		dataSourceKeys.set(key);
	}
	public static String getDataSourceKey(){
		return dataSourceKeys.get();
	}
	public static void setTableSuffix(Integer suffix){
		tableSuffixs.set(suffix);
	}
	public static Integer getTableSuffix(){
		return tableSuffixs.get();
	}
}

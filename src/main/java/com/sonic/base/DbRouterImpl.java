package com.sonic.base;

import java.util.List;

import com.sonic.spring.DynamicDataSource;
/**
 * 
 * @author shiweilu
 *
 */
public class DbRouterImpl{
	
	private static DynamicDataSource dynamicDataSource;
	
	public static ShardStratety shardStratety;
	
	public ShardStratety getShardStratety() {
		return shardStratety;
	}

	public void setShardStratety(ShardStratety shardStratety) {
		DbRouterImpl.shardStratety = shardStratety;
	}

	public DynamicDataSource getDynamicDataSource() {
		return dynamicDataSource;
	}

	public void setDynamicDataSource(DynamicDataSource dynamicDataSource) {
		DbRouterImpl.dynamicDataSource = dynamicDataSource;
	}

	public static String getDbKey(List<Object> filedsValue,boolean write) {
		if(write){
			return DbRouterImpl.dynamicDataSource.getWriteDbKeys().get(shardStratety.getDbIndex(filedsValue, dynamicDataSource.getWriteDbKeys().size()));
		}else{
			return DbRouterImpl.dynamicDataSource.getReadDbKeys().get(shardStratety.getDbIndex(filedsValue, dynamicDataSource.getReadDbKeys().size()));
		}
		
	}
	public static Integer getTableSuffix(List<Object> filedsValue){
		return shardStratety.getTableSuffix(filedsValue);
	}


}

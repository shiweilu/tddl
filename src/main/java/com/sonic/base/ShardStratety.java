package com.sonic.base;

import java.util.List;
/**
 * 
 * @author shiweilu
 *
 */
public interface ShardStratety {
	public int getDbIndex(List<Object> filedsValue,int tableNums);
	public int getTableSuffix(List<Object> filedsValue);
	
}

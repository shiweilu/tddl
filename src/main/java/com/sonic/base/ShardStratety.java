package com.sonic.base;

import java.util.List;

public interface ShardStratety {
	public int getDbIndex(List<Object> filedsValue,int tableNums);
	public int getTableSuffix(List<Object> filedsValue);
	
}

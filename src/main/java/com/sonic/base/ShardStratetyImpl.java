package com.sonic.base;

import java.util.List;

import com.sonic.common.HashUtils;

public class ShardStratetyImpl implements ShardStratety{
	
	private int tableNums;
	public void setTableNums(int tableNums) {
		this.tableNums = tableNums;
	}

	@Override
	public int getDbIndex(List<Object> filedsValue, int dbNums) {
		return HashUtils.getHashCode(filedsValue)%dbNums;
	}

	@Override
	public int getTableSuffix(List<Object> filedsValue) {
		return HashUtils.getHashCode(filedsValue)%tableNums;
	}

}

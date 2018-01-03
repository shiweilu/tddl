package com.sonic.common;

import java.util.List;

public class HashUtils {
	public static int getHashCode(List<Object> list){
		if(list == null || list.size() == 0){
			throw new RuntimeException("shard fileds can't be null or empty");
		}
		StringBuffer sb = new StringBuffer();
		Object obj = null;
		for(int i=0; i<list.size();i++){
			obj = list.get(i);
			sb.append(obj.toString());
		}
		return Math.abs(sb.toString().hashCode());
	}
}

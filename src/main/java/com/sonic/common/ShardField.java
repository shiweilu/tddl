package com.sonic.common;
/**
 * 
 * @author shiweilu
 *
 */
public class ShardField {
	private Object[] fields;
	public ShardField(Object... fileds){
		this.fields = fileds;
	}
	public Object[] getShardFields(){
		return this.fields;
	}
}

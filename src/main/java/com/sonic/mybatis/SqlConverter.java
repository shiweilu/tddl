package com.sonic.mybatis;

import org.apache.ibatis.executor.statement.StatementHandler;
/**
 * 
 * @author shiweilu
 *
 */
public interface SqlConverter {

	public String convert(String sql, StatementHandler statementHandler);

}

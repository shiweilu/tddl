package com.sonic.mybatis;

import org.apache.ibatis.executor.statement.StatementHandler;

import com.sonic.spring.ContextHolder;
/**
 * 
 * @author shiweilu
 *
 */
public class DefaultSqlConverter implements SqlConverter {

	public String convert(String sql, StatementHandler statementHandler) {
		return sql.replaceAll("\\$\\[index\\]\\$", ContextHolder.getTableSuffix().toString());
	}

}

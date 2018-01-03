package com.sonic.common;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.beanutils.BeanUtils;

import com.sonic.base.DbRouterImpl;
import com.sonic.spring.ContextHolder;

public class ShardUtils {
	public static void compute(MethodInvocation invocation)throws Exception{
		Method method = invocation.getMethod();
		DbRouter router = method.getAnnotation(DbRouter.class);
		if(router == null){
			router = invocation.getThis().getClass().getAnnotation(DbRouter.class);
			if(router == null){
				throw new RuntimeException("DbRouter can't be null on method "+ method);
			}
		}
		String[] shardFileds = router.shardField();
		Object[] params = invocation.getArguments();
		List<Object> routeFieldValues = new ArrayList<Object>();
		if(params != null && params.length > 0){
			for(int i = 0 ;i < params.length;i++){
				for(int j=0 ;j<shardFileds.length; j++){
					routeFieldValues.add(BeanUtils.getProperty(params[i],shardFileds[j])); 
				}
				 
			}
		}
		if(routeFieldValues == null || routeFieldValues.size() == 0){
			throw new RuntimeException("can't get shard fileds on mehtod "+ method);
		}
		ContextHolder.setDataSourceKey(DbRouterImpl.getDbKey(routeFieldValues,router.write()));
		ContextHolder.setTableSuffix(DbRouterImpl.getTableSuffix(routeFieldValues));
	}
	public static void clean(){
		ContextHolder.setDataSourceKey(null);
		ContextHolder.setTableSuffix(null);
	}
}

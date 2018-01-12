package com.sonic.common;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
/**
 * 
 * @author shiweilu
 *
 */
public class ReflectionUtils {
	public static void setFieldValue(final Object object, final String fieldName, final Object value) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		}
		makeAccessible(field);

		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	protected static Field getDeclaredField(final Object object, final String fieldName) {
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}
	protected static void makeAccessible(final Field field) {
		if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			field.setAccessible(true);
		}
	}
}

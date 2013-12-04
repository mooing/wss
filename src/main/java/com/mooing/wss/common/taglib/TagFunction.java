package com.mooing.wss.common.taglib;

import java.util.Collection;
import java.util.List;

public class TagFunction {
	public static boolean contains(List<Integer> list, long obj){
		Integer v = (int) obj;//el treat 1,2 as long type,so we need parameter type long, and then convert to int
		if(list == null){
			return false;
		}else{
			return list.contains(v);
		}
	}
	
	public static Object constantVal(String constFullName){
		int lastDotIndex = constFullName.lastIndexOf(".");
		String classFullName = constFullName.substring(0,lastDotIndex);
		String constantName = constFullName.substring(lastDotIndex+1);
		
		Object result = null;
		try {
			Class<?> klass = Class.forName(classFullName);
			result = klass.getDeclaredField(constantName).get(klass);
		} catch (Exception e) {
			throw new IllegalArgumentException("Get constant with full name: " + constFullName + " failed: constFullName must be a fullName of a constant", e);
		}
		return result;
	}
	public static boolean isContains(Collection<?> collection, Object object){
		if(collection != null && object != null){
			return collection.contains(object);
		}else{
			return false;
		}
		
	}
	
	
}

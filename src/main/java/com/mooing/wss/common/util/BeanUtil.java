/*
 * Copyright (c) 2011 Qunar.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mooing.wss.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;

/**
 *  Bean处理相关工具类
 *
 * @see com.mooing.wss.common.util.PropertyAccessException
 *
 */
public final class BeanUtil {
	public static <T> List<Integer> getIds(List<T> beanList){
		if(beanList.isEmpty()){
			return Collections.emptyList();
		}
		
		List<Integer> ids = new ArrayList<Integer>();
		Class<? extends Object> beanClass = beanList.get(0).getClass();
		
		try {
			Method method = beanClass.getMethod("getId");
			for (T beanObj : beanList) {
				ids.add((Integer)method.invoke(beanObj));
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Element of argument must be support getId() method");
		} 
		
		return ids;
	}
    /**
     * 设置指定的JavaBean属性的值
     *
     * @param bean JavaBean
     * @param property 属性名
     * @param value 属性值
     * @throws PropertyAccessException 异常
     */
    public static void setBeanProperty(Object bean, String property,
            Object value) throws PropertyAccessException {

        try {
            // 设置属性值
            PropertyUtils.setProperty(bean, property, value);
        } catch (IllegalArgumentException e) {
            throw new PropertyAccessException(e);
        } catch (IllegalAccessException e) {
            throw new PropertyAccessException(e);
        } catch (InvocationTargetException e) {
            throw new PropertyAccessException(e.getTargetException());
        } catch (NoSuchMethodException e) {
            throw new PropertyAccessException(e);
        }
    }

    /**
     * 获取指定的JavaBean属性的值
     *
     * @param bean JavaBean
     * @param property 属性名
     * @return value 属性值
     * @throws PropertyAccessException 异常
     */
    public static Object getBeanProperty(Object bean, String property)
            throws PropertyAccessException {

        Object value = null;
        try {
            value = PropertyUtils.getProperty(bean, property);
        } catch (IllegalArgumentException e) {
            throw new PropertyAccessException(e);
        } catch (IllegalAccessException e) {
            throw new PropertyAccessException(e);
        } catch (InvocationTargetException e) {
            throw new PropertyAccessException(e.getTargetException());
        } catch (NoSuchMethodException e) {
            throw new PropertyAccessException(e);
        }
        return value;
    }

    /**
     * 获取指定的JavaBean属性的类型
     * @param bean JavaBean
     * @param property 属性名
     * @return 属性类型
     * @throws PropertyAccessException 异常
     */
    @SuppressWarnings("rawtypes")
	public static Class getBeanPropertyType(Object bean, String property) 
        throws PropertyAccessException {
        try {
            Class type = null;
            if (bean instanceof DynaBean) {
                DynaProperty descriptor = ((DynaBean) bean).getDynaClass()
                    .getDynaProperty(property);
                if (descriptor != null){
                    type = descriptor.getType();
                }
            }
            else{
                type = PropertyUtils.getPropertyType(bean, property);
            }
            return type;
        } catch (IllegalArgumentException e) {
            throw new PropertyAccessException(e);
        } catch (IllegalAccessException e) {
            throw new PropertyAccessException(e);
        } catch (InvocationTargetException e) {
            throw new PropertyAccessException(e);
        } catch (NoSuchMethodException e) {
            throw new PropertyAccessException(e);
        }
    }
}

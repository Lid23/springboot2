package com.noodles.bean.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.noodles.log.MySlf4j;

/**
 * @filename GlobalBeanUtils
 * @description BeanUtils工具类
 * @autor noodles
 * @date 2019/8/6 9:32
 */
public class GlobalBeanUtils {

	/**
	 * 空构造函数
	 * @return
	 * @author noodles
	 * @date 2019/8/6 10:58
	 */
	private GlobalBeanUtils() {

	}

	/**
	 * 将对象Obj转为Map
	 * @param object 需要初始化的数据
	 * @return java.util.Map<java.lang.String                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               java.lang.Object>
	 * @author noodles
	 * @date 2019/8/6 9:44
	 */
	public static Map<String, Object> objectToMap(Object object) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!"class".equals(propertyName)) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(object, new Object[0]);
					map.put(propertyName, result);
				}
			}
		} catch (Exception e) {
			MySlf4j.textError("将对象Obj转为Map异常,异常信息:{0}", MySlf4j.ExceptionToString(e));
			throw e;
		}
		return map;
	}

	/**
	 * Map转Obj对象
	 * @param map  需要初始化的数据
	 * @param clazz  需要转化成的实体类
	 * @return java.lang.Object
	 * @author noodles
	 * @date 2019/8/6 9:50
	 */
	public static final Object mapToObject(Map<String, ? extends Object> map, Class<?> clazz) throws Exception {
		Object object = null;
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			object = clazz.newInstance();
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (map.containsKey(propertyName)) {
					Object value = map.get(propertyName);
					Object[] args = new Object[] { value };
					descriptor.getWriteMethod().invoke(object, args);
				}
			}
		} catch (Exception e) {
			MySlf4j.textError("将Map转Obj对象异常,异常信息:{0}", MySlf4j.ExceptionToString(e));
			throw e;
		}
		return object;
	}

	/**
	 * 对象复制
	 * @param source 源对象
	 * @param target 目标对象
	 * @return void
	 * @author noodles
	 * @date 2019/8/6 9:54
	 */
	public static void copyProperties(Object source, Object target) {
		try {
			BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
		} catch (Exception e) {
			MySlf4j.textError("对象复制,异常信息:{0}", MySlf4j.ExceptionToString(e));
			throw e;
		}
	}

	/**
	 * 功能描述 获取对象时null的字段名称数组
	 * @param source 源对象
	 * @return java.lang.String[]
	 * @author noodles
	 * @date 2019/12/23 17:20
	 */
	private static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
		Set<String> emptyNames = new HashSet<>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null){
				emptyNames.add(pd.getName());
			}
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

}

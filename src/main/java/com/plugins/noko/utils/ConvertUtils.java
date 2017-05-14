package com.plugins.noko.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by david.yun on 2017/5/14.
 */
public class ConvertUtils {
    static Logger logger = LoggerFactory.getLogger(ConvertUtils.class);

    public static <T> T mapToBean(Map<String, Object> map, Class<T> classT) {
        T bean = null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(classT);
            bean = classT.newInstance();
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                String propertyName = propertyDescriptor.getName();
                if (map.containsKey(propertyName)) {
                    Object value = map.get(propertyName);
                    propertyDescriptor.getWriteMethod().invoke(bean, value);
                }
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            logger.error(e.toString());
        }
        return bean;
    }

    public static Map<String, Object> beanToMap(Object object) {
        if (object == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                String key = propertyDescriptor.getName();
                if (!key.equals("class")) {
                    Method getter = propertyDescriptor.getReadMethod();
                    Object value = getter.invoke(object);
                    map.put(key, value);
                }
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            logger.error(e.toString());
        }
        return map;
    }
}

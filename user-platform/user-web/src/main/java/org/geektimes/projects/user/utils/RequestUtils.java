package org.geektimes.projects.user.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author zshuo
 * @date 2021/3/3
 **/
public class RequestUtils {
    public static <T> T parseParameter(HttpServletRequest request, Class<T> clazz) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
        Constructor<T> constructor = clazz.getConstructor();
        T t = constructor.newInstance();
        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            String name = propertyDescriptor.getName();
            String parameter = request.getParameter(name);
            Method writeMethod = propertyDescriptor.getWriteMethod();
            if (!StringUtils.isEmpty(parameter)) {
                writeMethod.invoke(t, parameter);
            }
        }
        return t;
    }
}

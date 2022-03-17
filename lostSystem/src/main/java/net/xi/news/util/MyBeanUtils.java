package net.xi.news.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;


public class MyBeanUtils {


	/**
	 * BeanUtils工具一般可以方便javaBean的哪些操作？
	 * 1）beanUtils 可以便于对javaBean的属性进行赋值。
	 * 2）beanUtils 可以便于对javaBean的对象进行赋值。
	 * 3）beanUtils可以将一个MAP集合的数据拷贝到一个javabean对象中。
	 * 获取所有的属性值为空属性名数组
	 *
	 * @param source
	 * @return
	 */
	public static String[] getNullPropertyNames(Object source) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(source);
		PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
		List<String> nullPropertyNames = new ArrayList<>();
		for (PropertyDescriptor pd : pds) {
			String propertyName = pd.getName();
			if (beanWrapper.getPropertyValue(propertyName) == null) {
				nullPropertyNames.add(propertyName);
			}
		}
		return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);
	}

}

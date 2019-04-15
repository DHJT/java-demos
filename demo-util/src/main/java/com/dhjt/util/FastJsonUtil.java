package com.dhjt.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.alibaba.fastjson.util.TypeUtils;
import com.dhjt.bean.User;

/**
 * FastJson工具类
 * @author DHJT 2018年11月23日 下午4:02:21
 *
 */
@SuppressWarnings("all")
public class FastJsonUtil {
	private static final Logger logger = LoggerFactory.getLogger(FastJsonUtil.class);

	public static final <T> List<T> getList(String jsontext, String list_str, Class<T> clazz) {
		JSONObject jsonobj = JSON.parseObject(jsontext);
		if (jsonobj == null) {
			return null;
		}
		Object obj = jsonobj.get(list_str);
		if (obj == null) {
			return null;
		}
		// if(obj instanceof JSONObject){}
		if (obj instanceof JSONArray) {
			JSONArray jsonarr = (JSONArray) obj;
			List<T> list = new ArrayList<T>();
			for (int i = 0; i < jsonarr.size(); i++) {
				list.add(jsonarr.getObject(i, clazz));
			}
			return list;
		}
		return null;
	}

	/**
	 * @param          <T> -> DepartmentBean
	 * @param jsontext -> {"department":{"id":"1","name":"生产部"},"password":"admin",
	 *                 "username":"admin"}
	 * @param obj_str  -> department
	 * @param clazz    -> DepartmentBean
	 * @return -> T
	 */
	public static final <T> T getObject(String jsontext, String obj_str, Class<T> clazz) {
		JSONObject jsonobj = JSON.parseObject(jsontext);
		if (jsonobj == null) {
			return null;
		}

		Object obj = jsonobj.get(obj_str);
		if (obj == null) {
			return null;
		}

		if (obj instanceof JSONObject) {
			return jsonobj.getObject(obj_str, clazz);
		} else {
			logger.info(obj.getClass() + "");
		}
		return null;
	}

	/**
	 * @param          <T>
	 * @param jsontext ->{"department":{"id":"1","name":"生产部"},"password":"admin",
	 *                 "username":"admin"}
	 * @param clazz    -> UserBean.class
	 * @return -> UserBean
	 */
	// 注：传入任意的jsontext,返回的T都不会为null,只是T的属性为null
	public static final <T> T getObject(String jsontext, Class<T> clazz) {
		T t = null;
		try {
			t = JSON.parseObject(jsontext, clazz);
		} catch (Exception e) {
			logger.error("json字符串转换失败！" + jsontext, e);
		}
		return t;
	}

	public static final String toJSONString(Object object, boolean prettyFormat) {
		if (object instanceof Collection) {
			System.out.println("2018年12月19日 下午9:02:33");
//			JSONArray.fromObject(object, null).toString()
			return JSONArray.toJSONString(object);
		} else {
//			JSONObject.fromObject(object, null).toString();
			return JSON.toJSONString(object, prettyFormat);
		}
	}

	// 指定的字段才能显示出来
	public static final String toJSONString1(Object object, final String[] arr) {
//		SimplePropertyPreFilter filter = new SimplePropertyPreFilter("title", "thumbUrl", "url");
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(arr);
		return JSONObject.toJSONString(object, filter);
	}

	/**
	 * 过滤指定字段
	 *
	 * @param object
	 * @param arr
	 * @return
	 */
	public static final String toJSONString(Object object, final String[] arr) {
		PropertyFilter propertyFilter = new PropertyFilter() {
			@Override
			public boolean apply(Object object, String name, Object value) {
				for (String string : arr) {
					if (name.equalsIgnoreCase(string)) {
						return false;// 过滤掉
					}
				}
				return true;// 不过滤
			}
		};
		if (object instanceof Collection) {
			System.out.println("2018年12月19日下午9:26:21->121");
			return JSONObject.toJSONString(object, propertyFilter);
		} else {
			return JSONObject.toJSONString(object, propertyFilter);
//			return JSON.toJSONString(object, false);
		}
	}

	/**
	 * @Description： json字符串转成为List
	 *
	 * @param jsonStr json字符串
	 * @param clazz   class名称
	 * @return
	 */
	public static <T> List<T> getList(String jsonStr, Class<T> clazz) {
		List<T> list = new ArrayList<T>();
		try {
			list = JSON.parseArray(jsonStr, clazz);
		} catch (Exception e) {
			logger.error("json字符串转List失败！" + jsonStr, e);
		}
		return list;
	}

	/**
	 * 暴力解析:Alibaba fastjson
	 *
	 * @param json
	 * @return
	 */
	public static final boolean mayBeJSON(String json) {
		try {
			JSONObject.parseObject(json);
		} catch (JSONException ex) {
			try {
				JSONObject.parseArray(json);
			} catch (JSONException ex1) {
				return false;
			}
		}
		return true;
	}

	/**
	 *
	 * @Description： json字符串转换成list<Map>
	 *
	 * @param jsonString json字符串
	 * @return
	 */
	public static List<Map<String, Object>> listKeyMaps(String jsonString) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = JSON.parseObject(jsonString, new TypeReference<List<Map<String, Object>>>() {
			});
		} catch (Exception e) {
			logger.error("json字符串转map失败", e);
		}
		return list;
	}

	/**
	 * @Description： json字符串转换为Map
	 *
	 * @author: GuXiYang
	 * @date: 2017/05/08 10:32:33
	 * @param jsonStr json字符串
	 * @return
	 */
	public static Map<String, Object> json2Map(String jsonStr) {
		try {
			return JSON.parseObject(jsonStr, Map.class);
		} catch (Exception e) {
			logger.error("json字符串转换失败！" + jsonStr, e);
		}
		return null;
	}

	public static void main(String[] args) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("tag", "data");
		dataMap.put("hospitalName", "成都市妇幼保健二院");
		dataMap.put("name", "皇甫凡凡");
		dataMap.put("gender", "女");
		dataMap.put("age", "18");
		dataMap.put("code", "410505550");
		dataMap.put("examineDoc", "孙龙");
		dataMap.put("examineDate", "2018-11-23");
		dataMap.put("verifyDoc", "张飞");
		User user = new User();
		user.setId("123");
		user.setName("slh");
		user.setEmail("dhjt11@qq.com");
		dataMap.put("user", user);
		String jsonString = JSON.toJSONString(dataMap);
		System.out.println(jsonString);
		Map object = getObject(jsonString, Map.class);
		System.out.println(object.get("age"));
		System.out.println("2019年1月1日下午11:21:51->" + TypeUtils.castToSqlDate("2018-12-12").getMinutes());
	}
}

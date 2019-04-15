package com.dhjt.json;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonBeanProcessor;

public class HibernateJsonBeanProcessor implements JsonBeanProcessor {

	public JSONObject processBean(Object arg0, JsonConfig arg1) {
		return new JSONObject();
	}

}

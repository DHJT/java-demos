package com.dhjt.json;

import java.util.Set;

import org.apache.log4j.Logger;

import net.sf.json.processors.JsonBeanProcessorMatcher;

public class HibernateJsonBeanProcessorMatcher extends JsonBeanProcessorMatcher {
	private static Logger log = Logger
			.getLogger(HibernateJsonBeanProcessorMatcher.class);

	@Override
	public Object getMatch(Class target, Set set) {
		if (target.getName().contains("$$EnhancerByCGLIB$$")) {
			log.warn("Found Lazy-References in Hibernate object "
					+ target.getName());
			return org.hibernate.proxy.HibernateProxy.class;
		}
		return DEFAULT.getMatch(target, set);
	}

}

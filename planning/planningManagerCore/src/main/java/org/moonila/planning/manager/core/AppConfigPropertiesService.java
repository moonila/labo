package org.moonila.planning.manager.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class AppConfigPropertiesService {

	private static final String APP_CONFIG = "wsRestConfig.properties";
	private static final String APP_CONFIG_PROPERTIES = "wsRestConfig";

	private Map<String, String> properties;

	private static class PropertiesConfigurationHolder {
		private final static AppConfigPropertiesService instance = new AppConfigPropertiesService();
	}

	public static AppConfigPropertiesService getInstance() {
		return PropertiesConfigurationHolder.instance;
	}

	public AppConfigPropertiesService() {
		properties = new HashMap<String, String>();
		populateAppConfigMapProperties();
	}

	private void populateAppConfigMapProperties() {
		Properties props = retrieveProperties();

		Iterator<Object> it = props.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			properties.put(key, props.getProperty(key));
		}
	}

	private Properties retrieveProperties() {
		Properties props = new Properties();
		String filePath = System.getProperty(APP_CONFIG_PROPERTIES);
		try {
			if (filePath != null && !filePath.isEmpty()) {
				props.load(new FileInputStream(filePath));
			} else {
				InputStream inputStream = getClass().getClassLoader()
						.getResourceAsStream(APP_CONFIG);
				props.load(inputStream);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

	public Map<String, String> getProperties() {
		return properties;
	}
}

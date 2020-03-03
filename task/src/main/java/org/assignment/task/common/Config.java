package org.assignment.task.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

	private static Properties properties;

	public static void initilize() {
		InputStream is = Config.class.getClassLoader().getResourceAsStream("config.properties");
		properties = new Properties();

		if (is != null) {
			try {
				properties.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static String getHomePageUrl() {
		return properties.getProperty("homepageUrl");
	}

	public static String getUsername() {
		return properties.getProperty("username");
	}

	public static String getPassword() {
		return properties.getProperty("password");
	}

	public static String getApiUrl() {
		return properties.getProperty("apiHost");
	}

}

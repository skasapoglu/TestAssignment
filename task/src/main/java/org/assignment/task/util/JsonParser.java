package org.assignment.task.util;

import com.google.gson.Gson;

public class JsonParser {

	private Gson gson = new Gson();

	public Object deserializeJson(String json, Class<?> clazz) {
		return gson.fromJson(json, clazz);
	}

	public String serializeObject(Object clazz) {
		return gson.toJson(clazz);
	}

}

package org.assignment.task.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.assignment.task.model.UserData;
import org.assignment.task.model.UserResponse;
import org.assignment.task.util.JsonParser;

public class TestUtil {

	public static UserData readUserInfo() throws IOException {

		String fileName = "user_info.json";
		JsonParser parser = new JsonParser();

		File file = loadFile(fileName);
		UserResponse userResponse = (UserResponse) parser.deserializeJson(readFileContentsAsJson(file),
				UserResponse.class);
		UserData data = userResponse.getData();

		return data;

	}

	private static File loadFile(String fileName) {

		ClassLoader classLoader = new TestUtil().getClass().getClassLoader();

		URL resource = classLoader.getResource(fileName);

		if (resource == null) {
			throw new IllegalArgumentException("test file" + fileName + " is not found!");
		}

		return new File(resource.getFile());
	}

	private static String readFileContentsAsJson(File file) {
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = null;
		String line = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return builder.toString();
	}

}

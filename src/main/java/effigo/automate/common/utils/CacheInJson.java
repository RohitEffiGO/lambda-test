package effigo.automate.common.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheInJson extends Loader {
	static Random random = new Random();

	private static String generateFileName() {
		int leftLimit = 97;
		int rightLimit = 122;
		int targetStringLength = 10;
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (random.nextInt() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		return generatedString + ".json";
	}

	public static void createFileInCacheFolder() {

		String cacheFolderPath = runnerPath + "\\src\\test\\resources\\cache\\";
		String fileName;

		if (allArgsMap.containsKey("cache.file.path")) {
			cacheFolderPath = allArgsMap.get("cache.file.path");
		}

		fileName = (allArgsMap.containsKey("cache.file.name")) ? allArgsMap.get("cache.file.name") : generateFileName();

		File cacheFolder = new File(cacheFolderPath);
		if (!cacheFolder.exists()) {
			cacheFolder.mkdir();
			File newFile = new File(cacheFolder, fileName);
			try {
				FileWriter writer = new FileWriter(newFile);
				writer.close();
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
	}

	public static void overrideAllArgsMap() {
		String path = runnerPath + "\\src\\test\\resources\\cache\\file.json"; // Default path
		if (allArgsMap.containsKey("cache.folder.path") && allArgsMap.containsKey("cache.file.name")) {
			path = runnerPath + allArgsMap.get("cache.folder.path") + "\\" + allArgsMap.get("cache.file.name");
		}

		try {
			String jsonString = new String(Files.readAllBytes(Paths.get(path)));

			Gson gson = new Gson();

			@SuppressWarnings("unchecked")
			Map<String, Map<String, String>> jsonObject = gson.fromJson(jsonString, Map.class);
			cachedData = jsonObject;

			if (jsonObject == null) {
				return;
			}
			if (jsonObject.containsKey("default")) {
				Map<String, String> replaceMap = jsonObject.get("default");
				replaceMap.forEach((key, value) -> {
					if (allArgsMap.containsKey(key)) {
						allArgsMap.put(key, value);
					}
				});
			} else {
				throw new IOException();
			}

		} catch (IOException e) {

		}
	}

	public static void overrideAllArgsMap(String stage) throws Exception {
		String path = runnerPath + "\\src\\test\\resources\\cache\\file.json"; // Default path
		if (allArgsMap.containsKey("cache.folder.path") && allArgsMap.containsKey("cache.file.name")) {
			path = runnerPath + allArgsMap.get("cache.folder.path") + "\\" + allArgsMap.get("cache.file.name");
		}

		try {
			String jsonString = new String(Files.readAllBytes(Paths.get(path)));

			Gson gson = new Gson();

			@SuppressWarnings("unchecked")
			Map<String, Map<String, String>> jsonObject = gson.fromJson(jsonString, Map.class);
			cachedData = jsonObject;

			if (jsonObject.containsKey(stage)) {
				Map<String, String> replaceMap = jsonObject.get(stage);
				replaceMap.forEach((key, value) -> {
					if (allArgsMap.containsKey(key)) {
						allArgsMap.put(key, value);
					}
				});
			} else {
				throw new IOException();
			}

		} catch (IOException e) {
			log.info("Something went wrong.");
			throw new Exception();
		}
	}

	public static void setCache(String stage, Map<String, String> data) {
		if (cacheTodoMap == null) {
			cacheTodoMap = new HashMap<>();
		}
		cacheTodoMap.put(stage, data);
	}

	public static Map<String, String> getCache(String stage) {
		return cachedData.get(stage);
	}

	public static void writeIntoCache() {
		Gson gson = new Gson();
		String jsonString = gson.toJson(cacheTodoMap);

		String filePath = runnerPath + "\\src\\test\\resources\\cache\\" + generateFileName();

		if (allArgsMap.containsKey("cache.folder.path") && allArgsMap.containsKey("cache.file.name")) {
			filePath = runnerPath + allArgsMap.get("cache.folder.path") + "\\" + allArgsMap.get("cache.file.name");
		}

		try (FileWriter writer = new FileWriter(filePath)) {
			writer.write(jsonString);
		} catch (IOException e) {
			System.out.println("Error writing JSON to file: " + e.getMessage());
			e.printStackTrace();
		}
	}
}

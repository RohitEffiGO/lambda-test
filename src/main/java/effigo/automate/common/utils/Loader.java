package effigo.automate.common.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Loader {
	public static List<List<String>> coreDataList;
	public static Map<String, Map<String, String>> cacheTodoMap;
	public static String csvPath = "";
	public static String browser = "";
	public static RemoteWebDriver driver;
	public static String url;
	public static String runnerPath = System.getProperty("user.dir");
	public static Map<String, String> allArgsMap = new HashMap<>();
	public static Map<String, Map<String, String>> cachedData = new HashMap<>();
	public static final String CONFIGPATHSTRING = runnerPath + "/src/test/resources/config.properties";
	public static String loginUsername;
	public static String loginPassword;
	Properties properties = new Properties();

	public String username = "rohit.thakureffigo";
	public String accesskey = "YmXkfqbnNZQOpoO39JUdY3rECfRIVBNTHZSLaO1VQIW5eSKP6K";
	public String gridURL = "@hub.lambdatest.com/wd/hub";

	HandleAlertsPopUpsDailogue handler;
	ElementAction elementAction;

	@BeforeSuite(alwaysRun = true)
	public void setup() {
		browser = loadBrowser();
		loadDriver();
		loadCSV();
		loadURL();
		loadAllKeys();

		String cacheOption = allArgsMap.get("cache.enable");
		if (cacheOption.equalsIgnoreCase("true")) {
			CacheInJson.createFileInCacheFolder();
			CacheInJson.overrideAllArgsMap();
		}
	}

	private void loadDriver() {

		this.handler = new HandleAlertsPopUpsDailogue();
		this.elementAction = new ElementAction();

		try {
			driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL),
					RemoteBrowserOptions.returnRemoteBrowserOptions(browser, loginUsername, loginPassword));
		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void loadAllKeys() {
		properties.forEach((key, value) -> allArgsMap.put((String) key, (String) value));
	}

	public void loadURL() {
		try {
			url = properties.getProperty("browser.url");
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public String loadBrowser() {
		try (FileInputStream input = new FileInputStream(CONFIGPATHSTRING)) {
			properties.load(input);
			browser = properties.getProperty("browser.type");
			loginUsername = properties.getProperty("lambda.username");
			loginPassword = properties.getProperty("lambda.password");
			return browser;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void loadCSV() {
		coreDataList = new ArrayList<>();

		try (FileInputStream input = new FileInputStream(CONFIGPATHSTRING)) {
			properties.load(input);
			csvPath = runnerPath + properties.getProperty("relative.path.data") + properties.getProperty("csv_name");
		} catch (NullPointerException e) {
			System.out.println("No csv provided.");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvPath))) {
			String lines;
			int columns = -1;
			lines = reader.readLine();
			List<String> tempList = new ArrayList<>();
			if (lines != null) {
				tempList = SerializeToList.convertToList(lines);
				columns = tempList.size();
			}

			for (int i = 0; i < columns; i++) {
				coreDataList.add(new ArrayList<>());
			}

			while ((lines = reader.readLine()) != null) {
				tempList = SerializeToList.convertToList(lines);
				int j = 0;
				for (String str : tempList) {
					if (!str.equals("") && !str.equals(" "))
						coreDataList.get(j).add(str);
					j++;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@AfterSuite(alwaysRun = true)
	@AfterClass(alwaysRun = true)
	public void destroy() {
		if (cacheTodoMap != null) {
			CacheInJson.writeIntoCache();
		}

		if (allArgsMap.containsKey("driver.close") && allArgsMap.get("driver.close").equalsIgnoreCase("true")) {
			driver.close();
		}
	}
}

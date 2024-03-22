package effigo.automate.common.utils;

import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class RemoteBrowserOptions {
	public static ChromeOptions returnChromeOptions(String username, String password) {
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("122.0");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", username);
		ltOptions.put("accessKey", password);
		ltOptions.put("geoLocation", "IN");
		ltOptions.put("visual", true);
		ltOptions.put("network", true);
		ltOptions.put("timezone", "Kolkata");
		ltOptions.put("build", "ChromeBuild");
		ltOptions.put("project", "AutomateTest");
		ltOptions.put("smartUI.project", "BasicUI");
		ltOptions.put("name", "ChromeTest");
		ltOptions.put("console", "warn");
		ltOptions.put("networkThrottling", "Regular 4G");
		ltOptions.put("w3c", true);
		ltOptions.put("plugin", "java-testNG");
		ltOptions.put("terminal", true);
		browserOptions.setCapability("LT:Options", ltOptions);

		return browserOptions;
	}

	public static EdgeOptions returnEdgeOptions(String username, String password) {
		EdgeOptions browserOptions = new EdgeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("122.0");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", username);
		ltOptions.put("accessKey", password);
		ltOptions.put("geoLocation", "IN");
		ltOptions.put("visual", true);
		ltOptions.put("network", true);
		ltOptions.put("timezone", "Kolkata");
		ltOptions.put("build", "test");
		ltOptions.put("project", "AutomateTest");
		ltOptions.put("smartUI.project", "BasicUI");
		ltOptions.put("name", "BasicTest");
		ltOptions.put("console", "warn");
		ltOptions.put("networkThrottling", "Regular 4G");
		ltOptions.put("w3c", true);
		ltOptions.put("plugin", "java-testNG");
		ltOptions.put("terminal", true);
		browserOptions.setCapability("LT:Options", ltOptions);

		return browserOptions;
	}

	public static AbstractDriverOptions<?> returnFirefoxOptions(String username, String password) {
		FirefoxOptions browserOptions = new FirefoxOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("122.0");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", username);
		ltOptions.put("accessKey", password);
		ltOptions.put("geoLocation", "IN");
		ltOptions.put("visual", true);
		ltOptions.put("network", true);
		ltOptions.put("timezone", "Kolkata");
		ltOptions.put("build", "test");
		ltOptions.put("project", "AutomateTest");
		ltOptions.put("smartUI.project", "BasicUI");
		ltOptions.put("name", "BasicTest");
		ltOptions.put("console", "warn");
		ltOptions.put("networkThrottling", "Regular 4G");
		ltOptions.put("w3c", true);
		ltOptions.put("plugin", "java-testNG");
		ltOptions.put("terminal", true);
		browserOptions.setCapability("LT:Options", ltOptions);

		return browserOptions;
	}

	public static AbstractDriverOptions<?> returnRemoteBrowserOptions(String browser, String username,
			String password) {
		browser = browser.toLowerCase();
		AbstractDriverOptions<?> driverOptions;
		switch (browser) {
		case "firefox":
			driverOptions = returnFirefoxOptions(username, password);
			break;

		case "chrome":
			driverOptions = returnChromeOptions(username, password);
			break;

		case "edge":
			driverOptions = returnEdgeOptions(username, password);
			break;
		default:
			driverOptions = null;
		}

		return driverOptions;
	}
}

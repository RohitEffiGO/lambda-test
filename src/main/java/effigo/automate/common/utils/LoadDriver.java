package effigo.automate.common.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoadDriver {

	public WebDriver loadWebDriver(String browser) {
		browser = browser.toLowerCase();
		WebDriver driver = null;
		switch (browser) {

		case "firefox":
			driver = loadGekoDriver();
			break;

		case "chrome":
			driver = loadChromeDriver();
			break;

		case "edge":
			driver = loadEdgeDriver();
			break;

		case "safari":
			driver = loadSafariDriver();
			break;

		default:
			break;
		}

		return driver;
	}

	public static WebDriver loadChromeDriver() {
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver();
	}

	public static WebDriver loadGekoDriver() {
		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver();
	}

	public static WebDriver loadEdgeDriver() {
		WebDriverManager.edgedriver().setup();
		return new EdgeDriver();
	}

	public static WebDriver loadSafariDriver() {
		WebDriverManager.safaridriver().setup();
		return new SafariDriver();
	}
}

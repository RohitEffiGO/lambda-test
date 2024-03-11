package com.scenario.automate;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.scenario.automate.utils.ElementAction;
import com.scenario.automate.utils.HandleAlertsPopUpsDailogue;

@SpringBootTest
public class HandleAlertTests {
	public static RemoteWebDriver driver = null;

	public String username = "rohit.thakureffigo";
	public String accesskey = "YmXkfqbnNZQOpoO39JUdY3rECfRIVBNTHZSLaO1VQIW5eSKP6K";
	public String gridURL = "@hub.lambdatest.com/wd/hub";

	ChromeOptions browserOptions;
	HandleAlertsPopUpsDailogue handler;
	ElementAction elementAction;

	@BeforeTest
	public void loadEverything() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\Login\\work\\demo\\src\\main\\resources\\chromedriver-win64\\chromedriver.exe");

		this.browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("122.0");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "rohit.thakureffigo");
		ltOptions.put("accessKey", "YmXkfqbnNZQOpoO39JUdY3rECfRIVBNTHZSLaO1VQIW5eSKP6K");
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

		this.handler = new HandleAlertsPopUpsDailogue();
		this.elementAction = new ElementAction();

		try {
			driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), browserOptions);
		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void handleAlertTest() {
		driver.get("https://demo.automationtesting.in/Alerts.html");
		driver.manage().window().maximize();
		WebElement element = elementAction.getTheElement(driver, By.cssSelector(".btn-danger"));
		elementAction.clickOnElement(element);

		assertEquals(true, handler.handleAlertsWithOk(driver));
	}

	@Test
	public void cancelAlertTest() {
		driver.get("https://demo.automationtesting.in/Alerts.html");
		driver.manage().window().maximize();
		WebElement element = elementAction.getTheElement(driver,
				By.xpath("/html/body/div[1]/div/div/div/div[1]/ul/li[2]/a"));
		elementAction.clickOnElement(element);
		element = elementAction.getTheElement(driver, By.cssSelector(".btn-primary"));
		elementAction.clickOnElement(element);

		assertEquals(true, handler.handleAlertsWithCancel(driver));
	}

	@Test
	public void sendAlertMessageTest() {
		driver.get("https://demo.automationtesting.in/Alerts.html");
		driver.manage().window().maximize();

		WebElement element = elementAction.getTheElement(driver,
				By.xpath("/html/body/div[1]/div/div/div/div[1]/ul/li[3]/a"));
		elementAction.clickOnElement(element);
		element = elementAction.getTheElement(driver, By.xpath("/html/body/div[1]/div/div/div/div[2]/div[3]/button"));
		elementAction.clickOnElement(element);

		assertEquals(true, handler.handleAlertWithTextBox(driver, "This is a test data"));
	}

	@Test
	public void testClosePopUp() {
		driver.manage().window().maximize();
		driver.get("https://www.webroot.com/services/popuptester1.htm");

		assertEquals(true, handler.closePopUp(driver));
	}

	@Test
	public void testModalOpenAndClose() {
		driver.manage().window().maximize();
		driver.get("https://www.lambdatest.com/selenium-playground/bootstrap-modal-demo");

		WebElement triggerElement = elementAction.getTheElement(driver,
				By.xpath("/html/body/div[1]/section[2]/div/div/div/div[1]/button"));

		assertEquals(true, elementAction.clickOnElement(triggerElement));

		WebElement interactElement = elementAction.getTheElement(driver,
				By.xpath("/html/body/div[1]/section[2]/div/div/div/div[1]/div[2]/div/div/div[3]/button[2]"));

		handler.waitForElement(driver, interactElement);
		assertEquals(true, handler.dialogBoxHandler(interactElement));

		elementAction.clickOnElement(triggerElement);

		WebElement closeElement = elementAction.getTheElement(driver,
				By.xpath("/html/body/div[1]/section[2]/div/div/div/div[1]/div[2]/div/div/div[1]/button"));

		handler.waitForElement(driver, closeElement);
		assertEquals(true, handler.dialogBoxHandler(closeElement));
	}

	@AfterTest
	public void closeAll() {
		driver.close();
	}
}

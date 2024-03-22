package effigo.automate.PageObjectModel;

import org.openqa.selenium.WebDriver;

import effigo.automate.common.utils.LoadDriver;

public class LoadBrowserBasic {
	LoadDriver loadDriver;

	public LoadBrowserBasic() {
		this.loadDriver = new LoadDriver();
	}

	public boolean loadBrowserModel(String browser) {

		WebDriver driver = loadDriver.loadWebDriver(browser);
		driver.close();
		return (driver != null) ? true : false;
	}

}

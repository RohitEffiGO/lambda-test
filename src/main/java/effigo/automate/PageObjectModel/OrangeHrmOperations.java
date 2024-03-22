package effigo.automate.PageObjectModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import effigo.automate.common.utils.CacheInJson;
import effigo.automate.common.utils.CheckForFields;
import effigo.automate.common.utils.CollectAndClick;
import effigo.automate.common.utils.ElementAction;
import effigo.automate.common.utils.HandleAlertsPopUpsDailogue;
import effigo.automate.common.utils.Loader;
import effigo.automate.common.utils.OtherActions;

public class OrangeHrmOperations extends Loader {
	ElementAction elementAction;
	CheckForFields checkForFields;
	CollectAndClick collectAndClick;
	String randomNumber;
	OtherActions otherActions;
	String employeeName;
	HandleAlertsPopUpsDailogue handler;
	String usernameString;

	public OrangeHrmOperations() {
		elementAction = new ElementAction();
		checkForFields = new CheckForFields();
		collectAndClick = new CollectAndClick();
		randomNumber = new Random().ints(5, 0, 5).mapToObj(Integer::toString).collect(Collectors.joining());
		otherActions = new OtherActions();
		handler = new HandleAlertsPopUpsDailogue();
	}

	public boolean performLogin() {
		driver.get(url);

		try {
			CacheInJson.overrideAllArgsMap("login");
		} catch (Exception e) {
			System.out.println("Could not override");
		}

		String username = allArgsMap.get("username");
		String password = allArgsMap.get("password");
		String usernamePath = allArgsMap.get("login.username.xpath");
		String passwordPath = allArgsMap.get("login.password.xpath");
		String loginButtonPath = allArgsMap.get("login.click.xpath");

		Map<String, String> cacheMap = new LinkedHashMap<>();
		cacheMap.put("username", username);
		cacheMap.put("password", password);
		cacheMap.put("login.username.css", usernamePath);
		cacheMap.put("login.password.xpath", passwordPath);
		cacheMap.put("login.click.xpath", loginButtonPath);
		CacheInJson.setCache("login", cacheMap);

		List<String> loginPath = new ArrayList<>();
		loginPath.add(usernamePath);
		loginPath.add(passwordPath);

		List<String> loginValues = new ArrayList<>();
		loginValues.add(username);
		loginValues.add(password);

		if (collectAndClick.enterValuesForEach(loginPath, loginValues)) {
			List<WebElement> button = checkForFields.findAllFields(driver, Arrays.asList(loginButtonPath));
			return elementAction.clickOnElement(button.get(0));
		}
		return false;
	}

	public boolean navigateToEmployee() {

		Map<String, String> cacheMap = new LinkedHashMap<>();
		try {
			CacheInJson.overrideAllArgsMap("navigate");
		} catch (Exception e) {
			System.out.println("Could not override navigate");
		}

		String adminRow = "admin.table.row";
		String adminPagePath = allArgsMap.get("side.panel.admin.path");
		cacheMap.put("side.panel.admin.path", adminPagePath);

		By byAdminPath = checkForFields.categorizeElementAndRetrieve(driver, adminPagePath);
		WebElement element = elementAction.getTheElement(driver, byAdminPath);

		if (elementAction.clickOnElement(element)) {
			By tableBy = checkForFields.categorizeElementAndRetrieve(driver, allArgsMap.get(adminRow));
			cacheMap.put(adminRow, allArgsMap.get(adminRow));
			WebElement tableElement = elementAction.getTheElement(driver, tableBy);
			this.employeeName = tableElement.getText().split("\n")[2];
			CacheInJson.setCache("navigate", cacheMap);
			return true;
		}
		return false;
	}

	public boolean addAndEnter() throws InterruptedException {

		Map<String, String> cacheMap = new LinkedHashMap<>();

		try {
			CacheInJson.overrideAllArgsMap("addUser");
		} catch (Exception e) {
			System.out.println("Could not override addAndEnter");
		}

		String addPath = allArgsMap.get("button.add.path");
		cacheMap.put("button.add.path", addPath);

		By addBtn = checkForFields.categorizeElementAndRetrieve(driver, addPath);
		WebElement element = elementAction.getTheElement(driver, addBtn);

		if (elementAction.clickOnElement(element)) {
//			Loading all paths

			String rolePath = allArgsMap.get("add.role.path");
			String employeeNamePath = allArgsMap.get("add.employee.path");
			String statusPath = allArgsMap.get("add.status.path");
			String usernamePath = allArgsMap.get("add.username.path");
			String passwordPath = allArgsMap.get("add.password.path");
			String confirmPath = allArgsMap.get("add.confirm.password.path");
			String saveBtnPath = allArgsMap.get("add.save.path");

			cacheMap.put("add.role.path", rolePath);
			cacheMap.put("add.employee.path", employeeNamePath);
			cacheMap.put("add.status.path", statusPath);
			cacheMap.put("add.save.path", saveBtnPath);

//			Loading all values
			usernameString = allArgsMap.get("add.username.value") + randomNumber;

			String password = allArgsMap.get("add.password.value");

//			employee name
			By empBy = checkForFields.categorizeElementAndRetrieve(driver, employeeNamePath);
			WebElement empElement = elementAction.getTheElement(driver, empBy);

			otherActions.writeInTextBox(empElement, employeeName);
			Thread.sleep(2000);
			empElement.sendKeys(Keys.DOWN);
			empElement.sendKeys(Keys.ENTER);
			Thread.sleep(1000);

//			username
			By userBy = checkForFields.categorizeElementAndRetrieve(driver, usernamePath);
			WebElement userElement = elementAction.getTheElement(driver, userBy);
			otherActions.writeInTextBox(userElement, usernameString);

//			password
			By passBy = checkForFields.categorizeElementAndRetrieve(driver, passwordPath);
			WebElement passwordElement = elementAction.getTheElement(driver, passBy);
			otherActions.writeInTextBox(passwordElement, password);

//			confirm password
			By configPassBy = checkForFields.categorizeElementAndRetrieve(driver, confirmPath);
			WebElement confirmPasswordElement = elementAction.getTheElement(driver, configPassBy);
			otherActions.writeInTextBox(confirmPasswordElement, password);

//			user role
			By roleBy = checkForFields.categorizeElementAndRetrieve(driver, rolePath);
			WebElement roleElement = elementAction.getTheElement(driver, roleBy);
			Thread.sleep(1000);
			roleElement.sendKeys(Keys.DOWN);
			roleElement.sendKeys(Keys.ENTER);

//			status
			By statusBy = checkForFields.categorizeElementAndRetrieve(driver, statusPath);
			WebElement statusElement = elementAction.getTheElement(driver, statusBy);
			Thread.sleep(1000);
			statusElement.sendKeys(Keys.DOWN);
			statusElement.sendKeys(Keys.ENTER);

//			save
			By saveBy = checkForFields.categorizeElementAndRetrieve(driver, saveBtnPath);
			WebElement saveElement = elementAction.getTheElement(driver, saveBy);

			CacheInJson.setCache("addUser", cacheMap);
			return elementAction.clickOnElement(saveElement);
		}

		return false;
	}

	public boolean deleteUser() throws InterruptedException {

		String deletePath = allArgsMap.get("delete.user.path");
		String deleteOkPath = allArgsMap.get("delete.alert.path");
		deletePath = deletePath.replace("dynamic", "'" + usernameString + "'");

		Thread.sleep(10000);

		By deleteBtn = checkForFields.categorizeElementAndRetrieve(driver, deletePath);
		WebElement deleteElement = elementAction.getTheElement(driver, deleteBtn);

		elementAction.clickOnElement(deleteElement);

		By deleteOkBy = checkForFields.categorizeElementAndRetrieve(driver, deleteOkPath);
		WebElement acceptElement = elementAction.getTheElement(driver, deleteOkBy);

		Thread.sleep(1000);
		return elementAction.clickOnElement(acceptElement);
	}

	public boolean editUser() throws InterruptedException {

		Thread.sleep(2000);

		String editBtn = allArgsMap.get("edit.click.button");
		editBtn = editBtn.replace("dynamic", "'" + usernameString + "'");

		By editBy = checkForFields.categorizeElementAndRetrieve(driver, editBtn);
		WebElement editBtnElement = elementAction.getTheElement(driver, editBy);
		elementAction.clickOnElement(editBtnElement);

		String usernamePath = allArgsMap.get("add.username.path");
		String rolePath = allArgsMap.get("add.role.path");
		String employeeNamePath = allArgsMap.get("add.employee.path");
		String statusPath = allArgsMap.get("add.status.path");
		String saveBtnPath = allArgsMap.get("edit.click.save");

//		username
		Thread.sleep(2000);
		By userBy = checkForFields.categorizeElementAndRetrieve(driver, usernamePath);
		WebElement userElement = elementAction.getTheElement(driver, userBy);
		usernameString = "hello" + randomNumber;
		userElement.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		userElement.sendKeys(usernameString);

//		employee name
		By empBy = checkForFields.categorizeElementAndRetrieve(driver, employeeNamePath);
		WebElement empElement = elementAction.getTheElement(driver, empBy);
		empElement.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
		empElement.sendKeys(employeeName.substring(0, 1));

		Thread.sleep(2000);
		empElement.sendKeys(Keys.DOWN);
		empElement.sendKeys(Keys.ENTER);
		Thread.sleep(1000);

//		user role
		By roleBy = checkForFields.categorizeElementAndRetrieve(driver, rolePath);
		WebElement roleElement = elementAction.getTheElement(driver, roleBy);
		Thread.sleep(1000);
		roleElement.sendKeys(Keys.DOWN);
		roleElement.sendKeys(Keys.ENTER);

//		status
		By statusBy = checkForFields.categorizeElementAndRetrieve(driver, statusPath);
		WebElement statusElement = elementAction.getTheElement(driver, statusBy);
		Thread.sleep(1000);
		statusElement.sendKeys(Keys.DOWN);
		statusElement.sendKeys(Keys.ENTER);

//		save
		By saveBy = checkForFields.categorizeElementAndRetrieve(driver, saveBtnPath);
		WebElement saveElement = elementAction.getTheElement(driver, saveBy);

		return elementAction.clickOnElement(saveElement);
	}
}

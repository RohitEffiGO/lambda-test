package effigo.automate.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheckForFields {
	List<WebElement> allFieldElements = new ArrayList<>();
	ElementAction elementAction = new ElementAction();

	public By categorizeElementAndRetrieve(WebDriver driver, String find) {
		By findBy;
		try {
			findBy = By.cssSelector(find);
			if (elementAction.checkForElement(driver, findBy)) {
				log.info("A css selector.");
				return findBy;
			}
		} catch (Exception error) {
			log.info("Not a css element");
		}
		try {
			findBy = By.xpath(find);
			if (elementAction.checkForElement(driver, findBy)) {
				log.info("A xpath");
				return findBy;
			}
		} catch (Exception error) {
			log.info("Not a xpath element");
		}
		try {
			findBy = By.id(find);
			if (elementAction.checkForElement(driver, findBy)) {
				log.info("An id");
				return findBy;
			}
		} catch (Exception error) {
			log.info("Not an element");
		}
		return null;
	}

	public List<WebElement> findAllFields(WebDriver driver, List<String> findBySomething) {
		By findBy;
		for (String find : findBySomething) {
			findBy = categorizeElementAndRetrieve(driver, find);
			allFieldElements.add(elementAction.getTheElement(driver, findBy));
		}

		return allFieldElements;
	}
}

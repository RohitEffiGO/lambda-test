package effigo.automate.common.utils;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CollectAndClick extends Loader {

	ElementAction elementAction;
	CheckForFields checkForFields;
	OtherActions otherActions;

	public CollectAndClick() {
		this.elementAction = new ElementAction();
		this.checkForFields = new CheckForFields();
		this.otherActions = new OtherActions();
	}

	public boolean enterValuesForEach(List<String> allElementLocators, List<String> values) {
		List<WebElement> allFilters = checkForFields.findAllFields(driver, allElementLocators);

		Map<WebElement, String> mapWebWithVal = IntStream.range(0, Math.min(allFilters.size(), values.size())).boxed()
				.collect(Collectors.toMap(allFilters::get, values::get));

		mapWebWithVal.forEach((key, val) -> {
			try {
				otherActions.writeInTextBox(key, val);
			} catch (StaleElementReferenceException e) {
				new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(key));
				otherActions.writeInTextBox(key, val);
			}
		});

		return true;
	}
}

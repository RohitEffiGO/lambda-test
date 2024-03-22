package effigo.automate.PageObjectModel;

import java.util.List;

import org.openqa.selenium.WebElement;

import effigo.automate.common.utils.CheckForFields;
import effigo.automate.common.utils.CollectAndClick;
import effigo.automate.common.utils.ElementAction;
import effigo.automate.common.utils.Loader;

public class ValidateTable extends Loader {

	CollectAndClick collectAndClick;
	ElementAction elementAction;
	CheckForFields checkForFields;

	public ValidateTable() {
		collectAndClick = new CollectAndClick();
		elementAction = new ElementAction();
		checkForFields = new CheckForFields();
	}

	public boolean validateThroughLength() {
		driver.get(url);

		int value = Integer.parseInt(coreDataList.get(3).get(0));

		if (collectAndClick.enterValuesForEach(coreDataList.get(0), coreDataList.get(1))) {
			WebElement webElement = checkForFields.findAllFields(driver, coreDataList.get(2)).get(0);
			int calculatedLength = webElement.getText().split("\n").length;
			return calculatedLength == value;
		}

		return false;
	}

	public boolean validateThroughValue() {
		driver.get(url);

		if (collectAndClick.enterValuesForEach(coreDataList.get(0), coreDataList.get(1))) {
			WebElement webElement = checkForFields.findAllFields(driver, coreDataList.get(2)).get(0);
			String[] collectedData = webElement.getText().split("\n");
			List<String> validList = coreDataList.get(3);
			for (String data : collectedData) {
				if (!validList.contains(data)) {
					return false;
				}
			}
		}
		return true;
	}
}

package effigo.automate.PageObjectModel;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;

import effigo.automate.common.utils.CheckForFields;
import effigo.automate.common.utils.CollectAndClick;
import effigo.automate.common.utils.ElementAction;
import effigo.automate.common.utils.Loader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataValidation extends Loader {
	CheckForFields checkForFields;
	ElementAction elementAction;
	CollectAndClick collectAndClick;

	public DataValidation() {
		this.checkForFields = new CheckForFields();
		this.elementAction = new ElementAction();
		this.collectAndClick = new CollectAndClick();
	}

	public boolean validate() {

		driver.get(url);
		if (!collectAndClick.enterValuesForEach(coreDataList.get(0), coreDataList.get(1)))
			return false;

		for (String row : coreDataList.get(2)) {
			WebElement rowWebElement = elementAction.getTheElement(driver,
					checkForFields.categorizeElementAndRetrieve(driver, row));
			List<String> result = Arrays.stream(rowWebElement.getText().split(" ")).map(String::trim).toList();

			log.debug(result.toString());
			log.debug(coreDataList.get(3).toString());
			if (result.containsAll(coreDataList.get(3))) {
				return true;
			}
		}
		return false;
	}

}

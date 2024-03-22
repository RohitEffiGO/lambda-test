package effigo.automate.PageObjectModel;

import java.util.List;

import org.openqa.selenium.WebElement;

import effigo.automate.common.utils.CheckForFields;
import effigo.automate.common.utils.Loader;

public class GetAllElements extends Loader {

	CheckForFields checkForFields;

	public GetAllElements() {
		this.checkForFields = new CheckForFields();
	}

	public Integer findAllColumns() {

		driver.get(url);
		List<WebElement> allElements = checkForFields.findAllFields(driver, coreDataList.get(0));

		return allElements.size();
	}

}

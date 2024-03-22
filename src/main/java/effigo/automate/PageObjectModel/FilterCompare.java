package effigo.automate.PageObjectModel;

import effigo.automate.common.utils.CollectAndClick;
import effigo.automate.common.utils.Loader;

public class FilterCompare extends Loader {

	CollectAndClick collectAndClick = new CollectAndClick();

	public boolean validateAllFilterAndValues() {
		driver.get(url);
		collectAndClick.enterValuesForEach(coreDataList.get(0), coreDataList.get(1));
		return true;
	}
}

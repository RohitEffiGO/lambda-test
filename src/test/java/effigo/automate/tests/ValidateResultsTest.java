package effigo.automate.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import effigo.automate.PageObjectModel.DataValidation;

public class ValidateResultsTest extends DataValidation {

	@Test
	public void validateFilterTest() {
		assertEquals(validate(), true);
	}
}

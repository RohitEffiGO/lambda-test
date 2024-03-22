package effigo.automate.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import effigo.automate.PageObjectModel.GetAllElements;

public class BasicTests extends GetAllElements {

	@Test
	public void testAllFields() {
		Integer assertColumnsInteger = 6;
		assertEquals(true, assertColumnsInteger.equals(findAllColumns()));
	}
}

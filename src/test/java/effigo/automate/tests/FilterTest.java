package effigo.automate.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import effigo.automate.PageObjectModel.FilterCompare;

public class FilterTest extends FilterCompare {
	@Test
	public void testFilter() {
		assertEquals(validateAllFilterAndValues(), true);
	}
}

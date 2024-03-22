package effigo.automate.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import effigo.automate.PageObjectModel.ValidateTable;

public class ValidateTableTest extends ValidateTable {
	@Test
	public void testTableLength() {
		assertEquals(validateThroughLength(), true);
	}

	@Test
	public void testTableThroughValues() {
		assertEquals(validateThroughValue(), true);
	}
}

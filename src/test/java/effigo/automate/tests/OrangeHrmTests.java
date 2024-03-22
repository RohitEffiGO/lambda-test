package effigo.automate.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import effigo.automate.PageObjectModel.OrangeHrmOperations;

public class OrangeHrmTests extends OrangeHrmOperations {
	@Test
	public void test() throws InterruptedException {
		assertEquals(performLogin(), true);
		assertEquals(navigateToEmployee(), true);
		assertEquals(addAndEnter(), true);
		assertEquals(editUser(), true);
		assertEquals(deleteUser(), true);
	}

}

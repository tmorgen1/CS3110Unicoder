package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import edu.westga.cs3110.unicoder.model.Codepoint;

class TestConstructor {

	@Test
	void testNullCodepoint() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Codepoint(null);
		});
	}
	
	@Test
	void testEmptyCodepoint() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Codepoint("");
		});
	}

	@Test
	void testCodepointContainsNonHexDigits() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Codepoint("x");
		});
	}
	
	@Test
	void testCodepointTooLargeHexValue() {
		assertThrows(IllegalArgumentException.class, () -> {
			// 10FFFF is the max codepoint value
			new Codepoint("110000");
		});
	}
	
	@Test
	void testNormalCodepoint() {
		Codepoint test = new Codepoint("1A043");
		this.assertEqualsIgnoreCase("1a043", Integer.toHexString(test.getCodepoint()));
	}
	
	void assertEqualsIgnoreCase(String expected, String actual) {
		String expectedLower = expected.toLowerCase();
		String actualLower = actual.toLowerCase();
		assertEquals(expectedLower, actualLower);
	}
}

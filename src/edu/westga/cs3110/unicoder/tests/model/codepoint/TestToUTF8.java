package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import edu.westga.cs3110.unicoder.model.Codepoint;

class TestToUTF8 {

	@Test
	void testSingleByteUTF8EncodingMin() {
		Codepoint test = new Codepoint("0002");
		this.assertEqualsIgnoreCase("02", test.toUTF8());
	}
	
	@Test
	void testSingleByteUTF8EncodingMid() {
		Codepoint test = new Codepoint("0056");
		this.assertEqualsIgnoreCase("56", test.toUTF8());
	}
	
	@Test
	void testSingleByteUTF8EncodingMax() {
		Codepoint test = new Codepoint("007F");
		this.assertEqualsIgnoreCase("7F", test.toUTF8());
	}
	
	@Test
	void testTwoByteUTF8Encoding() {
		Codepoint test = new Codepoint("01A0");
		this.assertEqualsIgnoreCase("C6A0", test.toUTF8());
	}
	
	@Test
	void testThreeByteUTF8Encoding() {
		Codepoint test = new Codepoint("4CE3");
		this.assertEqualsIgnoreCase("E4B3A3", test.toUTF8());
	}
	
	@Test
	void testFourByteUTF8Encoding() {
		Codepoint test = new Codepoint("10000");
		this.assertEqualsIgnoreCase("f0908080", test.toUTF8());
	}
	
	void assertEqualsIgnoreCase(String expected, String actual) {
		String expectedLower = expected.toLowerCase();
		String actualLower = actual.toLowerCase();
		assertEquals(expectedLower, actualLower);
	}

}

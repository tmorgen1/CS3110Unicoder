package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import edu.westga.cs3110.unicoder.model.Codepoint;

class TestToUTF16 {

	@Test
	void testTwoByteUTF16EncodingLowerRangeMin() {
		Codepoint test = new Codepoint("0000");
		this.assertEqualsIgnoreCase("0000", test.toUTF16());
	}
	
	@Test
	void testTwoByteUTF16EncodingLowerRangeMid() {
		Codepoint test = new Codepoint("ABCD");
		this.assertEqualsIgnoreCase("ABCD", test.toUTF16());
	}
	
	@Test
	void testTwoByteUTF16EncodingLowerRangeMax() {
		Codepoint test = new Codepoint("D7FF");
		this.assertEqualsIgnoreCase("d7ff", test.toUTF16());
	}
	
	@Test
	void testTwoByteUTF16EncodingUpperRangeMin() {
		Codepoint test = new Codepoint("E000");
		this.assertEqualsIgnoreCase("E000", test.toUTF16());
	}
	
	@Test
	void testTwoByteUTF16EncodingUpperRangeMid() {
		Codepoint test = new Codepoint("FFA2");
		this.assertEqualsIgnoreCase("FFA2", test.toUTF16());
	}
	
	@Test
	void testTwoByteUTF16EncodingUpperRangeMax() {
		Codepoint test = new Codepoint("FFFF");
		this.assertEqualsIgnoreCase("FFFF", test.toUTF16());
	}
	
	@Test
	void testTwoByteUTF16EncodingDoesNotWork() {
		Codepoint test = new Codepoint("D800");
		this.assertEqualsIgnoreCase("", test.toUTF16());
	}
	
	@Test
	void testFourByteUTF16EncodingMin() {
		Codepoint test = new Codepoint("10000");
		this.assertEqualsIgnoreCase("d800dc00", test.toUTF16());
	}
	
	@Test
	void testFourByteUTF16EncodingMid() {
		Codepoint test = new Codepoint("0183A5");
		this.assertEqualsIgnoreCase("d820dfa5", test.toUTF16());
	}
	
	@Test
	void testFourByteUTF16EncodingMax() {
		Codepoint test = new Codepoint("10FFFF");
		this.assertEqualsIgnoreCase("dbffdfff", test.toUTF16());
	}
	
	void assertEqualsIgnoreCase(String expected, String actual) {
		String expectedLower = expected.toLowerCase();
		String actualLower = actual.toLowerCase();
		assertEquals(expectedLower, actualLower);
	}
}

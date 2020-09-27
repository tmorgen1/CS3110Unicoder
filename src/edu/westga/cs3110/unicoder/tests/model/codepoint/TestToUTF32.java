package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3110.unicoder.model.Codepoint;

class TestToUTF32 {

	@Test
	void testFourByteUTF32EncodingMin() {
		Codepoint test = new Codepoint("0000");
		this.assertEqualsIgnoreCase("00000000", test.toUTF32());
	}
	
	@Test
	void testFourByteUTF32EncodingMid1() {
		Codepoint test = new Codepoint("183a5");
		this.assertEqualsIgnoreCase("000183a5", test.toUTF32());
	}
	
	@Test
	void testFourByteUTF32EncodingMid2() {
		Codepoint test = new Codepoint("0015");
		this.assertEqualsIgnoreCase("00000015", test.toUTF32());
	}
	
	@Test
	void testFourByteUTF32EncodingMax() {
		Codepoint test = new Codepoint("10FFFF");
		this.assertEqualsIgnoreCase("0010FFFF", test.toUTF32());
	}

	void assertEqualsIgnoreCase(String expected, String actual) {
		String expectedLower = expected.toLowerCase();
		String actualLower = actual.toLowerCase();
		assertEquals(expectedLower, actualLower);
	}
}

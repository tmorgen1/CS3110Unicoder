package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import edu.westga.cs3110.unicoder.model.Codepoint;

class TestToUTF16 {

	@Test
	void testTwoByteUTF16EncodingLowerRange() {
		Codepoint test = new Codepoint("ABCD");
		this.assertEqualsIgnoreCase("ABCD", test.toUTF16());
	}
	
	@Test
	void testTwoByteUTF16EncodingUpperRange() {
		Codepoint test = new Codepoint("FFA2");
		this.assertEqualsIgnoreCase("FFA2", test.toUTF16());
	}
	
	@Test
	void testFourByteUTF16Encoding() {
		Codepoint test = new Codepoint("0183A5");
		this.assertEqualsIgnoreCase("d820dfa5", test.toUTF16());
	}
	
	void assertEqualsIgnoreCase(String expected, String actual) {
		String expectedLower = expected.toLowerCase();
		String actualLower = actual.toLowerCase();
		assertEquals(expectedLower, actualLower);
	}
}

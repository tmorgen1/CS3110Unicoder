package edu.westga.cs3110.unicoder.model;

/**
 * The Class Codepoint.
 * 
 * @author Thomas Morgenstern
 * @version Fall 2020
 */
public class Codepoint {
	
	private static final String TEN = "10";
	public static final int CODEPOINT_MIN = 0;
	public static final int CODEPOINT_MAX = 0x10FFFF;
	
	public static final int SINGLE_BYTE_UTF8_THRESHOLD = 0x7F;
	public static final int TWO_BYTE_UTF8_THRESHOLD = 0x7FF;
	public static final int THREE_BYTE_UTF8_THRESHOLD = 0xFFFF;
	
	public static final int HEX_RADIX = 16;
	public static final int BINARY_RADIX = 2;
	
	private int codepointHex;
	
	/**
	 * Instantiates a new codepoint.
	 * 
	 * @precondition codepointHex != null AND !codepointHex.isEmpty() AND codepointHex does not contain non hex digits AND
	 * 					converted int value is not larger than CODEPOINT_MAX
	 * @postcondition getCodepoint() == codepointHex as int
	 *
	 * @param codepointHex the codepoint hex
	 */
	public Codepoint(String codepointHex) {
		if (codepointHex == null) {
			throw new IllegalArgumentException("codepointHex cannot be null");
		}
		if (codepointHex.isEmpty()) {
			throw new IllegalArgumentException("codepointHex cannot be empty");
		}
		if (!codepointHex.matches("[1234567890ABCDEFabcdef]*")) {
			throw new IllegalArgumentException("codepointHex must contain only hex digits");
		}
		
		this.codepointHex = Integer.parseUnsignedInt(codepointHex, HEX_RADIX);
		
		if (this.codepointHex > CODEPOINT_MAX) {
			throw new IllegalArgumentException("codepointHex must be in the unicode codepoint range.");
		}
	}
	
	public String toUTF8() {
		String encodedBytes = "";
		
		if (this.codepointHex <= SINGLE_BYTE_UTF8_THRESHOLD) {
			encodedBytes = this.encodeUTF81Byte();
		} else if (this.codepointHex <= TWO_BYTE_UTF8_THRESHOLD) {
			encodedBytes = this.encodeUTF82Byte();
		} else if (this.codepointHex <= THREE_BYTE_UTF8_THRESHOLD) {
			encodedBytes = this.encodeUTF83Byte();
		} else {
			encodedBytes = this.encodeUTF84Byte();
		}
		
		return encodedBytes;
	}

	private String encodeUTF81Byte() {
		String encodedBytes;
		encodedBytes = Integer.toHexString(this.codepointHex);
		if (encodedBytes.length() < 2) {
			encodedBytes = String.format("%02x", Integer.parseInt(encodedBytes, HEX_RADIX));
		}
		return encodedBytes;
	}

	private String encodeUTF82Byte() {
		String encodedBytes;
		String secondByte = Integer.toBinaryString(this.codepointHex);
		secondByte = String.format("%16s", secondByte).replace(' ', '0');
		secondByte = secondByte.substring(secondByte.length() - 6);
		secondByte = TEN + secondByte;
		
		String firstByte = Integer.toBinaryString(this.codepointHex);
		firstByte = String.format("%16s", firstByte).replace(' ', '0');
		firstByte = firstByte.substring(firstByte.length() - 11, firstByte.length() - 6);
		firstByte = "110" + firstByte;
		
		int buffer = Integer.parseInt(firstByte + secondByte, BINARY_RADIX);
		encodedBytes = Integer.toString(buffer, HEX_RADIX);
		return encodedBytes;
	}

	private String encodeUTF83Byte() {
		String encodedBytes;
		String thirdByte = Integer.toBinaryString(this.codepointHex);
		thirdByte = String.format("%21s", thirdByte).replace(' ', '0');
		thirdByte = thirdByte.substring(thirdByte.length() - 6);
		thirdByte = TEN + thirdByte;
		
		String secondByte = Integer.toBinaryString(this.codepointHex);
		secondByte = String.format("%21s", secondByte).replace(' ', '0');
		secondByte = secondByte.substring(secondByte.length() - 12, secondByte.length() - 6);
		secondByte = TEN + secondByte;
		
		String firstByte = Integer.toBinaryString(this.codepointHex);
		firstByte = String.format("%21s", firstByte).replace(' ', '0');
		firstByte = firstByte.substring(firstByte.length() - 16, firstByte.length() - 12);
		firstByte = "1110" + firstByte;
		
		int buffer = Integer.parseInt(firstByte + secondByte + thirdByte, BINARY_RADIX);
		encodedBytes = Integer.toString(buffer, HEX_RADIX);
		return encodedBytes;
	}

	private String encodeUTF84Byte() {
		String encodedBytes;
		String fourthByte = Integer.toBinaryString(this.codepointHex);
		fourthByte = String.format("%21s", fourthByte).replace(' ', '0');
		fourthByte = fourthByte.substring(fourthByte.length() - 6);
		fourthByte = TEN + fourthByte;
		
		String thirdByte = Integer.toBinaryString(this.codepointHex);
		thirdByte = String.format("%21s", thirdByte).replace(' ', '0');
		thirdByte = thirdByte.substring(thirdByte.length() - 12, thirdByte.length() - 6);
		thirdByte = TEN + thirdByte;
		
		String secondByte = Integer.toBinaryString(this.codepointHex);
		secondByte = String.format("%21s", secondByte).replace(' ', '0');
		secondByte = secondByte.substring(secondByte.length() - 18, secondByte.length() - 12);
		secondByte = TEN + secondByte;
		
		String firstByte = Integer.toBinaryString(this.codepointHex);
		firstByte = String.format("%21s", firstByte).replace(' ', '0');
		firstByte = firstByte.substring(firstByte.length() - 21, firstByte.length() - 18);
		firstByte = "11110" + firstByte;
		
		int buffer = Integer.parseInt(firstByte, BINARY_RADIX);
		encodedBytes = Integer.toString(buffer, HEX_RADIX);
		int buffer2 = Integer.parseInt(secondByte, BINARY_RADIX);
		encodedBytes += Integer.toString(buffer2, HEX_RADIX);
		int buffer3 = Integer.parseInt(thirdByte, BINARY_RADIX);
		encodedBytes += Integer.toString(buffer3, HEX_RADIX);
		int buffer4 = Integer.parseInt(fourthByte, BINARY_RADIX);
		encodedBytes += Integer.toString(buffer4, HEX_RADIX);
		return encodedBytes;
	}
	
	/**
	 * Gets the codepoint.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the codepoint
	 */
	public int getCodepoint() {
		return this.codepointHex;
	}
	
	
}

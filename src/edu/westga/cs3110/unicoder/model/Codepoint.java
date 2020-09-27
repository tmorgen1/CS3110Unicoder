package edu.westga.cs3110.unicoder.model;

/**
 * The Class Codepoint.
 * 
 * @author Thomas Morgenstern
 * @version Fall 2020
 */
public class Codepoint {
	
	public static final int CODEPOINT_MIN = 0;
	public static final int CODEPOINT_MAX = 0x10FFFF;
	
	public static final int HEX_RADIX = 16;
	
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
		
		if (this.codepointHex < CODEPOINT_MIN || this.codepointHex > CODEPOINT_MAX) {
			throw new IllegalArgumentException("codepointHex must be in the unicode codepoint range.");
		}
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
